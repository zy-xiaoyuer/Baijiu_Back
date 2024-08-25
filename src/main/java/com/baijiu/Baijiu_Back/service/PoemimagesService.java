package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
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
 * @since 2024-08-21
 */
public interface PoemimagesService extends IService<Poemimages> {
    IPage<Poemimages> getUserList(IPage<Poemimages> page, @Param("search") String search);

    IPage pageC(IPage<Poemimages> page);

    IPage pageCC(IPage<Poemimages> page, Wrapper wrapper);


}
