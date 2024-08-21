package com.baijiu.Baijiu_Back.entity;

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
@ApiModel(value = "Poemsbydynasty对象", description = "")
public class Poemsbydynasty implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String dynasty;

    private String author;

    private String place;

    private Integer time;

    private String content;

    private String emotion;

    private String emotionList;

    private Integer id;
}
