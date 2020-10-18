package top.erzhiqian.weixin.security.client.cmd;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class ChangeAppSecretCmd {

    @NotBlank(message = "密钥不能为空。")
    private String appSecret;
}
