package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @CrossOrigin
    @RequestMapping("/api/loadAll")
    @ResponseBody
    public Result<List<Users>> loadAll() {
        List<Users> list = usersService.list();
        return Result.of(list, true,"操作成功");
    }

    @RequestMapping("/api/loadAllByPage")
    public Result loadAllByPage(@RequestParam("pageNum")Integer pageNum,
                                @RequestParam("pageSize")Integer pageSize,
                                String search){
        //通过分页的插件【拦截器】
        Page<Users> page = new Page<>(pageNum, pageSize);
        IPage<Users> resultPage = usersService.getUserList(page, search);
        return Result.ofPage(resultPage.getRecords(), resultPage.getTotal(), true, "操作成功");
    }

    @RequestMapping("/api/save")
    public Result save(@RequestBody Users users){
        //调用service实现新增用户
        boolean save = usersService.save(users);
        return Result.of(save);
    }

}

