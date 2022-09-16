package com.springboot.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.dto.SalaryDto;
import com.springboot.entity.SalaryBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jb
 * @since 2021-01-08
 */
public interface SalaryBookMapper extends BaseMapper<SalaryBook> {
//p.personnelName,p.workID,p.department,p.job,p.basicSalary,p.travelAllowance,p.lunchAllowance,p.bonus
    @Select(""+
            "SELECT s.bookName,p.*" +
            "FROM salarybook s,personnelinfo p " +
            "WHERE s.bookName=p.bookName")
    List<SalaryDto> selectSalaryList();


}
