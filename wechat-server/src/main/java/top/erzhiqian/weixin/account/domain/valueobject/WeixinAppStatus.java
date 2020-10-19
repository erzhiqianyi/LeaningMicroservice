package top.erzhiqian.weixin.account.domain.valueobject;

public class WeixinAppStatus {

    private CertifiedStatus certifiedStatus;

    private AppStatus appStatus;

    public enum CertifiedStatus {
        CERTIFIED("CERTIFIED", "已认证"),
        UN_CERTIFIED("UN_CERTIFIED", "未认证");
        private String code;
        private String remark;

        CertifiedStatus(String code, String remark) {
            this.code = code;
            this.remark = remark;
        }
    }

    public enum AppStatus {
        CREATED("CREATED","已创建"),
        APPLY_CERTIFIED("APPLY_CERTIFIED","认证中"),
        CERTIFIED("CERTIFIED", "已认证"),
        UN_CERTIFIED("UN_CERTIFIED", "未认证"),
        BLOCKED("BLOCKED","暂停服务")
        ;
        private String code;
        private String remark;

        AppStatus(String code, String remark) {
            this.code = code;
            this.remark = remark;
        }
    }

}
