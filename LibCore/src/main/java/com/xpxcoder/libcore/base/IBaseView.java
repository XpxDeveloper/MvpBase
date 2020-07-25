package com.xpxcoder.libcore.base;

/***************************************************************
 * @author: xpxCoder.
 * CreateTime: 2020-07-25 21:25(星期六)
 * Description:
 * ************************************************************/
public interface IBaseView {
    /**
     * 开始加载
     */
    void onStartLoad();

    /**
     * 加载成功
     */
    void onLoadSuccess();

    /**
     * 加载失败
     */
    void onLoadError();

    /**
     * 加载失败
     *
     * @param code
     * @param message
     */
    void onHandleErrorCode(int code, String message);
}
