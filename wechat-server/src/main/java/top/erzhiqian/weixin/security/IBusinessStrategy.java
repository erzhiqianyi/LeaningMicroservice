package top.erzhiqian.weixin.security;

import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;

public interface IBusinessStrategy {
    <T> T getBusinessStrategy(WeixinAppId app, String businessType, Class<T> strategy);
}
