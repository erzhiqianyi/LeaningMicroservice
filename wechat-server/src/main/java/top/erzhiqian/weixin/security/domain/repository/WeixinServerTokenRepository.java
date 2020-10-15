package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.IWeixinServerToken;

public interface WeixinServerTokenRepository {
    IWeixinServerToken findWeixinServerToken(WeixinAppId app);
}
