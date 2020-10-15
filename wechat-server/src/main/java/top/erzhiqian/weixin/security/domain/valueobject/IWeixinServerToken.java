package top.erzhiqian.weixin.security.domain.valueobject;

import top.erzhiqian.weixin.security.client.cmd.WeixinVerifyMessageCmd;

public interface IWeixinServerToken {
    boolean checkMessage(WeixinVerifyMessageCmd message);


    static IWeixinServerToken defaultToken(String token) {
        Sha1SignTokenWeixin shaToken = new Sha1SignTokenWeixin(token);
        return shaToken;
    }
}
