package top.erzhiqian.weixin.message.domain.repository;

import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.IWeixinServerToken;

public interface WeixinServerTokenRepository {
    IWeixinServerToken findWeixinServerToken(WeixinAppId app);
}
