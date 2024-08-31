package com.baijiu.Baijiu_Back.mapper;

import com.baijiu.Baijiu_Back.entity.Vessel;
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
 * @since 2024-08-26
 */
@Mapper
public interface VesselMapper extends BaseMapper<Vessel> {
    IPage<Vessel> selectPage(IPage<Vessel> page, @Param("sql") String sql);

    IPage pageC(IPage<Vessel> page);

    IPage pageCC(IPage<Vessel> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
