package com.baijiu.Baijiu_Back.controller;

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
    public String adminLogin(@RequestBody Admin admin) {

        System.out.println("User : " + admin);
        String str = "error";
        int count =adminService.getAdminByMassage(admin.getUsername(), admin.getPassword());
        if (count != 0) {
            str = "ok";
        }
        return str;
    }

}
