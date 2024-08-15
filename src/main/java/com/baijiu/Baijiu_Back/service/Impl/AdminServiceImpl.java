package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Admin;
import com.baijiu.Baijiu_Back.mapper.AdminMapper;
import com.baijiu.Baijiu_Back.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private  AdminMapper adminMapper;

    @Override
    public int getAdminByMassage(String name, String password) {
        Admin admin = adminMapper.getAdminByMassage(name, password);
        if (admin != null) {
            return admin.getId();
        }
        // 用户不存在或密码错误
        return 0;
    }


}
