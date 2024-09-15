package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.service.VesselTotalService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private VesselTotalService vesselTotalService;
    @PostMapping("/save")
    public ResponseEntity<?> save(VesselTotal vesselTotal,@RequestParam( value="picture",required=false)MultipartFile file)throws Exception {

        if (file != null && !file.isEmpty()) {
            byte[] fileBytes = file.getBytes();
            vesselTotal.setPicture(fileBytes);
            vesselTotalService.save(vesselTotal);
            // 将图片转换为 Base64 编码
            String base64Image = Base64.getEncoder().encodeToString(fileBytes);
            return ResponseEntity.ok(base64Image);
        } else {
            return  ResponseEntity.badRequest().body("文件不能为空");
        }
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable("id") Integer id) {
        VesselTotal vesselTotal = vesselTotalService.getById(id);
            String baseimg = Base64.getEncoder().encodeToString(vesselTotal.getPicture());
            System.out.println(baseimg);

            Map<Integer, String> res = new HashMap<>();
            res.put(id,baseimg);

        return ResponseEntity.ok()
                .body(res);
    }
}