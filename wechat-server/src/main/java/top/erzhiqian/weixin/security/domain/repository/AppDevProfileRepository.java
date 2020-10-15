package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.security.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;

public interface AppDevProfileRepository {
    AppDevProfile findAppDevProfile(WeixinAppId app);

    void saveProfile(AppDevProfile profile);
}
