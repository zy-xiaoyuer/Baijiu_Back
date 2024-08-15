package com.baijiu.Baijiu_Back.mapper;

import com.baijiu.Baijiu_Back.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ltt
 * @since 2024-08-15
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
   Admin getAdminByMassage(@Param("username") String name, @Param("password") String password);

}
