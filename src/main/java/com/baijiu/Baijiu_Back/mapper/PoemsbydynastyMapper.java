package com.baijiu.Baijiu_Back.mapper;

import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
@Mapper
public interface PoemsbydynastyMapper extends BaseMapper<Poemsbydynasty> {
    IPage<Poemsbydynasty> selectPage(IPage<Poemsbydynasty> page, @Param("sql") String sql);

    IPage pageC(IPage<Poemsbydynasty> page);

    IPage pageCC(IPage<Poemsbydynasty> page, @Param(Constants.WRAPPER) Wrapper wrapper);
    @Select("SELECT dynasty_name AS dynasty, poem_count AS count FROM dynasty_poem_count")
    List<Map<String, Object>> getDynastyStatistics();
    @Select("SELECT author_name AS author, poem_count AS count FROM author_poem_count")
    List<Map<String, Object>> getAuthorStatistics();
}
