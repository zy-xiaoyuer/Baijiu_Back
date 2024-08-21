package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Admin;
import com.baijiu.Baijiu_Back.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        System.out.println("User : " + admin);
        String str = "error";
        int count = adminService.getAdminByMassage(admin.getUsername(), admin.getPassword());

        if (count != 0) {
            return Result.success(); // 登录成功，返回成功结果
        } else {
            return Result.fail(); // 登录失败，返回失败结果和错误信息
        }


    }
}
