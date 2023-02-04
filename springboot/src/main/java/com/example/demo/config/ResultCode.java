package com.example.demo.config;

/**
 * @author zsq
 * @create 2023-01-31-22:38:34
 */
public enum ResultCode {

    SUCCESS(0, "成功"),
    FAIL(501, "失败"),
    LOGIN_ERROR(10001, "账号或密码错误"),
    LOGIN_ERROR_2(10002, "两次密码输入不同"),
    LOGIN_ERROR_3(10003, "该用户名已存在"),
    ;

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
