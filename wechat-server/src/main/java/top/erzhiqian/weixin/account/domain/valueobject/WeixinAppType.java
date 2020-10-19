package top.erzhiqian.weixin.account.domain.valueobject;

public enum WeixinAppType {
    SUBSCRIBE("SUBSCRIBE","订阅号"),
    SERVICES("SERVICES","服务号"),
    MINI_PROGRAM("MINI_PROGRAM","小程序");
    private String code;
    private String remark;

    WeixinAppType(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }
}
