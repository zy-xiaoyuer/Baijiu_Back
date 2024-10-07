package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.mapper.PoemsbydynastyMapper;
import com.baijiu.Baijiu_Back.mapper.UsersMapper;
import com.baijiu.Baijiu_Back.service.PoemsbydynastyService;
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
public class PoemsbydynastyServiceImpl extends ServiceImpl<PoemsbydynastyMapper, Poemsbydynasty> implements PoemsbydynastyService {
    @Autowired
    private PoemsbydynastyMapper poemsbydynastyMapper;


    @Override
    public IPage<Poemsbydynasty> getUserList(IPage<Poemsbydynasty> page, String search) {
        QueryWrapper<Poemsbydynasty> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("poetry", search);
        }
        return poemsbydynastyMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<Poemsbydynasty> page) {
        return poemsbydynastyMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Poemsbydynasty> page, Wrapper wrapper) {
        return poemsbydynastyMapper.pageCC(page, wrapper);
    }
    @Override
    public Long countAll() {
        return poemsbydynastyMapper.selectCount(null);
    }

}
