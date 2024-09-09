package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.common.SearchService;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PoemsbydynastySearchService implements SearchService<Poemsbydynasty> {
    @Autowired
    private PoemsbydynastyService poemsbydynastyService;

    @Override
    public List<Poemsbydynasty> search(String keyword) {
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
        return poemsbydynastyService.list(queryWrapper);
    }
}
