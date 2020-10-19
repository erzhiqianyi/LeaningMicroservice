package top.erzhiqian.weixin.account.domain.valueobject;

public enum WeixinAppType {
    ;
    private String code;
    private String remark;

    WeixinAppType(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }
}
