package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/search")
public class GlobalSearchController {

    @Autowired
    private VesselSearchService vesselSearchService;
    @Autowired
    private PoemsbydynastySearchService poemsbydynastySearchService;
    @Autowired
    private PoembylocationSearchService poembylocationSearchService;
    @Autowired
    private PoemimagesSearchService poemimagesSearchService;

    @PostMapping("/api/globalPage")
    public Result globalSearchWithPagination(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String keyword = (String) params.get("searchQuery");

        List<Object> results = new ArrayList<>();
        int totalVessels = 0, totalPoemsByDynasty = 0, totalPoemsByLocation = 0, totalPoemImages = 0;

        // 搜索酒器
        Page<Vessel> vesselPage = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        IPage<Vessel> vesselResult = vesselSearchService.search(keyword, vesselPage);
        results.addAll(vesselResult.getRecords());
        totalVessels = (int)vesselResult.getTotal();

        // 搜索酒诗
        Page<Poemsbydynasty> poemsbydynastyPage = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        IPage<Poemsbydynasty> poemsbydynastyResult = poemsbydynastySearchService.search(keyword, poemsbydynastyPage);
        results.addAll(poemsbydynastyResult.getRecords());
        totalPoemsByDynasty = (int)poemsbydynastyResult.getTotal();

        // 搜索酒诗按地点
        Page<Poemsbylocation> poemsbylocationPage = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        IPage<Poemsbylocation> poemsbylocationResult = poembylocationSearchService.search(keyword, poemsbylocationPage);
        results.addAll(poemsbylocationResult.getRecords());
        totalPoemsByLocation = (int)poemsbylocationResult.getTotal();

        // 搜索酒画
        Page<Poemimages> poemimagesPage = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        IPage<Poemimages> poemimagesResult = poemimagesSearchService.search(keyword, poemimagesPage);
        results.addAll(poemimagesResult.getRecords());
        totalPoemImages = (int)poemimagesResult.getTotal();

        int totalResults = totalVessels + totalPoemsByDynasty + totalPoemsByLocation + totalPoemImages;
        return Result.success(results,(long)totalResults);
    }

}
//在前端添加一个输入字段，使用axios获取到接口