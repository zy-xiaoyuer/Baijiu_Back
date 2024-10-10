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
        int pageNum = queryPageParam.getPageNum();
        int pageSize = queryPageParam.getPageSize();

        // 获取每个表的总记录数
        long totalVessels = vesselSearchService.count(keyword);
        long totalPoemsByDynasty = poemsbydynastySearchService.count(keyword);
        long totalPoemsByLocation = poembylocationSearchService.count(keyword);
        long totalPoemImages = poemimagesSearchService.count(keyword);

        // 计算总记录数
        long totalResults = totalVessels + totalPoemsByDynasty + totalPoemsByLocation + totalPoemImages;

        // 计算需要返回的记录的起始索引和结束索引
        long start = (pageNum - 1) * pageSize;
        long end = pageNum * pageSize;

        // 如果结束索引超过了总记录数，将其设置为总记录数
        end = end > totalResults ? totalResults : end;

        List<Object> results = new ArrayList<>();

        // 根据总记录数和请求的页码、页面大小从每个表中获取记录
        long recordsTaken = 0;
        long recordsToTake = Math.min(pageSize, end - recordsTaken);

        // 搜索酒器
        if (recordsTaken < end) {
            IPage<Vessel> vesselResult = vesselSearchService.search(keyword, new Page<>(pageNum, (int)recordsToTake));
            results.addAll(vesselResult.getRecords());
            recordsTaken += vesselResult.getRecords().size();
        }

        // 搜索酒诗
        if (recordsTaken < end) {
            IPage<Poemsbydynasty> poemsbydynastyResult = poemsbydynastySearchService.search(keyword, new Page<>(pageNum, (int)recordsToTake));
            results.addAll(poemsbydynastyResult.getRecords());
            recordsTaken += poemsbydynastyResult.getRecords().size();
        }

        // 搜索酒诗按地点
        if (recordsTaken < end) {
            IPage<Poemsbylocation> poemsbylocationResult = poembylocationSearchService.search(keyword, new Page<>(pageNum, (int)recordsToTake));
            results.addAll(poemsbylocationResult.getRecords());
            recordsTaken += poemsbylocationResult.getRecords().size();
        }

        // 搜索酒画
        if (recordsTaken < end) {
            IPage<Poemimages> poemimagesResult = poemimagesSearchService.search(keyword, new Page<>(pageNum, (int)recordsToTake));
            results.addAll(poemimagesResult.getRecords());
            recordsTaken += poemimagesResult.getRecords().size();
        }

        // 截取到请求的页面大小
        if (results.size() > pageSize) {
            results = results.subList(0, pageSize);
        }

        return Result.success(results, totalResults);
    }
}
