package top.erzhiqian.weixin.account.domain.repository;

import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Optional;

public interface WeixinAppRepository {

    Optional<WeixinApp> findWeixinApp(WeixinAppId weixinAppId);

    void save(WeixinAppAccount weixinApp);
}
