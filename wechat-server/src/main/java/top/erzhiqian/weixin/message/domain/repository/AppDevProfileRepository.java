package top.erzhiqian.weixin.message.domain.repository;

import top.erzhiqian.weixin.message.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;

public interface AppDevProfileRepository {
    AppDevProfile findAppDevProfile(WeixinAppId app);

    void saveProfile(AppDevProfile profile);
}
