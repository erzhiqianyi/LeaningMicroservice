package top.erzhiqian.weixin.account.domain.valueobject;

public enum  WeixinCertifiedState {
   CERTIFIED("CERTIFIED","已认证"),
   UN_CERTIFIED("UN_CERTIFIED","未认证"),
   ;

   WeixinCertifiedState(String code, String remark) {
      this.code = code;
      this.remark = remark;
   }

   private String code;

   private String remark;

   public String getCode() {
      return code;
   }

   public String getRemark() {
      return remark;
   }
}
