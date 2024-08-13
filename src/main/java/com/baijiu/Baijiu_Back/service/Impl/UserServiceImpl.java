package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.User;
import com.baijiu.Baijiu_Back.mapper.UserMapper;
import com.baijiu.Baijiu_Back.service.UserService;
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
 * @since 2024-08-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private  UserMapper userMapper;

    @Override
    public int getUserByMassage(String name, String password) {
        User user = userMapper.getUserByMassage(name, password);
        if (user != null) {
            return user.getId();
        }
        // 用户不存在或密码错误
        return 0;
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(null); // 假设 findAll() 方法会返回所有用户
    }


}
