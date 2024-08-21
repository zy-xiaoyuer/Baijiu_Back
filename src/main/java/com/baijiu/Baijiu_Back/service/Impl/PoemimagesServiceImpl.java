package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.mapper.PoemimagesMapper;
import com.baijiu.Baijiu_Back.mapper.VesselTotalMapper;
import com.baijiu.Baijiu_Back.service.PoemimagesService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ltt
 * @since 2024-08-21
 */
@Service
public class PoemimagesServiceImpl extends ServiceImpl<PoemimagesMapper, Poemimages> implements PoemimagesService {
    @Autowired
    private PoemimagesMapper poemimagesMapper;


    @Override
    public IPage<Poemimages> getUserList(IPage<Poemimages> page, String search) {
        QueryWrapper<Poemimages> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("imagename", search);
        }
        return poemimagesMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<Poemimages> page) {
        return poemimagesMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Poemimages> page, Wrapper wrapper) {
        return poemimagesMapper.pageCC(page, wrapper);
    }

}
