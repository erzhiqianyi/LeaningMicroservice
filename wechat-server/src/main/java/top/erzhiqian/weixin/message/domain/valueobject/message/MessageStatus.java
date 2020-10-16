package top.erzhiqian.weixin.message.domain.valueobject.message;


public enum MessageStatus {
    CREATED("CREATED", "已创建"),
    SUCCESS("SUCCESS", "发送成功"),
    FAIL("FAIL", "发送失败");
    private String code;
    private String remark;

    MessageStatus(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }
}
