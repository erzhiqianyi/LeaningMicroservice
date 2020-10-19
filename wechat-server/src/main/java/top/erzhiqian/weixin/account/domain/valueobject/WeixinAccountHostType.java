package top.erzhiqian.weixin.account.domain.valueobject;

public enum WeixinAccountHostType {
    PERSONAL("PERSONAL","个人"),
    COMPANY("COMPANY","企业"),
    GOVERNMENT("GOVERNMENT", "政府"),
    NGO("NGO", "公益组织");

    private String code;
    private String remark;

    WeixinAccountHostType(String code, String remark) {
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
