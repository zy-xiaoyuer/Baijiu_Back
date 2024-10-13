package com.baijiu.Baijiu_Back.service;

import com.baijiu.Baijiu_Back.entity.Users;
import com.baijiu.Baijiu_Back.entity.VesselTotal;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
@Resource
public interface VesselTotalService extends IService<VesselTotal> {
    IPage<VesselTotal> getUserList(IPage<VesselTotal> page, @Param("search") String search);

    IPage pageC(IPage<VesselTotal> page);

    IPage pageCC(IPage<VesselTotal> page, Wrapper wrapper);
    public Long countAll();

}
