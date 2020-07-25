package com.xpxcoder.libcore.rx;

import lombok.Data;

/**
 * @author: Mai_Xiao_Peng
 * email: Mai_Xiao_Peng@163.com
 * time: 2017/5/5
 * desc:事件类型数据
 */

@Data
public class RxEvent {

    private int eventType;
    private Object data;

    public RxEvent(int eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }

    /**
     * 事件类型
     */
    public static class EventType {
        /**
         * 升级app
         */
        public static final int UPDATE_APP = 1001;


        /**
         * 退出账户登录
         */
        public static final int SYSTEM_LOGIN_OUT = 1003;

        /**
         * 退出账户切换账号
         */
        public static final int SYSTEM_LOGIN_OUT_CHANGE_ACCOUNT = 10013;

        /**
         * 关闭除了首页所有activity
         */
        public static final int SYSTEM_CLOSE_ACTIVITY = 1004;

        /**
         * 登录成功
         */
        public static final int SYSTEM_LOGIN_SUCCESS = 1005;


    }
}
