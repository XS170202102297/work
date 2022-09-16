package com.springboot.controller;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.common.dto.LoginDto;
import com.springboot.common.lang.Result;
import com.springboot.entity.Account;
import com.springboot.service.AccountService;
import com.springboot.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jb
 * @since 2020-11-17
 */
@RestController
//@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    JwtUtils jwtUtils;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        Account account = accountService.getOne(new QueryWrapper<Account>().eq("username", loginDto.getUsername()));
        Assert.notNull(account, "用户不存在或密码不正确");

        if(!account.getPassword().equals(loginDto.getPassword())){
            return Result.fail("用户不存在或密码不正确",loginDto.getPassword());
        }

        String jwt = jwtUtils.generateToken(account.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", account.getId())
                .put("username", account.getUsername())
                .put("msg","登录成功")
                .put("jwt",response.getHeader("Authorization"))
                .map()
        );
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody LoginDto loginDto){
        Account account2 = accountService.getOne(new QueryWrapper<Account>().eq("username", loginDto.getUsername()));
        if(account2==null){
            Account account = new Account();
            account.setUsername(loginDto.getUsername());
            account.setPassword(loginDto.getPassword());

            accountService.save(account);
            return Result.success(account);
        }else {
            return Result.fail("用户已存在",loginDto.getUsername());
        }
    }



    @GetMapping("/{id}")
    public Object test(@PathVariable("id") long id) {
        return accountService.getById(id);
    }

}
