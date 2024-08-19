package com.baijiu.Baijiu_Back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "Poemsbylocation对象", description = "")
public class Poemsbylocation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;

    private String country;

    private String province;

    private String city;

    private String district;

    private String poetry;

    private String dynasty;

    private String author;

    private Integer time;

    private String content;

    private String emotion;

    private String coordinates;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
}
