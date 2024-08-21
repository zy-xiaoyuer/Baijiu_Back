package com.baijiu.Baijiu_Back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ltt
 * @since 2024-08-19
 */
@Getter
@Setter
@TableName("vessel_total")
@ApiModel(value = "VesselTotal对象", description = "")
public class VesselTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String vessel;

    private String name;

    private String discription;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private byte[] picture;
}
