package top.erzhiqian.weixin.security.service.impl;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.IAccessToken;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;

@Service("thirdGetAccessToken")
public class ThirdServerAccessTokenStrategy implements IAccessToken {


    @Override
    public AccessTokenString loadAccessToken(WeixinAppId app) {
        throw new IllegalStateException(" service not  implement.");
    }
}
