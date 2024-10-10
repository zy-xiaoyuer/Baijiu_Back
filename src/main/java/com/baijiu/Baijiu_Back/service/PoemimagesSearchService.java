package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.common.SearchService;
import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PoemimagesSearchService implements SearchService<Poemimages> {
    @Autowired
    private PoemimagesService poemimagesService;

    @Override
    public IPage<Poemimages> search(String keyword, Page<Poemimages> page) {
        LambdaQueryWrapper<Poemimages> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(Poemimages::getImagename, keyword)
                    .or().like(Poemimages::getDynasty, keyword)
            ;
        }
        return poemimagesService.page(page, queryWrapper);
    }
    @Override
    public long count(String keyword) {
        LambdaQueryWrapper<Poemimages> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(Poemimages::getImagename, keyword)
                    .or().like(Poemimages::getDynasty, keyword)
            ;
        }
        return poemimagesService.count(queryWrapper);
    }
}
