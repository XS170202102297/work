package com.springboot.service.impl;

import com.springboot.entity.CompanyInfo;
import com.springboot.mapper.CompanyInfoMapper;
import com.springboot.service.CompanyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jb
 * @since 2021-02-19
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService {

}
