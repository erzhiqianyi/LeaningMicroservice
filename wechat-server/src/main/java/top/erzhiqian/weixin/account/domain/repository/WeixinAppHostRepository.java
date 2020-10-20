package top.erzhiqian.weixin.account.domain.repository;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppHost;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Optional;

public interface WeixinAppHostRepository {
    Optional<WeixinAppHost> findWeixinAppHost(WeixinAppId appId);

    void save(WeixinAppHost appHost);
}
