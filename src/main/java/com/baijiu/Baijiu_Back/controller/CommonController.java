package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.service.VesselTotalService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;



@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private VesselTotalService vesselTotalService;
    @PostMapping("/upload")
    public Result upload( @RequestParam("picture")MultipartFile file,
                         @RequestParam("name")String name,
                         @RequestParam("discription")String  discription
    ) {
        try {
            byte[] fileBytes = file.getBytes();
            VesselTotal vesselTotal = new VesselTotal();
            vesselTotal.setPicture(fileBytes);
            vesselTotal.setName(name);
            vesselTotal.setDiscription(discription);
            vesselTotalService.save(vesselTotal);
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
            String fileName = URLEncoder.encode(vesselTotal.getName() + ".jpg");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                outputStream.write(vesselTotal.getPicture());
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
