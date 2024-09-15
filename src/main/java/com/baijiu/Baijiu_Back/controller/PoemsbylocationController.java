package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.service.PoemsbylocationService;
import com.baijiu.Baijiu_Back.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
@RestController
@CrossOrigin
@RequestMapping("/poemsbylocation")
public class PoemsbylocationController {

    @Autowired
    private PoemsbylocationService poemsbylocationService;

    //列表
    @GetMapping("/api/list")
    public List<Poemsbylocation> list() {
        return poemsbylocationService.list(); // 直接返回用户列表
    }
    @GetMapping("/api/findByPoetry")
    public Result findByUsername(@RequestParam String poetry)
    {
        List list=poemsbylocationService.lambdaQuery().eq(Poemsbylocation::getPoetry,poetry).list();
        return list.size()>0?Result.success():Result.fail();
    }
    //新增
    @PostMapping("/api/save")
    public Result save(@RequestBody Poemsbylocation poemsbylocation){
        //调用service实现新增用户
        return poemsbylocationService.save(poemsbylocation)?Result.success():Result.fail();

    }

    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestBody Poemsbylocation poemsbylocation){

        return poemsbylocationService.updateById(poemsbylocation) ? Result.success() : Result.fail();
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return poemsbylocationService.removeById(id) ? Result.success() : Result.fail();
    }

    // 分页查询（按作者或朝代精确查询）
    @PostMapping("/api/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String search = (String) params.get("search");

        Page<Poemsbylocation> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<Poemsbylocation> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(search)) {
            queryWrapper.and(w ->
                    w.eq(Poemsbylocation::getAuthor, search).or().eq(Poemsbylocation::getDynasty, search)
            );
        }
        //System.out.println(queryWrapper.toString());
        IPage<Poemsbylocation> result = poemsbylocationService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }

}
