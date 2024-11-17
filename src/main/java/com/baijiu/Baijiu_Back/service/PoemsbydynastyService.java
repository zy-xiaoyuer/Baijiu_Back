package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
public interface PoemsbydynastyService extends IService<Poemsbydynasty> {
    IPage<Poemsbydynasty> getUserList(IPage<Poemsbydynasty> page, @Param("search") String search);

    IPage pageC(IPage<Poemsbydynasty> page);

    IPage pageCC(IPage<Poemsbydynasty> page, Wrapper wrapper);
    public Long countAll();
    public Map<String, Integer> getDynastyStatistics();
    public Map<String, Integer> getAuthorStatistics();
}
