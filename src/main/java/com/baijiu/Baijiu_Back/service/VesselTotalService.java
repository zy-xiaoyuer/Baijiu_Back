package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Users;
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
 * @since 2024-08-19
 */
public interface VesselTotalService extends IService<VesselTotal> {
    IPage<VesselTotal> getUserList(IPage<VesselTotal> page, @Param("search") String search);

    IPage pageC(IPage<VesselTotal> page);

    IPage pageCC(IPage<VesselTotal> page, Wrapper wrapper);

}
