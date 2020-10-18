package top.erzhiqian.weixin.security;

import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;

public interface IAccessToken {
    AccessTokenString loadAccessToken(WeixinAppId app);
}
