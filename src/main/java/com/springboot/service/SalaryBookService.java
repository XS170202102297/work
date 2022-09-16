package com.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.common.dto.SalaryDto;
import com.springboot.common.lang.Result;
import com.springboot.entity.SalaryBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jb
 * @since 2021-01-08
 */
public interface SalaryBookService extends IService<SalaryBook> {
    List<SalaryDto> selectSalaryList();


}
