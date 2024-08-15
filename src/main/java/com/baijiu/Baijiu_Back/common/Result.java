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
@NoArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 通信数据
     */
    private T data;
    /**
     * 通信状态
     */
    private boolean success = true;
    /**
     * 通信描述
     */
    private String msg = "操作成功";

    /**
     * 用于分页结果的静态方法
     * @param data 分页数据记录
     * @param total 总记录数
     * @param success 操作是否成功
     * @param msg 操作消息
     * @return Result 实例
     */

    public static <T> Result<T> ofPage(List<T> data, long total, boolean success, String msg) {
        return (Result<T>) new Result<>(new PageData<>(data, total), success, msg);
    }
    /**
     * 封装分页数据的内部类
     */
    public static class PageData<T> implements Serializable {
        private List<T> records;
        private long total;

        public PageData(List<T> records, long total) {
            this.records = records;
            this.total = total;
        }

        public List<T> getRecords() {
            return records;
        }

        public long getTotal() {
            return total;
        }
    }
    /**
     * 通过静态方法获取实例
     */
    public T getData() {
        return data;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getMessage() {
        return msg;
    }
    public static <T> Result<T> of(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> of(T data, boolean success) {
        return new Result<>(data, success);
    }

    public static <T> Result<T> of(T data, boolean success, String msg) {
        return new Result<>(data, success, msg);
    }


    private Result(T data) {
        this.data = data;
    }

    private Result(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    private Result(T data, boolean success, String msg) {
        this.data = data;
        this.success = success;
        this.msg = msg;
    }

}