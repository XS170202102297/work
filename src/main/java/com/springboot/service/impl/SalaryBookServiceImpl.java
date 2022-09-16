package com.springboot.service.impl;

import com.springboot.common.dto.SalaryDto;
import com.springboot.common.lang.Result;
import com.springboot.entity.SalaryBook;
import com.springboot.mapper.SalaryBookMapper;
import com.springboot.service.SalaryBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jb
 * @since 2021-01-08
 */
@Service
public class SalaryBookServiceImpl extends ServiceImpl<SalaryBookMapper, SalaryBook> implements SalaryBookService {


    public List<SalaryDto> selectSalaryList() {

        return baseMapper.selectSalaryList();
    }
}
