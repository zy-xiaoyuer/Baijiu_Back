package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Vessel;
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
 * @since 2024-08-26
 */
public interface VesselService extends IService<Vessel> {
    IPage<Vessel> getUserList(IPage<Vessel> page, @Param("search") String search);

    IPage pageC(IPage<Vessel> page);

    IPage pageCC(IPage<Vessel> page, Wrapper wrapper);
}
