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
    @PostMapping("/api/getUserPassword") // @RequestMapping注解创建接口
    @ResponseBody
    public String userLogin(@RequestBody User user) { // @RequestBody注解方便找到user实体

        System.out.println("User : " + user);
        String str = "error";
        int count = userService.getUserByMassage(user.getName(), user.getPassword());
        if (count != 0) {
            str = "ok";
        }
        return str;
    }

}
