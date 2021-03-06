package com.cas.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @param <T>
 * @author JiangXianyi
 * @ClassName 接口返回数据
 * @Description TODO
 * @date 2019年4月11日
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    public final static int successCode = 200;
    public final static int errorCode = 400;
    private static final long serialVersionUID = 1L;
    /**
     * 成功标志
     */
    private boolean success = true;
    /**
     * 返回处理消息
     */
    private String message = "";
    /**
     * 返回代码
     */
    private Integer code = 200;
    /**
     * 返回数据对象 data
     */
    private T result;
    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public static <T> Result<T> buildError(String msg) {
        return buildError(500, msg);
    }

    public static <T> Result<T> buildError(int code, String msg) {
        return buildError(code, msg, null);
    }

    public static <T> Result<T> buildError(int code, String msg, T result) {
        Result<T> r = new Result<>();
        r.error(code, msg, result);
        return r;
    }

    public static <T> Result<T> buildSuccess() {
        return buildSuccess(null);
    }

    public static <T> Result<T> buildSuccess(String msg) {
        return buildSuccess(msg, null);
    }

    public static <T> Result<T> buildSuccess(int code, String msg) {
        return buildSuccess(code, msg, null);
    }

    public static <T> Result<T> buildSuccess(String message, T result) {
        Result<T> r = new Result<>();
        r.success(message, result);
        return r;
    }

    public static <T> Result<T> buildSuccess(int code, String message, T result) {
        Result<T> r = new Result<>();
        r.message = message;
        r.code = code;
        r.success = true;
        r.result = result;
        return r;
    }

    public Result<T> error(int code, String message, T result) {
        this.success = false;
        this.code = code;
        this.message = message;
        this.result = result;
        return this;
    }

    public Result<T> error(int code, String message) {
        return error(code, message, null);
    }

    public Result<T> error(String msg) {
        return error(500, msg);
    }

    public Result<T> success(String message) {
        this.message = message;
        this.code = 200;
        this.success = true;
        return this;
    }

    public Result<T> success(String message, T result) {
        success(message);
        this.result = result;
        return this;
    }
}
