package top.erzhiqian.weixin.security.domain.repository;

import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AppBusinessStrategy;

public interface BusinessStrategyRepository {
    AppBusinessStrategy findBusinessStrategy(WeixinAppId app);

    void save(AppBusinessStrategy strategy);
}
