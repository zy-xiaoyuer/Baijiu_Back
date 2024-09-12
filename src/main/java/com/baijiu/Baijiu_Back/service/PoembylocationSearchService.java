package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.common.SearchService;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PoembylocationSearchService implements SearchService<Poemsbylocation> {
    @Autowired
    private PoemsbylocationService poemsbylocationService;
    @Override
    public List<Poemsbylocation> search(String keyword) {
        LambdaQueryWrapper<Poemsbylocation> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(Poemsbylocation::getPoetry, keyword)
                    .or().like(Poemsbylocation::getFullName, keyword)
                    .or().like(Poemsbylocation::getAuthor, keyword)
                    .or().like(Poemsbylocation::getCity, keyword)
                    .or().like(Poemsbylocation::getDynasty, keyword)
                    .or().like(Poemsbylocation::getEmotion, keyword)
                    .or().like(Poemsbylocation::getDistrict, keyword)
                    .or().like(Poemsbylocation::getContent, keyword)
                    .or().like(Poemsbylocation::getCountry, keyword)
                    .or().like(Poemsbylocation::getProvince, keyword)
            ;
        }
        return poemsbylocationService.list(queryWrapper);
    }
}