package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.security.domain.entity.WeixinApp;
import top.erzhiqian.weixin.security.domain.valueobject.ICheckMessageToken;

public interface WeixinServerVerifyTokenRepository {
    ICheckMessageToken findServerVerifyToken(WeixinApp app);
}
