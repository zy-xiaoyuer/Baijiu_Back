package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.mapper.PoemsbydynastyMapper;
import com.baijiu.Baijiu_Back.mapper.UsersMapper;
import com.baijiu.Baijiu_Back.service.PoemsbydynastyService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
@Service
public class PoemsbydynastyServiceImpl extends ServiceImpl<PoemsbydynastyMapper, Poemsbydynasty> implements PoemsbydynastyService {
    @Autowired
    private PoemsbydynastyMapper poemsbydynastyMapper;


    @Override
    public IPage<Poemsbydynasty> getUserList(IPage<Poemsbydynasty> page, String search) {
        QueryWrapper<Poemsbydynasty> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("poetry", search);
        }
        return poemsbydynastyMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<Poemsbydynasty> page) {
        return poemsbydynastyMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Poemsbydynasty> page, Wrapper wrapper) {
        return poemsbydynastyMapper.pageCC(page, wrapper);
    }
    @Override
    public Long countAll() {
        return poemsbydynastyMapper.selectCount(null);
    }
    @Override
    public Map<String, Integer> getDynastyStatistics() {
        // 调用Mapper方法获取朝代统计数据
        List<Map<String, Object>> stats = poemsbydynastyMapper.getDynastyStatistics();

        // 创建一个用于存储朝代统计结果的Map
        Map<String, Integer> dynastyStats = new HashMap<>();

        // 遍历返回的统计数据，填充Map
        for (Map<String, Object> stat : stats) {
            String dynasty = (String) stat.get("dynasty");
            Integer count = ((Long) stat.get("count")).intValue(); // 将Long转为Integer
            dynastyStats.put(dynasty, count);
        }

        return dynastyStats;
    }
    @Override
    public Map<String, Integer> getAuthorStatistics() {
        // 调用Mapper方法获取朝代统计数据
        List<Map<String, Object>> stats = poemsbydynastyMapper.getAuthorStatistics();

        // 创建一个用于存储朝代统计结果的Map
        Map<String, Integer> AuthorStats = new HashMap<>();

        // 遍历返回的统计数据，填充Map
        for (Map<String, Object> stat : stats) {
            String author = (String) stat.get("author");
            Integer count = ((Long) stat.get("count")).intValue(); // 将Long转为Integer
            AuthorStats.put(author, count);
        }

        return AuthorStats;
    }

}
