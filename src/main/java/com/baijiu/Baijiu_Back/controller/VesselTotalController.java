package com.baijiu.Baijiu_Back.controller;
import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.entity.VesselTotal;

import com.baijiu.Baijiu_Back.service.VesselTotalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.UUID;

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
@RequestMapping("/vesselTotal")
public class VesselTotalController {

    @Autowired
    private VesselTotalService vesselTotalService;
    @Value("${file.upload-dir}")
    private String uploadDir;


    @PostMapping("/api/saveall")
    public Result save(@RequestPart("name") String name,
                       @RequestPart("discription") String discription,
                       @RequestPart("picture") MultipartFile pictureFile) {
        try {
            if (pictureFile.isEmpty()) {
                return Result.fail();
            }

            String fileName = pictureFile.getOriginalFilename();
            byte[] bytes = pictureFile.getBytes();
            Path path = Paths.get(uploadDir,  fileName);

            Files.write(path, bytes);

            // 保存文件名到数据库
            VesselTotal vesselTotal = new VesselTotal();
            vesselTotal.setName(name);
            vesselTotal.setDiscription(discription);
            vesselTotal.setPicture("src\\\\main\\\\resources\\\\upload\\\\" + fileName); // 注意路径前缀

            vesselTotalService.save(vesselTotal);
            return Result.success("/" + fileName); // 返回文件名
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail();
        }
    }
    //列表
    @GetMapping("/api/list")
    public List<VesselTotal> list() {
        return vesselTotalService.list();
    }

    @GetMapping("/api/findByname")
    public Result findByUsername(@RequestParam String name)
    {
        List list=vesselTotalService.lambdaQuery().eq(VesselTotal::getName,name).list();
        return list.size()>0?Result.success():Result.fail();
    }

    //新增
    @PostMapping("/api/save")
    public Result save(@RequestBody VesselTotal vesselTotal){
        return vesselTotalService.save(vesselTotal)?Result.success():Result.fail();
    }

    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestPart("id") Integer id,
                      @RequestPart("name") String name,
                      @RequestPart("discription") String discription,
                      @RequestPart("picture") MultipartFile pictureFile) {
        try {
            VesselTotal vesselTotal = vesselTotalService.getById(id);
            if (vesselTotal == null) {
                return Result.fail();
            }

            // 如果用户上传了新图片，则处理新图片
            if (!pictureFile.isEmpty()) {
                String fileName = pictureFile.getOriginalFilename();
                byte[] bytes = pictureFile.getBytes();
                Path path = Paths.get(uploadDir, fileName);
                Files.write(path, bytes);
                vesselTotal.setPicture("src\\\\main\\\\resources\\\\upload\\\\" + fileName);
            }

            // 更新数据库中的其他信息
            vesselTotal.setName(name);
            vesselTotal.setDiscription(discription);
            boolean isUpdated = vesselTotalService.updateById(vesselTotal);

            if (isUpdated) {
                return Result.success();
            } else {
                return Result.fail();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail();
        }
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return vesselTotalService.removeById(id) ? Result.success() : Result.fail();
    }

    // 分页查询（模糊匹配用户名）
    @PostMapping("/api/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String name = (String) params.get("name");

        Page<VesselTotal> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<VesselTotal> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            queryWrapper.eq(VesselTotal::getName, name);
            System.out.println("Applying LIKE condition for username: " + name);
        }
        System.out.println(queryWrapper.toString());
        IPage<VesselTotal> result = vesselTotalService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
    //传id
    @GetMapping("/api/getPoemById")
    public Result getPoemById(@RequestParam("id") Integer id) {
        VesselTotal vessel = vesselTotalService.getById(id);
        if (vessel == null) {
            return Result.fail();
        }
        return Result.success(vessel);
    }
//不传id就打印全部结果，传id打印筛选后的结果
    @GetMapping("/api/getPoemByIdsearch")
    public Result getPoemByIdsearch(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null) {
            VesselTotal vessel = vesselTotalService.getById(id);
            if (vessel == null) {
                return Result.fail();
            }
            return Result.success(vessel);
        } else {
            List<VesselTotal> vessels = vesselTotalService.list();
            return Result.success(vessels);
        }
    }

}
