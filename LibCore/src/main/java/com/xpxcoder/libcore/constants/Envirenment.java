package com.xpxcoder.libcore.constants;

/**
 * @author : Mai_Xiao_Peng
 * @email : Mai_Xiao_Peng@163.com
 * @time : 2018/7/26 9:53
 * @describe : App环境
 */


public enum Envirenment {

    //    DEV(1, "dev", "开发环境", "http://carowner.newdev.ychost.com", "http://merchant-app.dev.51yryc.com", "yryc-merchant-app-dev", "bA5l5LBffPwc", false),
    DEV(1, "dev", "开发环境", "http://gateway.devproxy.51yryc.com", "http://ownercar-web-dev.devproxy.51yryc.com", "yryc-merchant-app-dev", "bA5l5LBffPwc", false),
    TEST(2, "test", "测试环境", "http://gatewaytest.devproxy.51yryc.com", "http://ownercar-web-test.devproxy.51yryc.com", "yryc-merchant-app-test", "lEtTEcBvErct", false),
    PRE_PROD(3, "pre", "预生产环境", "https://onecar-pre.51yryc.com", "https://onecar-app.51yryc.com/own-pre", "yryc-merchant-app-pre", "pRtPzLvXqOgg", true),
    PROD(4, "prod", "生产环境", "https://onecar.51yryc.com", "https://onecar-app.51yryc.com/own-prod", "yryc-merchant-app-prod", "pRtPzLvXqOgg", true);

    private Integer code;
    private String message;
    private String remark;
    private String httpAddress;
    private String webAddress;
    private String ossBucket;
    private String yrycImAppId;
    /**
     * 是否加密
     **/
    private Boolean isEncrypt;

    Envirenment(Integer code, String message, String remark, String httpAddress, String webAddress, String ossBucket, String yrycImAppId, boolean isEncrypt) {
        this.code = code;
        this.message = message;
        this.remark = remark;
        this.httpAddress = httpAddress;
        this.webAddress = webAddress;
        this.ossBucket = ossBucket;
        this.yrycImAppId = yrycImAppId;
        this.isEncrypt = isEncrypt;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRemark() {
        return remark;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public String getOssBucket() {
        return ossBucket;
    }

    public String getHttpAddress() {
        return httpAddress;
    }

    public String getYrycImAppId() {
        return yrycImAppId;
    }

    public Boolean getEncrypt() {
        return isEncrypt;
    }

    public void setEncrypt(Boolean encrypt) {
        isEncrypt = encrypt;
    }

    public static String getValueByKey(int key) {
        for (Envirenment c : Envirenment.values()) {
            if (c.getCode() == key) {
                return c.getRemark();
            }
        }
        return null;
    }
}
