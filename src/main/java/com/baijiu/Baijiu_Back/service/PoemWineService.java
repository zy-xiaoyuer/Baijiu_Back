package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.PoemWine;
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
 * @since 2024-08-18
 */
public interface PoemWineService extends IService<PoemWine> {
    IPage<PoemWine> getUserList(IPage<PoemWine> page, @Param("search") String search);

    IPage pageC(IPage<PoemWine> page);

    IPage pageCC(IPage<PoemWine> page, Wrapper wrapper);
}
