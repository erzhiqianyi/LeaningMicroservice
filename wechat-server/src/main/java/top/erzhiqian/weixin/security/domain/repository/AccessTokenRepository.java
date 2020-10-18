package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AccessToken;

public interface AccessTokenRepository {
    void cacheAccessToken(AccessToken accessToken);

    AccessToken findAccessToken(WeixinAppId app);
}
