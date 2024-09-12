package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baijiu.Baijiu_Back.service.VesselTotalService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private VesselTotalService vesselTotalService;
    @PostMapping("/save")
    public Result save(@ModelAttribute VesselTotal vesselTotal, @RequestParam( value="picture",required=false)MultipartFile file)throws Exception{

        if ((file != null && !file.isEmpty())) {
            vesselTotal.setPicture(file.getBytes());
            System.out.println(file.getBytes());
        }
        vesselTotalService.save(vesselTotal);

        return Result.success(vesselTotal);
    }
    @PostMapping("/add")
    public Result save(@ModelAttribute VesselTotal vesselTotal, @RequestParam( value="picture",required=false)MultipartFile file,@RequestParam( value="name",required=false)String name,@RequestParam( value="discription",required=false)String discription)throws Exception{

        if ((file != null && !file.isEmpty())) {
            vesselTotal.setPicture(file.getBytes());
            vesselTotal.setName(name);
            vesselTotal.setDiscription(discription);
        }
        vesselTotalService.save(vesselTotal);

        return Result.success(vesselTotal);
    }

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
    public ResponseEntity<String> download(@PathVariable("id") Integer id) {
        VesselTotal vesselTotal = vesselTotalService.getById(id);
//        if (vesselTotal != null && vesselTotal.getPicture() != null) {
//
//            response.setContentType("image/jpeg");
//            String fileName = URLEncoder.encode(vesselTotal.getName() + ".jpg");
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//            try (ServletOutputStream outputStream = response.getOutputStream()) {
//                outputStream.write(vesselTotal.getPicture());
//            } catch (IOException e) {
//                e.printStackTrace();
//                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            }
//        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }

            String baseimg = Base64.getEncoder().encodeToString(vesselTotal.getPicture());
            System.out.println(baseimg);

            Map<Integer, String> res = new HashMap<>();
            res.put(id,baseimg);

        return ResponseEntity.ok()
                .body(baseimg);
    }
}