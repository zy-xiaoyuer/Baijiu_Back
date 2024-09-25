package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.service.VesselService;
import com.baijiu.Baijiu_Back.service.VesselTotalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ltt
 * @since 2024-08-26
 */
@RestController
@CrossOrigin
@RequestMapping("/vessel")
public class VesselController {
    @Autowired
    private VesselService vesselService;

    @GetMapping("/api/get-image/{id}")
    public void getImage(@PathVariable Integer id, HttpServletResponse response) {
        Vessel vessel = vesselService.getById(id);
        if (vessel != null && vessel.getPicture() != null) {

            response.setContentType("image/jpeg");
            try {
                response.getOutputStream().write(vessel.getPicture());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    //列表
    @GetMapping("/api/list")
    public List<Vessel> list() {
        return vesselService.list(); // 直接返回用户列表
    }


    //新增
    @PostMapping("/api/save")
    public Result save(@RequestBody Vessel vessel){
        //调用service实现新增用户
        return vesselService.save(vessel)?Result.success():Result.fail();

    }

    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestBody Vessel vessel){

        return vesselService.updateById(vessel) ? Result.success() : Result.fail();
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return vesselService.removeById(id) ? Result.success() : Result.fail();
    }

    // 分页查询（模糊匹配用户名）
    @PostMapping("/api/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String search = (String) params.get("search");

        Page<Vessel> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<Vessel> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(search)) {
            queryWrapper.and(w ->
                    w.eq(Vessel::getAge, search).or().eq(Vessel::getNow, search)
            );
        }

        IPage<Vessel> result = vesselService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
    //统计每个朝代的酒画数量
    @GetMapping("/api/countByDynasty")
    public Result countByDynasty() {
        List<Vessel> vessels = vesselService.list();
        Map<String, Long> dynastyCount = new HashMap<>();
        for (Vessel vessel : vessels) {
            String dynasty = vessel.getAge();
            dynastyCount.put(dynasty, dynastyCount.getOrDefault(dynasty, 0L) + 1);
        }
        return Result.success(dynastyCount);
    }

}
