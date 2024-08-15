package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Users;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
public interface UsersService extends IService<Users> {
    List<Users> list();

    IPage<Users> getUserList(IPage<Users> page, @Param("search") String search);
}
