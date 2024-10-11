package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.service.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
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

        // 查询每个表的数据
        List<Vessel> vessels = vesselSearchService.search(keyword, new Page<>(1, Integer.MAX_VALUE)).getRecords();
        List<Poemsbydynasty> poemsbydynasties = poemsbydynastySearchService.search(keyword, new Page<>(1, Integer.MAX_VALUE)).getRecords();
        List<Poemsbylocation> poemsbylocations = poembylocationSearchService.search(keyword, new Page<>(1, Integer.MAX_VALUE)).getRecords();
        List<Poemimages> poemimages = poemimagesSearchService.search(keyword, new Page<>(1, Integer.MAX_VALUE)).getRecords();

        // 合并数据
        List<Object> allResults = new ArrayList<>();
        allResults.addAll(vessels);
        allResults.addAll(poemsbydynasties);
        allResults.addAll(poemsbylocations);
        allResults.addAll(poemimages);

        // 计算总记录数
        long totalResults = allResults.size();

        // 计算需要返回的记录的起始索引和结束索引
        long start = (pageNum - 1) * pageSize;
        long end = Math.min(start + pageSize, totalResults);

        // 截取到请求的页面大小
        List<Object> results = allResults.subList((int) start, (int) end);

        return Result.success(results, totalResults);
    }
}