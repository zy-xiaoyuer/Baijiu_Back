package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.mapper.UsersMapper;
import com.baijiu.Baijiu_Back.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;



    @Override
    public IPage<Users> getUserList(IPage<Users> page, String search) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("username", search);
        }
        return usersMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<Users> page) {
        return usersMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Users> page, Wrapper wrapper) {
        return usersMapper.pageCC(page, wrapper);
    }



}
