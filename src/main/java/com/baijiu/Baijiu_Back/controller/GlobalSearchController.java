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
    @PostMapping("/api/globalPage1")
    public Result globalSearchWithPagination1(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String keyword = (String) params.get("searchQuery");
        int pageNum = queryPageParam.getPageNum();
        int pageSize = queryPageParam.getPageSize();

        // 步骤1: 获取每个表的总记录数
        long totalVessels = vesselSearchService.count(keyword);
        long totalPoemsByDynasty = poemsbydynastySearchService.count(keyword);
        long totalPoemsByLocation = poembylocationSearchService.count(keyword);
        long totalPoemImages = poemimagesSearchService.count(keyword);

        // 计算总记录数
        long totalResults = totalVessels + totalPoemsByDynasty + totalPoemsByLocation + totalPoemImages;

        // 计算需要返回的记录的结束索引
        long end = ((long)pageNum * pageSize) > totalResults ? totalResults : ((long)pageNum * pageSize);

        List<Object> results = new ArrayList<>();

        // 步骤2: 根据总记录数和请求的页码、页面大小计算每个表应该返回的记录数
        // 并进行查询
        long recordsTaken = 0;
        if (recordsTaken < end) {
            IPage<Vessel> vesselResult = vesselSearchService.search(keyword, new Page<>(1, (int)Math.min(end - recordsTaken, pageSize)));
            results.addAll(vesselResult.getRecords());
            recordsTaken += vesselResult.getRecords().size();
        }
        if (recordsTaken < end) {
            IPage<Poemsbydynasty> poemsbydynastyResult = poemsbydynastySearchService.search(keyword, new Page<>(1, (int)Math.min(end - recordsTaken, pageSize)));
            results.addAll(poemsbydynastyResult.getRecords());
            recordsTaken += poemsbydynastyResult.getRecords().size();
        }
        if (recordsTaken < end) {
            IPage<Poemsbylocation> poemsbylocationResult = poembylocationSearchService.search(keyword, new Page<>(1, (int)Math.min(end - recordsTaken, pageSize)));
            results.addAll(poemsbylocationResult.getRecords());
            recordsTaken += poemsbylocationResult.getRecords().size();
        }
        if (recordsTaken < end) {
            IPage<Poemimages> poemimagesResult = poemimagesSearchService.search(keyword, new Page<>(1, (int)Math.min(end - recordsTaken, pageSize)));
            results.addAll(poemimagesResult.getRecords());
            recordsTaken += poemimagesResult.getRecords().size();
        }

        // 确保结果不超过请求的页面大小
        if (results.size() > pageSize) {
            List<Object> finalResults = results.subList(0, pageSize);
            return Result.success(finalResults, totalResults);
        }

        return Result.success(results, totalResults);
    }

}
