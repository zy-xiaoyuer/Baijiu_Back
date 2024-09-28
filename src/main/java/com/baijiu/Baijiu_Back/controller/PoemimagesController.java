package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Poemimages;
import com.baijiu.Baijiu_Back.entity.Poemsbydynasty;
import com.baijiu.Baijiu_Back.entity.Poemsbylocation;
import com.baijiu.Baijiu_Back.entity.Vessel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import com.baijiu.Baijiu_Back.service.PoemimagesService;
import com.baijiu.Baijiu_Back.service.VesselTotalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
 * @since 2024-08-21
 */
@RestController
@CrossOrigin
@RequestMapping("/poemimages")
public class PoemimagesController {
    @Autowired
    private PoemimagesService poemimagesService;
    @Value("${file.upload-dir}")
    private String uploadDir;


    @GetMapping("/api/findByPictureName")
    public Result findByUsername(@RequestParam String imagename)
    {
        List list=poemimagesService.lambdaQuery().eq(Poemimages::getImagename,imagename).list();
        return list.size()>0?Result.success():Result.fail();
    }
//    @GetMapping("/api/get-image/{id}")
//    public void getImage(@PathVariable Integer id, HttpServletResponse response) {
//        Poemimages poemimages = poemimagesService.getById(id);
//        if (poemimages != null && poemimages.getImage() != null) {
//            // 设置响应的类型为图片类型
//            response.setContentType("image/jpeg");
//            // 将图片的字节数据写入响应输出流
//            try {
//                response.getOutputStream().write(poemimages.getImage());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            // 如果没有找到图片，返回错误或者默认图片
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
    //列表
    @GetMapping("/api/list")
    public List<Poemimages> list() {
        return poemimagesService.list(); // 直接返回用户列表
    }


    //新增
    @PostMapping("/api/save")
    public Result save(@RequestBody Poemimages poemimages){
        //调用service实现新增用户
        return poemimagesService.save(poemimages)?Result.success():Result.fail();

    }

    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestBody Poemimages poemimages){

        return poemimagesService.updateById(poemimages) ? Result.success() : Result.fail();
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
        return poemimagesService.removeById(id) ? Result.success() : Result.fail();
    }

    // 分页查询（模糊匹配用户名）
    @PostMapping("/api/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String imagename = (String) params.get("imagename");

        Page<Poemimages> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<Poemimages> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(imagename)) { // 使用 StringUtils 来检查字符串是否不为空或全为空格
            queryWrapper.like(Poemimages::getImagename, imagename);
            System.out.println("Applying LIKE condition for username: " + imagename); // 调试输出
        }
        System.out.println(queryWrapper.toString()); // 打印出queryWrapper的内容，以便调试
        IPage<Poemimages> result = poemimagesService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
    @GetMapping("/api/getPoemById")
    public Result getPoemById(@RequestParam("id") Integer id) {
        Poemimages poem = poemimagesService.getById(id);
        if (poem == null) {
            return Result.fail();
        }
        return Result.success(poem);
    }
    //统计每个朝代的酒画数量
    @GetMapping("/api/countByDynasty")
    public Result countByDynasty() {
        List<Poemimages> images = poemimagesService.list();
        Map<String, Long> dynastyCount = new HashMap<>();
        for (Poemimages image : images) {
            String dynasty = image.getDynasty();
            dynastyCount.put(dynasty, dynastyCount.getOrDefault(dynasty, 0L) + 1);
        }
        return Result.success(dynastyCount);
    }
    @PostMapping("/api/saveall")
    public Result save(@RequestPart("imagename") String imagename,
                       @RequestPart("image") MultipartFile pictureFile) {
        try {
            if (pictureFile.isEmpty()) {
                return Result.fail();
            }

            String fileName = pictureFile.getOriginalFilename();
            byte[] bytes = pictureFile.getBytes();
            Path path = Paths.get(uploadDir,  fileName);

            Files.write(path, bytes);

            // 保存文件名到数据库
            Poemimages poemimages = new Poemimages();
            poemimages.setImagename(imagename);

            poemimages.setImage("src\\main\\resources\\upload" + fileName); // 注意路径前缀

            poemimagesService.save(poemimages);
            return Result.success("/" + fileName); // 返回文件名
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail();
        }
    }
}
