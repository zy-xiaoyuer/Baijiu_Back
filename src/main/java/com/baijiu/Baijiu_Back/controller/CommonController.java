package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.service.VesselTotalService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

//文件上传，本地下载

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private VesselTotalService vesselTotalService;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        try {
            byte[] fileBytes = file.getBytes();
            VesselTotal vesselTotal = new VesselTotal();
            vesselTotal.setPicture(fileBytes);
            vesselTotalService.save(vesselTotal); // 假设你有一个保存方法
            return Result.success(vesselTotal.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail();
        }
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response) {

        VesselTotal vesselTotal = vesselTotalService.getById(id);
        if (vesselTotal != null && vesselTotal.getPicture() != null) {
            response.setContentType("image/jpeg");
            try {
                response.getOutputStream().write(vesselTotal.getPicture());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
