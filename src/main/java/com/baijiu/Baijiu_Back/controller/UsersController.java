package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    //列表
    @GetMapping("/api/list")
    public List<Users> list() {
        return usersService.list(); // 直接返回用户列表
    }
    @GetMapping("/api/findByUsername")
    public Result findByUsername(@RequestParam String username)
    {
        List list=usersService.lambdaQuery().eq(Users::getUsername,username).list();
        return list.size()>0?Result.success():Result.fail();
    }
    //新增
    @PostMapping("/api/save")
    public Result save(@RequestBody Users users){
        //调用service实现新增用户
        return usersService.save(users)?Result.success():Result.fail();

    }
    //更新
    @PostMapping("/api/update")
    public Result update(@RequestBody Users users){

            return usersService.updateById(users)?Result.success():Result.fail();

    }
    //修改
    @PostMapping("/api/mod")
    public Result mod(@RequestBody Users users){

        return usersService.updateById(users) ? Result.success() : Result.fail();
    }
    //删除
    @GetMapping("/api/delete")
    public Result delete(@RequestParam("id") Integer id) {
            return usersService.removeById(id) ? Result.success() : Result.fail();
    }
    //查询(模糊、匹配）
    // 分页查询（模糊匹配用户名）
    @PostMapping("/api/listPage")
    public Result listPage(@RequestBody QueryPageParam queryPageParam) {
        HashMap params = queryPageParam.getParam();
        String username = (String) params.get("username");

        Page<Users> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<Users> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(username)) { // 使用 StringUtils 来检查字符串是否不为空或全为空格
            queryWrapper.like(Users::getUsername, username);
            System.out.println("Applying LIKE condition for username: " + username); // 调试输出
        }
        System.out.println(queryWrapper.toString()); // 打印出queryWrapper的内容，以便调试
        IPage<Users> result = usersService.page(page, queryWrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }


    //分页
    @PostMapping("/api/listPageC")
    public Result listPageC(@RequestBody QueryPageParam queryPageParam)
    {
        HashMap param =queryPageParam.getParam();
        String username=(String)param.get("username");
        System.out.println("username=="+(String)param.get("username"));
        Page<Users> page=new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        //page.setCurrent(queryPageParam.getPageNum());//也可以这样设置

        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            lambdaQueryWrapper.like(Users::getUsername, username);
        }

        //还可以添加其他条件
        IPage result=usersService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return  Result.success(result.getRecords(),result.getTotal());

    }

    @PostMapping("/api/listPageCC")
    public List<Users> listPageCC(@RequestBody QueryPageParam queryPageParam)
    {
        System.out.println("num=="+queryPageParam.getPageNum());
        System.out.println("size=="+queryPageParam.getPageSize());
        HashMap param =queryPageParam.getParam();
        String name=(String)param.get("username");
        System.out.println("username=="+(String)param.get("username"));

        Page<Users> page=new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        //page.setCurrent(queryPageParam.getPageNum());//也可以这样设置

        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Users::getUsername,name);
        //还可以添加其他条件

        //IPage result=usersService.pageC(page);
        IPage result=usersService.pageCC(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());

        return  result.getRecords();

    }
    @PostMapping("/api/listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam)
    {

        HashMap params =queryPageParam.getParam();
        String name=(String)params.get("username");
        System.out.println("username=="+(String)params.get("username"));

        Page<Users> page=new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        //page.setCurrent(queryPageParam.getPageNum());//也可以这样设置

        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Users::getUsername,name);
        //还可以添加其他条件

        //IPage result=usersService.pageC(page);
        IPage result=usersService.pageCC(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());

        return  Result.success(result.getRecords(),result.getTotal());

    }


}

