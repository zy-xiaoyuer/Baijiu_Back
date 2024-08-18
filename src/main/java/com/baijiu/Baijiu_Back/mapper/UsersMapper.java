package com.baijiu.Baijiu_Back.mapper;


import com.baijiu.Baijiu_Back.entity.Users;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {


    IPage<Users> selectPage(IPage<Users> page, @Param("sql") String sql);


    IPage pageC(IPage<Users> page);

    IPage pageCC(IPage<Users> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
