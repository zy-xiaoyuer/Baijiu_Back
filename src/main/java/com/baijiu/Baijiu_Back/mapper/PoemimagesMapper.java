package com.baijiu.Baijiu_Back.mapper;

import com.baijiu.Baijiu_Back.entity.Poemimages;
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
 * @since 2024-08-21
 */
@Mapper
public interface PoemimagesMapper extends BaseMapper<Poemimages> {
    IPage<Poemimages> selectPage(IPage<Poemimages> page, @Param("sql") String sql);

    IPage pageC(IPage<Poemimages> page);

    IPage pageCC(IPage<Poemimages> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
