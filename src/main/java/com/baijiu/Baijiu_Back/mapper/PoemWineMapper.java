package com.baijiu.Baijiu_Back.mapper;

import com.baijiu.Baijiu_Back.entity.PoemWine;
import com.baijiu.Baijiu_Back.entity.Users;
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
 * @since 2024-08-18
 */
@Mapper
public interface PoemWineMapper extends BaseMapper<PoemWine> {

    IPage<PoemWine> selectPage(IPage<PoemWine> page, @Param("sql") String sql);


    IPage pageC(IPage<PoemWine> page);

    IPage pageCC(IPage<PoemWine> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
