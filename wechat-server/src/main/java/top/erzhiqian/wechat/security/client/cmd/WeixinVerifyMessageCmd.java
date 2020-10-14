package top.erzhiqian.wechat.security.client.cmd;

import lombok.Data;

@Data
public class WeixinVerifyMessageCmd {

    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    private String signature;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 随机数
     */
    private Integer nonce;

    /**
     * 随机字符串
     */
    private String echostr;
}
