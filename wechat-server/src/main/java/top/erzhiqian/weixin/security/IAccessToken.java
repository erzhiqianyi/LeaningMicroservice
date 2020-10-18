package top.erzhiqian.weixin.security;

import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;
import top.erzhiqian.weixin.spring.ApplicationContextHolder;

public interface IAccessToken {
    AccessTokenString loadAccessToken(WeixinAppId app);

    static AccessTokenString accessToken(WeixinAppId app) {
        IBusinessStrategy businessStrategy = ApplicationContextHolder.getBean(IBusinessStrategy.class);
        IAccessToken iAccessToken = businessStrategy.getBusinessStrategy(app,
                BusinessType.GET_ACCESS_TOKEN.getCode(),  IAccessToken.class);
        return iAccessToken.loadAccessToken(app);
    }
}
