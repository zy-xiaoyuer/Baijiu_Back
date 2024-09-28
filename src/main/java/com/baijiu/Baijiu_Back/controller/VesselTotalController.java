package com.baijiu.Baijiu_Back.controller;
import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Vessel;
import com.baijiu.Baijiu_Back.entity.VesselTotal;

import com.baijiu.Baijiu_Back.service.VesselTotalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
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



    //    @GetMapping("/api/get-image/{id}")
//    public void getImage(@PathVariable Integer id, HttpServletResponse response) {
//        VesselTotal vesselTotal = vesselTotalService.getById(id);
//        if (vesselTotal != null && vesselTotal.getPicture() != null) {
//            response.setContentType("image/jpeg");
//            try {
//                response.getOutputStream().write(vesselTotal.getPicture());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
@GetMapping("/api/get-image/{id}")
public void getImage(@PathVariable Integer id, HttpServletResponse response) {
    VesselTotal vesselTotal = vesselTotalService.getById(id);
//    if (vesselTotal != null && vesselTotal.getPicture() != null) {
//        response.setContentType("image/jpeg");
//        try {
//            // 使用URLEncoder对文件名进行URL编码
//            //String encodedFileName = URLEncoder.encode(vesselTotal.getName(), "UTF-8");
//            //response.setHeader("Content-Disposition", "inline; filename=\"" + encodedFileName + "\"");
//            //response.getOutputStream().write(vesselTotal.getPicture());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    } else {
//        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//    }
}
    // 添加图片
    @ResponseBody
    @PostMapping("/api/add")
//    public Result addImage(@RequestParam("file") MultipartFile file,
//                                           @RequestParam("name") String name,
//                                           @RequestParam("discription") String discription) {
//
//        if (file.isEmpty()) {
//            return Result.fail();
//        }
//
//        try {
//            VesselTotal vesselTotal = new VesselTotal();
//            vesselTotal.setName(name);
//            vesselTotal.setDiscription(discription);
//            vesselTotal.setPicture(file.getBytes());
//
//            boolean saved = vesselTotalService.save(vesselTotal);
//            if (saved) {
//                return Result.success(vesselTotal.getId()); // 返回保存的实体ID
//            } else {
//                return Result.fail();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Result.fail();
//        }
//    }
    public Object upload(@RequestParam("file") MultipartFile file){
    String fileName= UUID.randomUUID()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    File savefile=new File("E:\\Baijiu_Back\\src\\resources\\upload"+fileName);
    try {
        file.transferTo(savefile);
        return "上传成功";
    }catch (IOException e){
        e.printStackTrace();
    }
    return "上传失败";
    }





// 更新图片
@PostMapping("/api/update/{id}")
public ResponseEntity<Result> updateImage(@PathVariable Integer id, @RequestParam("file") MultipartFile file,
                                          @RequestParam("name") String name,
                                          @RequestParam("discription") String discription) throws IOException {
    if (file.isEmpty()) {
        return ResponseEntity.badRequest().body(Result.fail());
    }

    VesselTotal vesselTotal = vesselTotalService.getById(id);
    if (vesselTotal != null) {
        vesselTotal.setName(name);
        vesselTotal.setDiscription(discription);
        //vesselTotal.setPicture(file.getBytes());

        if (vesselTotalService.updateById(vesselTotal)) {
            String imageUrl = "http://localhost:9000/vesselTotal/api/get-image/" + id;
            return ResponseEntity.ok(Result.success(imageUrl));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.fail());
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Result.fail());
    }
}

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
            vesselTotal.setPicture("src\\main\\resources\\upload" + fileName); // 注意路径前缀

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
    public Result mod(@RequestBody VesselTotal vesselTotal){

        return vesselTotalService.updateById(vesselTotal) ? Result.success() : Result.fail();
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
            queryWrapper.like(VesselTotal::getName, name);
            System.out.println("Applying LIKE condition for username: " + name);
        }
        System.out.println(queryWrapper.toString());
        IPage<VesselTotal> result = vesselTotalService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
    @GetMapping("/api/getPoemById")
    public Result getPoemById(@RequestParam("id") Integer id) {
        VesselTotal vessel = vesselTotalService.getById(id);
        if (vessel == null) {
            return Result.fail();
        }
        return Result.success(vessel);
    }

}
