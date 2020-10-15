package top.erzhiqian.weixin.security.client.cmd;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WeixinVerifyMessageCmd {

    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    @NotBlank(message = "signature can't be null ")
    private String signature;

    /**
     * 时间戳
     */
    @NotBlank(message = "timestamp can't be null ")
    private String timestamp;

    /**
     * 随机数
     */
    @NotBlank(message = " nonce can't be null ")
    private String nonce;

    /**
     * 随机字符串
     */
    @NotBlank(message = " echostr can't be null ")
    private String echostr;
}
