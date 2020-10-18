package top.erzhiqian.weixin.security.service.impl;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.IAccessToken;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;

@Service
public class LoadAccessToken implements IAccessToken {


    @Override
    public AccessTokenString loadAccessToken(WeixinAppId app) {

        return null;
    }

}
