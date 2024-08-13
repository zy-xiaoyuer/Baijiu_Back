package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.User;
import com.baijiu.Baijiu_Back.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ltt
 * @since 2024-08-12
 */
@Service
public interface UserService extends IService<User> {

    int getUserByMassage(@Param("name") String name, @Param("password") String password);
    List<User> list();
}
