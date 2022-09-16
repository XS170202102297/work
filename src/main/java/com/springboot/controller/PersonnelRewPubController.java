package com.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.dto.SearchDto;
import com.springboot.common.lang.Result;
import com.springboot.entity.PersonnelInfo;
import com.springboot.entity.PersonnelRewPub;
import com.springboot.mapper.PersonnelRewPubMapper;
import com.springboot.service.PersonnelInfoService;
import com.springboot.service.PersonnelRewPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jb
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/pRewPub")
public class PersonnelRewPubController {
    @Autowired
    PersonnelInfoService personInfoService;

    @Autowired
    PersonnelRewPubService personnelRewPubService;

    @Autowired
    PersonnelRewPubMapper personnelRewPubMapper;

    //添加奖惩信息（增）
    @PostMapping("/addRewPub")
    public Result addPersonRewPub(@Validated @RequestBody PersonnelRewPub personnelRewPub) {
            PersonnelInfo personnelInfo1 = personInfoService.getOne(new QueryWrapper<PersonnelInfo>().eq("workID", personnelRewPub.getWorkID()));

            PersonnelRewPub pRewPub = new PersonnelRewPub();
            pRewPub.setWorkID(personnelRewPub.getWorkID());
            pRewPub.setPersonnelName(personnelRewPub.getPersonnelName());
            pRewPub.setDate(personnelRewPub.getDate());
            pRewPub.setRewPub(personnelRewPub.getRewPub());
            pRewPub.setDepartment(personnelRewPub.getDepartment());
            pRewPub.setRemarks(personnelRewPub.getRemarks());

            if(pRewPub.getWorkID().equals(personnelInfo1.getWorkID()) && pRewPub.getPersonnelName().equals(personnelInfo1.getPersonnelName())
                    && pRewPub.getDepartment().equals(personnelInfo1.getDepartment())){
                personnelRewPubService.save(pRewPub);
                return Result.success(pRewPub);
            }else{
                return Result.fail("输入的工号 员工姓名 部门信息不匹配,"+
                        " 若输入工号为:"+personnelInfo1.getWorkID()+",  员工姓名是"+personnelInfo1.getPersonnelName()+"  部门是"+personnelInfo1.getDepartment());
            }


        }

    //删除奖惩信息（删）
    @PostMapping("/deleteRewPub")
    public Result deletePersonRewPub(@RequestBody PersonnelRewPub pRewPub){
        boolean rows = personnelRewPubService.remove(new QueryWrapper<PersonnelRewPub>().eq("id", pRewPub.getId()));

        if(rows){
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

    @PostMapping("/deleteRewPubs")
    public Result deleteRewPubInfos(@RequestBody List<String> ids ){
    for (int i=ids.size()-1;i>=0;i--) {
        personnelRewPubMapper.delete(new QueryWrapper<PersonnelRewPub>().eq("id", ids.get(i)));
    }
        if(ids.size()>0){
            return Result.success("删除成功");
        }else{
            return Result.fail("删除失败");
        }
    }

    //修改奖惩信息（改）
    @GetMapping("/updateRewPub/{id}")
    public Result findRewPubById(@PathVariable(name = "id") String id){
        return Result.success(personnelRewPubService.getById(id));
    }

    @PostMapping("/updateRewPub")
    public Result updateRewPubInfo(@RequestBody PersonnelRewPub pRewPub){
        if (1==1) {
            UpdateWrapper<PersonnelRewPub> updateWrapper =new UpdateWrapper<>();
            updateWrapper.eq("id",pRewPub.getId());
            PersonnelRewPub pRewPub1 = new PersonnelRewPub();
            pRewPub1.setPersonnelName(pRewPub.getPersonnelName());
            pRewPub1.setWorkID(pRewPub.getWorkID());
            pRewPub1.setDepartment(pRewPub.getDepartment());
            pRewPub1.setDate(pRewPub.getDate());
            pRewPub1.setRewPub(pRewPub.getRewPub());
            pRewPub1.setRemarks(pRewPub.getRemarks());
            personnelRewPubMapper.update(pRewPub,updateWrapper);

            return Result.success(pRewPub1);
        } else {
            return Result.fail("修改失败，请重新输入信息");
        }

    }

    //查找员工信息（查）
    @PostMapping("/searchRewPub/{currentPage}")
    public Result searchRewPub(@RequestBody PersonnelRewPub pRewPub, @PathVariable(name = "currentPage")Integer currentPage){
        Page page = new Page(currentPage, 3);
        IPage pageData = personnelRewPubService.page(page, new QueryWrapper<PersonnelRewPub>().like("personnelName", pRewPub.getPersonnelName()));
        if (pageData.getTotal() == 0){
            return Result.fail("用户不存在");
        }
        return Result.success(pageData);
    }

    //分页展示奖惩信息
    @GetMapping("/RewPub/{currentPage}")
    public Result PersonRewPub(@PathVariable(name = "currentPage") Integer currentPage) {

        Page page = new Page(currentPage, 3);
        IPage pageData = personnelRewPubService.page(page, new QueryWrapper<PersonnelRewPub>().orderByAsc("workID"));

        return Result.success(pageData);
    }
}
