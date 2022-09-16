package com.springboot.mapper;

import com.springboot.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jb
 * @since 2020-11-17
 */
public interface AccountMapper extends BaseMapper<Account> {

}
