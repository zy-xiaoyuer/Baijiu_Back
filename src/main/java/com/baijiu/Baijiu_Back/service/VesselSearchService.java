package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.common.SearchService;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
//针对每个实体实现SearchService接口
@Service
public class VesselSearchService implements SearchService<Vessel> {
    @Autowired
    private VesselService vesselService;

    @Override
    public IPage<Vessel> search(String keyword, Page<Vessel> page) {
        LambdaQueryWrapper<Vessel> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(keyword)) {
            queryWrapper.like(Vessel::getAge, keyword)
                    .or().like(Vessel::getNow, keyword);
        }

        return vesselService.page(page, queryWrapper);
    }
    @Override
    public long count(String keyword) {
            LambdaQueryWrapper<Vessel> queryWrapper = new LambdaQueryWrapper<>();
            if (StringUtils.hasText(keyword)) {
                queryWrapper.like(Vessel::getAge, keyword)
                        .or().like(Vessel::getNow, keyword);
            }
            return vesselService.count(queryWrapper);

    }
}
