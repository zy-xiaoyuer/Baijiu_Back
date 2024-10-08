package com.baijiu.Baijiu_Back.service;

import com.aliyun.core.annotation.Query;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
public interface UsersService extends IService<Users> {


    IPage<Users> getUserList(IPage<Users> page, @Param("search") String search);

    IPage pageC(IPage<Users> page);

    IPage pageCC(IPage<Users> page, Wrapper wrapper);
    public Long countAll();
    //Optional<Users> findById(Integer );
}
