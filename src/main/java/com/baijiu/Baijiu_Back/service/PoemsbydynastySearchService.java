package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.common.SearchService;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PoemsbydynastySearchService implements SearchService<Poemsbydynasty> {
    @Autowired
    private PoemsbydynastyService poemsbydynastyService;

    @Override
    public IPage<Poemsbydynasty> search(String keyword, Page<Poemsbydynasty> page) {
        LambdaQueryWrapper<Poemsbydynasty> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(Poemsbydynasty::getTitle, keyword)
                    .or().like(Poemsbydynasty::getAuthor, keyword)
                    .or().like(Poemsbydynasty::getDynasty, keyword)
                    .or().like(Poemsbydynasty::getContent, keyword)
                    .or().like(Poemsbydynasty::getPlace, keyword)
                    .or().like(Poemsbydynasty::getEmotion, keyword)
                    .or().like(Poemsbydynasty::getTime, keyword)
            ;
        }
        return poemsbydynastyService.page(page, queryWrapper);
    }
}
