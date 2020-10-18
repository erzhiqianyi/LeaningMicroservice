package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;

public interface AppSecretRepository {
    AppSecret findAppSecret(WeixinAppId app);

    void saveAppSecret(AppSecret appSecret);
}
