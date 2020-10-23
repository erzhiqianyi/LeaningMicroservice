package top.erzhiqian.weixin.account.domain.repository;

import top.erzhiqian.weixin.account.domain.entity.AppHost;
import top.erzhiqian.weixin.lang.WeixinAppId;

public interface WeixinAppHostRepository {
    AppHost findWeixinAppHost(WeixinAppId appId);


    void save(AppHost appHost);
}
