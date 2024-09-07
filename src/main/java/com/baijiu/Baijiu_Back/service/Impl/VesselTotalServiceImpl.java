package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.mapper.VesselTotalMapper;
import com.baijiu.Baijiu_Back.service.VesselTotalService;
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
 * @since 2024-08-19
 */
@Service
public class VesselTotalServiceImpl extends ServiceImpl<VesselTotalMapper, VesselTotal> implements VesselTotalService {
    @Autowired
    private VesselTotalMapper vesselTotalMapper;


    @Override
    public IPage<VesselTotal> getUserList(IPage<VesselTotal> page, String search) {
        QueryWrapper<VesselTotal> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("name", search);
        }
        return vesselTotalMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<VesselTotal> page) {
        return vesselTotalMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<VesselTotal> page, Wrapper wrapper) {
        return vesselTotalMapper.pageCC(page, wrapper);
    }



}
