package com.baijiu.Baijiu_Back.common;

import com.baijiu.Baijiu_Back.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 统一返回对象
 */

@Data

public class Result {
    private int code;//编码 200/400
    private String msg;//成功/失败
    private  Long total;//总记录数
    private Object data;//数据
    public Result() {}

    public static Result fail(String info)
    {
        return result(400,"失败",0l,info);
    }
    public static Result success()
    {
        return result(200,"成功",0l,null);
    }
    public static Result success(Object data)
    {
        return result(200,"成功",0l,data);
    }

   public static Result success(Object data, Long total)
    {
        return result(200,"成功",total,data);
    }


    public static Result result(int code,String msg,Long total,Object data) {
       Result res=new Result();
       res.setCode(code);
       res.setTotal(total);
       res.setData(data);
       res.setMsg(msg);
       return res;
    }

}