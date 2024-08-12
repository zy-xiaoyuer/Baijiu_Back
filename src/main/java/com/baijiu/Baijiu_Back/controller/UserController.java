package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.entity.User;
import com.baijiu.Baijiu_Back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.xml.transform.Result;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ltt
 * @since 2024-08-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "/login")
    @ResponseBody
    public ApiResponse login(@RequestBody User requestUser) {
        // 获取用户名和密码
        String username = requestUser.getName();
        String password = requestUser.getPassword();
        // 对html标签进行转义，防止XSS攻击
        username = HtmlUtils.htmlEscape(username);
        // 调用用户服务对象的登录方法
        User user = userService.login(username, password);
        // 判断登录是否成功
        // 判断登录是否成功
        if (user != null) {
            return new ApiResponse(200, "登录成功", user);
        } else {
            System.out.println("用户名或密码有误！");
            return new ApiResponse(400, "用户名或密码有误！");
        }
    }
}
