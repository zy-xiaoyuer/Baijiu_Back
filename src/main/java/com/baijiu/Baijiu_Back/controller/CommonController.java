package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.service.VesselTotalService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private VesselTotalService vesselTotalService;
   // @Value("${file-storage.base-path}") String basePath;
    @PostMapping("/save")
//    public ResponseEntity<?> save(VesselTotal vesselTotal,@RequestParam( value="picture",required=false)String filename)throws Exception {
//
//        if (filename.isEmpty()) {
//            return ResponseEntity.badRequest().body("文件不能为空");
//        }
//       // String fileName = file.getOriginalFilename();
//
//        //String filePath = basePath + filename;  // 组合成完整的文件路径
//
//       // File dest = new File(filePath);
//        //if (!dest.getParentFile().exists() && !dest.getParentFile().mkdirs()) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建目录失败");
//        }
//
//        File destFile = new File(dest, filename);
//
//        try {
//            //file.transferTo(destFile);
//            vesselTotal.setPicture(filePath);
//            vesselTotalService.save(vesselTotal);
//
//            // 返回响应时设置 Content-Type 和编码
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//            return new ResponseEntity<>(filePath, headers, HttpStatus.OK);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败");
//        }
//    }


    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable("id") Integer id) {
        VesselTotal vesselTotal = vesselTotalService.getById(id);
            String imgurl = vesselTotal.getPicture();
//            System.out.println(baseimg);

        Map<Integer, String> res = new HashMap<>();
        res.put(id,imgurl);
       // 返回响应时设置 Content-Type 和编码
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return  new ResponseEntity<>(
                res, headers, HttpStatus.OK);


    }
}