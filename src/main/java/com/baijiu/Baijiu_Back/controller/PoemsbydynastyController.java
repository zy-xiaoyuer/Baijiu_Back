package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.service.PoemsbydynastyService;
import com.baijiu.Baijiu_Back.service.PoemsbylocationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/poemsbydynasty")
public class PoemsbydynastyController {
    @Autowired
    private PoemsbydynastyService poemsbydynastyService;

    //列表
    @GetMapping("/api/list")
    public List<Poemsbydynasty> list() {
        return poemsbydynastyService.list(); // 直接返回用户列表
    }
    @GetMapping("/api/findByTitle")
    public Result findByUsername(@RequestParam String title)
    {
        List list=poemsbydynastyService.lambdaQuery().eq(Poemsbydynasty::getTitle,title).list();
        return list.size()>0?Result.success():Result.fail("酒诗不存在");
    }
    //新增
    @PostMapping("/api/save")
    public Result save(@RequestBody Poemsbydynasty poemsbydynasty){
        //调用service实现新增用户
        return poemsbydynastyService.save(poemsbydynasty)?Result.success():Result.fail("保存失败");

    }

    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestBody Poemsbydynasty poemsbydynasty){

        return poemsbydynastyService.updateById(poemsbydynasty) ? Result.success() : Result.fail("保存失败");
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return poemsbydynastyService.removeById(id) ? Result.success() : Result.fail("保存失败");
    }

    // 分页查询（按作者或朝代精确查询）
    @PostMapping("/api/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String search = (String) params.get("search");

        Page<Poemsbydynasty> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<Poemsbydynasty> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.hasText(search)) {
            queryWrapper.and(w ->
                    w.eq(Poemsbydynasty::getAuthor, search).or().eq(Poemsbydynasty::getDynasty, search)
            );
        }

        IPage<Poemsbydynasty> result = poemsbydynastyService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
    // 根据ID获取单条诗歌数据
    @GetMapping("/api/getPoemById")
    public Result getPoemById(@RequestParam("id") Integer id) {
        Poemsbydynasty poem = poemsbydynastyService.getById(id);
        if (poem == null) {
            return Result.fail("保存失败");
        }
        return Result.success(poem);
    }
    //不传id就打印全部结果，传id打印筛选后的结果
    @GetMapping("/api/getPoemByIdsearch")
    public Result getPoemByIdsearch(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            Poemsbydynasty poemsbydynasty = poemsbydynastyService.getById(id);
            if (poemsbydynasty == null) {
                return Result.fail("保存失败");
            }
            return Result.success(poemsbydynasty);
        } else {
            List<Poemsbydynasty> poemsbydynasties = poemsbydynastyService.list();
            return Result.success(poemsbydynasties);
        }
    }
    //返回朝代和作者（用于柱状图）
    @GetMapping("/api/getPoemInfoById")
    public Result getPoemInfoById(@RequestParam("id") Integer id) {
        Poemsbydynasty poem = poemsbydynastyService.getById(id);
        if (poem == null) {
            return Result.fail("保存失败");
        }
        Map<String, Object> poemInfo = new HashMap<>();
        poemInfo.put("id", poem.getId());
        poemInfo.put("dynasty", poem.getDynasty());
        poemInfo.put("author", poem.getAuthor());
        return Result.success(poemInfo);
    }

}
