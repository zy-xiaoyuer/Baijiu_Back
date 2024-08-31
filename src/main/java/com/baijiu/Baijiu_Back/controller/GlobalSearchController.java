package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.service.VesselSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class GlobalSearchController {

    @Autowired
    private VesselSearchService vesselSearchService;

    @PostMapping("/api/global")
    public Result globalSearch(@RequestBody SearchParam searchParam) {
        String keyword = searchParam.getKeyword();

        List<Object> results = new ArrayList<>();

        // 搜索酒器
        List<Vessel> vessels = vesselSearchService.search(keyword);
        results.addAll(vessels);

        // 搜索酒诗（假设存在）
//        List<Poemsbydynasty> poems = poemsbydynastySearchService.search(keyword);
//        results.addAll(poems);

        // 可以继续添加其他实体的搜索

        return Result.success(results);
    }

    // 定义一个简单的请求参数类
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