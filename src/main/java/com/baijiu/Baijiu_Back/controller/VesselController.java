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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @Value("${file.upload-dir}")
    private String uploadDir;

//    @GetMapping("/api/get-image/{id}")
//    public void getImage(@PathVariable Integer id, HttpServletResponse response) {
//        Vessel vessel = vesselService.getById(id);
//        if (vessel != null && vessel.getPicture() != null) {
//
//            response.setContentType("image/jpeg");
//            try {
//                response.getOutputStream().write(vessel.getPicture());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
    //列表
    @GetMapping("/api/list")
    public List<Vessel> list() {
        return vesselService.list(); // 直接返回用户列表
    }


    //新增
    @PostMapping("/api/save")
    public Result save(@RequestBody Vessel vessel){
        //调用service实现新增用户
        return vesselService.save(vessel)?Result.success():Result.fail("保存失败");

    }

    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestPart("id") Integer id,
                      @RequestPart("age") String age,
                      @RequestPart("now") String now,
                      @RequestPart("picture") MultipartFile pictureFile) {
        try {
            Vessel vessel = vesselService.getById(id);
            if (vessel == null) {
                return Result.fail("保存失败");
            }

            // 如果用户上传了新图片，则处理新图片
            if (!pictureFile.isEmpty()) {
                String fileName = pictureFile.getOriginalFilename();
                byte[] bytes = pictureFile.getBytes();
                Path path = Paths.get(uploadDir, fileName);
                Files.write(path, bytes);
                vessel.setPicture("src\\\\main\\\\resources\\\\upload\\\\" + fileName);
            }

            // 更新数据库中的其他信息
            vessel.setAge(age);
            vessel.setNow(now);
            boolean isUpdated = vesselService.updateById(vessel);

            if (isUpdated) {
                return Result.success();
            } else {
                return Result.fail("保存失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("保存失败");
        }
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return vesselService.removeById(id) ? Result.success() : Result.fail("保存失败");
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
    @GetMapping("/api/getPoemById")
    public Result getPoemById(@RequestParam("id") Integer id) {
        Vessel vessel = vesselService.getById(id);
        if (vessel == null) {
            return Result.fail("保存失败");
        }
        return Result.success(vessel);
    }
    //不传id就打印全部结果，传id打印筛选后的结果
    @GetMapping("/api/getPoemByIdsearch")
    public Result getPoemByIdsearch(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            Vessel vessel = vesselService.getById(id);
            if (vessel == null) {
                return Result.fail("保存失败");
            }
            return Result.success(vessel);
        } else {
            List<Vessel> vessels =vesselService.list();
            return Result.success(vessels);
        }
    }
    @PostMapping("/api/saveall")
    public Result save(@RequestPart("age") String age,
                       @RequestPart("now") String now,
                       @RequestPart("picture") MultipartFile pictureFile) {
        try {
            if (pictureFile.isEmpty()) {
                return Result.fail("保存失败");
            }

            String fileName = pictureFile.getOriginalFilename();
            byte[] bytes = pictureFile.getBytes();
            Path path = Paths.get(uploadDir,  fileName);

            Files.write(path, bytes);

            // 保存文件名到数据库
            Vessel vessel = new Vessel();
            vessel.setAge(age);
            vessel.setNow(now);
            vessel.setPicture("src\\\\main\\\\resources\\\\upload\\\\" + fileName);
            vesselService.save(vessel);
            return Result.success("/" + fileName); // 返回文件名
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("保存失败");
        }
    }
}
