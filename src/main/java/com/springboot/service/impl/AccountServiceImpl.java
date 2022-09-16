package com.springboot.service.impl;

import com.springboot.entity.Account;
import com.springboot.mapper.AccountMapper;
import com.springboot.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jb
 * @since 2020-11-17
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
