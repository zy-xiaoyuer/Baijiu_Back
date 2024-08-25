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
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @CrossOrigin
    @PostMapping("/api/getUserPassword") // @RequestMapping注解创建接口
    @ResponseBody
    public Result adminLogin(@RequestBody Admin admin) {
        int count = adminService.getAdminByMassage(admin.getUsername(), admin.getPassword());
        if (count != 0) {
            return Result.success(); // 登录成功，返回成功结果
            
        } else {
            return Result.fail(); // 登录失败，返回失败结果和错误信息
        }

    }
    @ResponseBody
    @PostMapping("/api/delete-account")
    public Result deleteAccount(@RequestBody Admin admin) {
        boolean isDeleted = adminService.deleteAccount(admin.getId()); // 假设admin.getId()可以获取到用户ID
        if (isDeleted) {

            return Result.success("账号已注销");
        } else {
            return Result.fail();
        }
    }

}
