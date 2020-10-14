package top.erzhiqian.weixin.security.domain.valueobject;

import top.erzhiqian.weixin.security.client.cmd.WeixinVerifyMessageCmd;

public interface ICheckMessageToken {
    boolean checkMessage(WeixinVerifyMessageCmd message);

    static ICheckMessageToken defaultToken(String token) {
        Sha1SignToken shaToken = new Sha1SignToken(token);
        return shaToken;
    }
}
