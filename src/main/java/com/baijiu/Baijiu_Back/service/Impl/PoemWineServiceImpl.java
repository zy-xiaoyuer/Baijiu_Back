package com.baijiu.Baijiu_Back.service.Impl;

import com.baijiu.Baijiu_Back.entity.PoemWine;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.mapper.PoemWineMapper;
import com.baijiu.Baijiu_Back.mapper.UsersMapper;
import com.baijiu.Baijiu_Back.service.PoemWineService;
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
 * @since 2024-08-18
 */
@Service
public class PoemWineServiceImpl extends ServiceImpl<PoemWineMapper, PoemWine> implements PoemWineService {
    @Autowired
    private PoemWineMapper poemWineMapper;


    @Override
    public IPage<PoemWine> getUserList(IPage<PoemWine> page, String search) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.like("username", search);
        }
        return poemWineMapper.selectPage(page, queryWrapper.getSqlSegment() );
    }

    @Override
    public IPage pageC(IPage<PoemWine> page) {
        return poemWineMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<PoemWine> page, Wrapper wrapper) {
        return poemWineMapper.pageCC(page, wrapper);
    }
}
