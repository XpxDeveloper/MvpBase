package com.xpxcoder.libcore.constants;

/**
 * @author : Mai_Xiao_Peng
 * @email : Mai_Xiao_Peng@163.com
 * @time : 2018/7/26 9:53
 * @describe : 文件操作类型
 */
public enum FileOperateCode {

    DOWNLOAD(0, "down"),
    UPLOAD(1, "upload");

    private Integer code;

    private String message;

    FileOperateCode(Integer code, String message) {
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
