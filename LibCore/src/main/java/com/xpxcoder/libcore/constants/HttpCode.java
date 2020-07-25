package com.xpxcoder.libcore.constants;

/**
 * @author : Mai_Xiao_Peng
 * @email : Mai_Xiao_Peng@163.com
 * @time : 2018/7/26 9:53
 * @describe : http请求状态码
 */
public enum HttpCode {

    HTTP_OK(0, "请求成功"),
    HTTP_EXPIRED_401(-401, "token过期"),
    HTTP_EXPIRED_402(-402, "token过期"),
    HTTP_USER_ALREADY_REGISTERED(-12201020, "用户己注册"),
    HTTP_USER_LOGIN_AT_OTHER_PHONE(-12106904, "该帐号已在另一设备登录"),
    HTTP_LOGIN_WECHAT_FAIL(-12106039, "微信登录失败,请绑定手机号"),
    HTTP_NULL(1000, "服务数据返回null"),
    HTTP_OK_NULL(1001, "请求成功无数据返回"),
    HTTP_UNKNOWN(10000, "未知错误");

    private Integer code;

    private String message;

    HttpCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
