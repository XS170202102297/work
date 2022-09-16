package com.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.dto.SearchDto;
import com.springboot.common.dto.SeniorSearchDto;
import com.springboot.common.lang.Result;
import com.springboot.entity.PersonnelInfo;
import com.springboot.mapper.PersonnelInfoMapper;
import com.springboot.service.PersonnelInfoService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jb
 * @since 2020-12-09
 */
@RestController
@RequestMapping("/pInfo")
public class PersonnelInfoController {
    @Autowired
    PersonnelInfoService personInfoService;

    @Autowired
    PersonnelInfoMapper personnelInfoMapper;

    //添加员工信息（增）
    @PostMapping("/addPerson")
    public Result addPersonInfo(@Validated @RequestBody SearchDto searchDto) {
        PersonnelInfo personnelInfo1 = personInfoService.getOne(new QueryWrapper<PersonnelInfo>().eq("workID", searchDto.getWorkID()));
        if (personnelInfo1 == null) {
            PersonnelInfo personnelInfo = new PersonnelInfo();
            personnelInfo.setWorkID(searchDto.getWorkID());
            personnelInfo.setPersonnelName(searchDto.getPersonnelName());
            personnelInfo.setPersonnelSex(searchDto.getPersonnelSex());
            personnelInfo.setDepartment(searchDto.getDepartment());
            personnelInfo.setBookName(searchDto.getBookName());
            personnelInfo.setJob(searchDto.getJob());
            personnelInfo.setBeginWorkDate(searchDto.getBeginWorkDate());
            personnelInfo.setWorkState(searchDto.getWorkState());

            personnelInfo.setId(searchDto.getId());
            personnelInfo.setPhone(searchDto.getPhone());
            personnelInfo.setEMail(searchDto.getEMail());
            personnelInfo.setBirthday(searchDto.getBirthday());
            personnelInfo.setAddress(searchDto.getAddress());

            personInfoService.save(personnelInfo);
            return Result.success(personnelInfo);
        } else {
            return Result.fail("添加失败，请重新输入信息");
        }
    }

    //删除员工信息（删）
    //@RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
    @PostMapping("/deletePerson")
    public Result deletePersonInfo(@RequestBody SearchDto searchDto){
        boolean rows = personInfoService.remove(new QueryWrapper<PersonnelInfo>().eq("workID", searchDto.getWorkID()));
        
        if(rows){
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/deletePersons")
    public Result deletePersonInfos(@RequestBody List<String> ids ){
        int sum = personnelInfoMapper.deleteBatchIds(ids);

        if(sum>0){
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }


    //修改员工信息（改）
     // 1.根据id查找用户
    @GetMapping("/update/{workID}")
    public Result findPersonById(@PathVariable(name = "workID") String workID){
        PersonnelInfo personnelInfo = personInfoService.getOne(new QueryWrapper<PersonnelInfo>().eq("workID", workID));
        return Result.success(personnelInfo);
    }

    @PostMapping("/update")
    public Result updatePersonInfo(@Validated @RequestBody SearchDto searchDto){
        if (1==1) {
            UpdateWrapper<PersonnelInfo> updateWrapper =new UpdateWrapper<>();
            updateWrapper.eq("personnelID",searchDto.getPersonnelID());
            PersonnelInfo personnelInfo = new PersonnelInfo();
            personnelInfo.setPersonnelName(searchDto.getPersonnelName());
            personnelInfo.setWorkID(searchDto.getWorkID());
            personnelInfo.setPersonnelSex(searchDto.getPersonnelSex());
            personnelInfo.setDepartment(searchDto.getDepartment());
            personnelInfo.setJob(searchDto.getJob());
            personnelInfo.setBeginWorkDate(searchDto.getBeginWorkDate());
            personnelInfo.setWorkState(searchDto.getWorkState());
            personnelInfoMapper.update(personnelInfo,updateWrapper);

            return Result.success(personnelInfo);
        } else {
            return Result.fail("修改失败,请重新输入信息");
        }
    }

    @PostMapping("/updateSenior")
    public Result updatePersonSeniorInfo(@Validated @RequestBody SeniorSearchDto seniorSearchDto){
        if (1==1) {
            UpdateWrapper<PersonnelInfo> updateWrapper =new UpdateWrapper<>();
            updateWrapper.eq("personnelID",seniorSearchDto.getPersonnelID());
            PersonnelInfo personnelInfo = new PersonnelInfo();
            personnelInfo.setWorkID(seniorSearchDto.getWorkID());
            personnelInfo.setPersonnelName(seniorSearchDto.getPersonnelName());
            personnelInfo.setId(seniorSearchDto.getId());
            personnelInfo.setPhone(seniorSearchDto.getPhone());
            personnelInfo.setEMail(seniorSearchDto.getEMail());
            personnelInfo.setBirthday(seniorSearchDto.getBirthday());
            personnelInfo.setAddress(seniorSearchDto.getAddress());
            personnelInfoMapper.update(personnelInfo,updateWrapper);
            return Result.success(personnelInfo);
        } else {
            return Result.fail("修改失败,请重新输入信息");
        }
    }

    //查找员工信息（查）
    //@PathVariable是用来对指定请求的URL路径里面的变量
    @PostMapping("/search/{currentPage}")
    public Result searchPersonInfo(@RequestBody SearchDto searchDto, @PathVariable(name = "currentPage")Integer currentPage){
        Page page = new Page(currentPage, 3);
        IPage pageData = personInfoService.page(page, new QueryWrapper<PersonnelInfo>().like("personnelName", searchDto.getPersonnelName()));

        if (pageData.getTotal() == 0){
            return Result.fail("用户不存在");
        }
        return Result.success(pageData);
    }

    //分页展示员工
    
    @GetMapping("/info/{currentPage}")
    public Result personInfo(@PathVariable(name = "currentPage") Integer currentPage) {
//        @PathVariable@RequestParam(defaultValue = "1",name = "currentPage") Integer currentPage
            Page page = new Page(currentPage, 3);
            IPage pageData = personInfoService.page(page, new QueryWrapper<PersonnelInfo>().orderByAsc("workID"));
        return Result.success(pageData);
    }

}


