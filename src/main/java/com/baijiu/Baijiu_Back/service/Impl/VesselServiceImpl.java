package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.mapper.VesselMapper;
import com.baijiu.Baijiu_Back.mapper.VesselTotalMapper;
import com.baijiu.Baijiu_Back.service.VesselService;
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
 * @since 2024-08-26
 */
@Service
public class VesselServiceImpl extends ServiceImpl<VesselMapper, Vessel> implements VesselService {
    @Autowired
    private VesselMapper vesselMapper;


    @Override
    public IPage<Vessel> getUserList(IPage<Vessel> page, String search) {
        QueryWrapper<Vessel> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("name", search);
        }
        return vesselMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<Vessel> page) {
        return vesselMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Vessel> page, Wrapper wrapper) {
        return vesselMapper.pageCC(page, wrapper);
    }
    @Override
    public Long countAll() {
        return vesselMapper.selectCount(null);
    }
}
