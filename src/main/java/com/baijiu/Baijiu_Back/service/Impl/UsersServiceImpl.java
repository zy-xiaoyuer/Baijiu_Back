package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.mapper.UsersMapper;
import com.baijiu.Baijiu_Back.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public List<Users> list() {
        return usersMapper.selectAll(); // 假设 UsersMapper 有 selectAll 方法
    }

    @Override
    public IPage<Users> getUserList(IPage<Users> page, String search) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("username", search);
        }
        return usersMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

}
