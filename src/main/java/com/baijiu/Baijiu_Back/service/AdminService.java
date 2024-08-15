package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
public interface AdminService extends IService<Admin> {
    int getAdminByMassage(@Param("username") String name, @Param("password") String password);

}
