package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.common.SearchService;
import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PoemimagesSearchService implements SearchService<Poemimages> {
    @Autowired
    private PoemimagesService poemimagesService;

    @Override
    public List<Poemimages> search(String keyword) {
        LambdaQueryWrapper<Poemimages> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(Poemimages::getImagename, keyword)
                    .or().like(Poemimages::getDynasty, keyword)
            ;
        }
        return poemimagesService.list(queryWrapper);
    }
}
