package top.erzhiqian.weixin.security.client.cmd;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BusinessStrategySetting {

   @NotBlank(message = "业务类型不能为空")
   private String businessType;

   @NotBlank(message = "业务策略不能为空")
   private String strategy;

   private String callBackUrl;
}
