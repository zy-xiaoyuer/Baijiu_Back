package com.baijiu.Baijiu_Back.mapper;

import com.baijiu.Baijiu_Back.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口  用户映射器接口
 * </p>
 *
 * @author ltt
 * @since 2024-08-12
 */
@Mapper//纳入Spring容器管理
public interface UserMapper extends BaseMapper<User> {
    User getUserByMassage(@Param("name") String name, @Param("password") String password);

    List<User> findAll();
}
