package com.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.dto.SearchDto;
import com.springboot.common.lang.Result;
import com.springboot.entity.CompanyInfo;
import com.springboot.entity.PersonnelInfo;
import com.springboot.service.CompanyInfoService;
import com.springboot.service.PersonnelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jb
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/cInfo")
public class CompanyInfoController {

    @Autowired
    CompanyInfoService companyinfoService;

    @Autowired
    PersonnelInfoService personInfoService;

//    //查询所有部门信息
//    @GetMapping("/all")
//    public Result searchCompanyInfo(CompanyInfo companyinfo){
//        List lists = new ArrayList<>();
//        List<CompanyInfo> list1 =companyinfoService.list(new QueryWrapper<CompanyInfo>().eq("depid",0));
//        List<CompanyInfo> list2 =companyinfoService.list(new QueryWrapper<CompanyInfo>().eq("depid",1));
//        List<CompanyInfo> list3 =companyinfoService.list(new QueryWrapper<CompanyInfo>().eq("depid",2));
//        lists.add(list1);
//        lists.add(list2);
//        lists.add(list3);
//        return Result.success(lists);
//    }
    //查找员工信息（查）
    @PostMapping("/search/{currentPage}")
    public Result searchDepPerson(@RequestBody SearchDto searchDto, @PathVariable(name = "currentPage")Integer currentPage){
        Page page = new Page(currentPage, 3);
        IPage pageData = personInfoService.page(page, new QueryWrapper<PersonnelInfo>().like("department", searchDto.getDepartment()));
        if (pageData.getTotal() == 0){
            return Result.fail("无用户存在");
        }
        return Result.success(pageData);
    }

    //分页展示员工
    @GetMapping("/dPerson/{currentPage}")
    public Result depPerson(@PathVariable(name = "currentPage") Integer currentPage) {
        Page page = new Page(currentPage, 3);
        IPage pageData = personInfoService.page(page, new QueryWrapper<PersonnelInfo>().orderByAsc("workID"));

        return Result.success(pageData);
    }

}
