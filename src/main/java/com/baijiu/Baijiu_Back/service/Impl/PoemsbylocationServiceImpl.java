package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.mapper.PoemsbylocationMapper;
import com.baijiu.Baijiu_Back.mapper.UsersMapper;
import com.baijiu.Baijiu_Back.service.PoemsbylocationService;
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
public class PoemsbylocationServiceImpl extends ServiceImpl<PoemsbylocationMapper, Poemsbylocation> implements PoemsbylocationService {

    @Autowired
    private PoemsbylocationMapper poemsbylocationMapper;


    @Override
    public IPage<Poemsbylocation> getUserList(IPage<Poemsbylocation> page, String search) {
        QueryWrapper<Poemsbylocation> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("poetry", search);
        }
        return poemsbylocationMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<Poemsbylocation> page) {
        return poemsbylocationMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Poemsbylocation> page, Wrapper wrapper) {
        return poemsbylocationMapper.pageCC(page, wrapper);
    }

}
