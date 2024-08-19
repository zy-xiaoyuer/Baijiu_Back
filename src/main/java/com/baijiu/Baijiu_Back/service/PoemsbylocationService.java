package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
public interface PoemsbylocationService extends IService<Poemsbylocation> {

    IPage<Poemsbylocation> getUserList(IPage<Poemsbylocation> page, @Param("search") String search);

    IPage pageC(IPage<Poemsbylocation> page);

    IPage pageCC(IPage<Poemsbylocation> page, Wrapper wrapper);

}
