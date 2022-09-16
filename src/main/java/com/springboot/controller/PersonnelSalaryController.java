package com.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.common.dto.SalaryDto;
import com.springboot.common.lang.Result;
import com.springboot.entity.PersonnelInfo;
import com.springboot.entity.SalaryBook;
import com.springboot.service.PersonnelInfoService;
import com.springboot.service.SalaryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pSalary")
public class PersonnelSalaryController {
    @Autowired
    SalaryBookService salaryBookService;

    @Autowired
    PersonnelInfoService personnelInfoService;

    @GetMapping("/person/{currentPage}")
    public Result getPersonSalary(@PathVariable(name = "currentPage") Integer currentPage){
        String orderBy="workID asc";
        PageHelper.startPage(currentPage,3,orderBy);
        List<SalaryDto> salarys = salaryBookService.selectSalaryList();
        PageInfo<SalaryDto> pageInfo = new PageInfo<SalaryDto>(salarys);
        return Result.success(pageInfo);
    }

    @PostMapping("/update")
    public Result updateBook(@RequestBody SalaryDto salaryDto){

        PersonnelInfo personnelInfo1 = personnelInfoService.getOne(new QueryWrapper<PersonnelInfo>().eq("personnelName", salaryDto.getPersonnelName())) ;
        SalaryBook salaryBook2 = salaryBookService.getOne(new QueryWrapper<SalaryBook>().eq("bookName", salaryDto.getBookName()));
        if (salaryBook2.getStatus() != 0) {
            personnelInfo1.setBasicSalary(salaryBook2.getBasicSalary());
            personnelInfo1.setTravelAllowance(salaryBook2.getTravelAllowance());
            personnelInfo1.setLunchAllowance(salaryBook2.getLunchAllowance());
            personnelInfo1.setBonus(salaryBook2.getBonus());
            personnelInfo1.setBookName(salaryBook2.getBookName());

            personnelInfoService.saveOrUpdate(personnelInfo1);

            return Result.success(personnelInfo1);
        } else {
            return Result.fail("修改失败，请重新输入信息");
        }
    }

    @GetMapping("/updateInfo/{workID}")
    public Result findPersonById(@PathVariable(name = "workID") String workID){
        PersonnelInfo personnelInfo = personnelInfoService.getOne(new QueryWrapper<PersonnelInfo>().eq("workID", workID));
        return Result.success(personnelInfo);
    }

    @PostMapping("/updateInfo")
    public Result updateInfo(@Validated @RequestBody SalaryDto salaryDto){
        PersonnelInfo personnelInfo1 = personnelInfoService.getOne(new QueryWrapper<PersonnelInfo>().eq("workID", salaryDto.getWorkID())) ;
        if (personnelInfo1.getPersonnelName() != null) {
            personnelInfo1.setBasicSalary(salaryDto.getBasicSalary());
            personnelInfo1.setTravelAllowance(salaryDto.getTravelAllowance());
            personnelInfo1.setLunchAllowance(salaryDto.getLunchAllowance());
            personnelInfo1.setBonus(salaryDto.getBonus());
            personnelInfo1.setJob(salaryDto.getJob());

            personnelInfoService.saveOrUpdate(personnelInfo1);
            return Result.success(personnelInfo1);
        } else {
            return Result.fail("修改失败，请重新输入信息");
        }
    }
}
