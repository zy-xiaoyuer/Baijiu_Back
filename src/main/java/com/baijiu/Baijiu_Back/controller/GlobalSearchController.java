package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class GlobalSearchController {

    @Autowired
    private VesselSearchService vesselSearchService;
    private PoemsbydynastySearchService poemsbydynastySearchService;
    private PoembylocationSearchService poembylocationSearchService;
    private PoemimagesSearchService poemimagesSearchService;


    @PostMapping("/api/global")
    public Result globalSearch(@RequestParam("searchQuery") SearchParam searchParam) {
        String keyword = searchParam.getKeyword();

        List<Object> results = new ArrayList<>();

        // 搜索酒器
        List<Vessel> vessels = vesselSearchService.search(keyword);
        results.addAll(vessels);

        //搜索酒诗
        List<Poemsbydynasty> poemsbydynasties = poemsbydynastySearchService.search(keyword);
        results.addAll(poemsbydynasties);

        List<Poemsbylocation> poemsbylocations = poembylocationSearchService.search(keyword);
        results.addAll(poemsbylocations);

        //搜索酒画
        List<Poemimages> poemimages = poemimagesSearchService.search(keyword);
        results.addAll(poemimages);

        return Result.success(results);
    }

    public static class SearchParam {
        private String keyword;

        public String getKeyword() {
            return keyword;
        }
        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
//在前端添加一个输入字段，使用axios获取到接口