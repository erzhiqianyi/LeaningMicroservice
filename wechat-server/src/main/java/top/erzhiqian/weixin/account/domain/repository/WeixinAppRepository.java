package top.erzhiqian.weixin.account.domain.repository;

import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.lang.WeixinAppId;

public interface WeixinAppRepository {

    WeixinApp findWeixinApp(WeixinAppId weixinAppId);

}
