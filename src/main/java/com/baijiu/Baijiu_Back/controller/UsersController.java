package com.baijiu.Baijiu_Back.controller;

import com.baijiu.Baijiu_Back.common.QueryPageParam;
import com.baijiu.Baijiu_Back.common.Result;
import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
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

    @CrossOrigin
    @RequestMapping("/api/loadAll")
    @ResponseBody
    public Result<List<Users>> loadAll() {
        List<Users> list = usersService.list();
        return Result.of(list, true,"操作成功");
    }

    @RequestMapping("/api/loadAllByPage")
    public Result loadAllByPage(@RequestParam("pageNum")Integer pageNum,
                                @RequestParam("pageSize")Integer pageSize,
                                String search){
        //通过分页的插件【拦截器】
        Page<Users> page = new Page<>(pageNum, pageSize);
        IPage<Users> resultPage = usersService.getUserList(page, search);
        return Result.ofPage(resultPage.getRecords(), resultPage.getTotal(), true, "操作成功");
    }
    //新增
    @PostMapping("/api/save")
    public boolean save(@RequestBody Users users){
        //调用service实现新增用户
        return usersService.save(users);
        //return Result.of(save);
    }
    //修改
    @PostMapping("/api/mod")
    public boolean mod(@RequestBody Users users){
        return usersService.updateById(users);
        //return Result.of(save);
    }
    //删除
    @GetMapping("/api/delete")
    public boolean delete(Integer id)
    {
        return usersService.removeById(id);
    }
    //查询(模糊、匹配）
    @PostMapping("/api/search")
    public List<Users> search(@RequestBody Users users)
    {
        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Users::getUsername,users.getUsername());//模糊查询
        lambdaQueryWrapper.eq(Users::getUsername,users.getUsername());//精确查询
        return usersService.list(lambdaQueryWrapper);

    }

    //分页操作
    @PostMapping("/api/listPage")
    public List<Users> listPage(@RequestBody QueryPageParam queryPageParam)
    {
//        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper();
//        lambdaQueryWrapper.like(Users::getUsername,users.getUsername());//模糊查询
//        lambdaQueryWrapper.eq(Users::getUsername,users.getUsername());//精确查询
//        return usersService.list(lambdaQueryWrapper);
        System.out.println(queryPageParam);

        //System.out.println("num=="+(String)map.get("pageSize"));hashmap的方式
        System.out.println("num=="+queryPageParam.getPageNum());
        System.out.println("size=="+queryPageParam.getPageSize());

        HashMap param =queryPageParam.getParam();
        System.out.println("username=="+(String)param.get("username"));
        System.out.println("nickname=="+(String)param.get("nickname"));
        return  null;

    }
    @PostMapping("/api/listPageC")
    public List<Users> listPageC(@RequestBody QueryPageParam queryPageParam)
    {
        System.out.println("num=="+queryPageParam.getPageNum());
        System.out.println("size=="+queryPageParam.getPageSize());
        HashMap param =queryPageParam.getParam();
        String name=(String)param.get("username");
        System.out.println("username=="+(String)param.get("username"));

        Page<Users> page=new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        //page.setCurrent(queryPageParam.getPageNum());//也可以这样设置

        LambdaQueryWrapper<Users> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Users::getUsername,name);

        IPage result=usersService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());

        return  result.getRecords();

    }

}

