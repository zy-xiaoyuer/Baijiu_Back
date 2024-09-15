package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Admin;
import com.baijiu.Baijiu_Back.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String adminPage() {
        return "Admin Page";
    }
    @CrossOrigin
    @PostMapping("/api/getUserPassword")
    @ResponseBody
    public Result adminLogin(@RequestBody Admin admin) {
        int count = adminService.getAdminByMassage(admin.getUsername(), admin.getPassword());
        if (count != 0) {
            return Result.success();
            
        } else {
            return Result.fail();
        }

    }
    @ResponseBody
    @PostMapping("/api/delete-account")
    public Result deleteAccount(@RequestBody Admin admin) {
        boolean isDeleted = adminService.deleteAccount(admin.getId());
        if (isDeleted) {

            return Result.success("账号已注销");
        } else {
            return Result.fail();
        }
    }

}
