package com.springboot.service.impl;

import com.springboot.entity.PersonnelInfo;
import com.springboot.mapper.PersonnelInfoMapper;
import com.springboot.service.PersonnelInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jb
 * @since 2020-12-09
 */
@Service
public class PersonnelInfoServiceImpl extends ServiceImpl<PersonnelInfoMapper, PersonnelInfo> implements PersonnelInfoService {

}
