package com.baijiu.Baijiu_Back.mapper;

import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
@Mapper
public interface VesselTotalMapper extends BaseMapper<VesselTotal> {
    IPage<VesselTotal> selectPage(IPage<VesselTotal> page, @Param("sql") String sql);

    IPage pageC(IPage<VesselTotal> page);

    IPage pageCC(IPage<VesselTotal> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
