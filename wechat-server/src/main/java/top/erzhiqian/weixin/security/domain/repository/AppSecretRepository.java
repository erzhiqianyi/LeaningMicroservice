package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;

public interface AppSecretRepository {
    AppSecret findAppSecret(WeixinAppId app);

    void saveAppSecret(AppSecret appSecret);
}
