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
    @GetMapping("/api/total")
    public Long  total() {
        return poemsbydynastyService.countAll();
    }

    //新增
    @PostMapping("/api/checkTitle")
    public Result checkTitle(@RequestBody Map<String, String> params) {
        String title = params.get("title");
        if (poemsbydynastyService.lambdaQuery().eq(Poemsbydynasty::getTitle, title).list().size() > 0) {
            return Result.fail("酒诗已经存在");
        }
        return Result.success();
    }
    @PostMapping("/api/save")
    public Result save(@RequestBody Poemsbydynasty poemsbydynasty){
        //调用service实现新增用户
        return poemsbydynastyService.save(poemsbydynasty)?Result.success():Result.fail("保存失败");

    }

    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestBody Poemsbydynasty poemsbydynasty){

        return poemsbydynastyService.updateById(poemsbydynasty) ? Result.success() : Result.fail("修改失败");
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return poemsbydynastyService.removeById(id) ? Result.success() : Result.fail("删除失败");
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
        System.out.print(queryWrapper.getSqlSelect());
        IPage<Poemsbydynasty> result = poemsbydynastyService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
    // 根据ID获取单条诗歌数据
    @GetMapping("/api/getPoemById")
    public Result getPoemById(@RequestParam("id") Integer id) {
        Poemsbydynasty poem = poemsbydynastyService.getById(id);
        if (poem == null) {
            return Result.fail("酒诗不存在");
        }
        return Result.success(poem);
    }
    @GetMapping("/api/getPoemStatistics")
    public Result getPoemStatistics() {
            StringBuilder result = new StringBuilder();

            // 获取朝代统计
            Map<String, Integer> dynastyStats = poemsbydynastyService.getDynastyStatistics();
            dynastyStats.forEach((dynasty, count) -> {
                result.append("Dynasty: ").append(dynasty).append(", Count: ").append(count).append("; ");
            });

            // 获取作者统计
            Map<String, Integer> authorStats = poemsbydynastyService.getAuthorStatistics();
            authorStats.forEach((author, count) -> {
                result.append("Author: ").append(author).append(", Count: ").append(count).append("; ");
            });

            return Result.success(result.toString());
    }
    //不传id就打印全部结果，传id打印筛选后的结果
    @GetMapping("/api/getPoemByIdsearch")
    public Result getPoemByIdsearch(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            Poemsbydynasty poemsbydynasty = poemsbydynastyService.getById(id);
            if (poemsbydynasty == null) {
                return Result.fail("酒诗不存在");
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
            return Result.fail("酒诗不存在");
        }
        Map<String, Object> poemInfo = new HashMap<>();
        poemInfo.put("id", poem.getId());
        poemInfo.put("dynasty", poem.getDynasty());
        poemInfo.put("author", poem.getAuthor());
        return Result.success(poemInfo);
    }

}
