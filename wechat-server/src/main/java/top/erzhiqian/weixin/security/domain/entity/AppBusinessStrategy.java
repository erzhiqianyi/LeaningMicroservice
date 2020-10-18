package top.erzhiqian.weixin.security.domain.entity;

import org.springframework.util.StringUtils;
import top.erzhiqian.weixin.lang.UrlLink;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.BusinessStrategySetting;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategyEnum;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppBusinessStrategy {

    private final WeixinAppId app;

    private Map<BusinessType, BusinessStrategy> strategies;

    public AppBusinessStrategy(WeixinAppId app) {
        if (StringUtils.isEmpty(app)) {
            throw new IllegalArgumentException(" illegal app.");
        }
        this.app = app;
        this.strategies = new HashMap<>();
    }


    public BusinessStrategy getStrategy(BusinessType business) {
        if (null == business) {
            throw new IllegalArgumentException("illegal business");
        }
        BusinessStrategy businessStrategy = strategies.get(business);
        return businessStrategy;
    }

    public void registerStrategy(BusinessType businessType, BusinessStrategyEnum strategy, UrlLink callback) {
        if (null == businessType || null == strategy) {
            throw new IllegalArgumentException(" illegal strategy.");
        }
        BusinessStrategy businessStrategy = new BusinessStrategy(businessType, strategy, callback);
        strategies.put(businessType, businessStrategy);
    }

    public void registerStrategy(List<BusinessStrategySetting> settings) {
        if (null == settings || settings.isEmpty()) {
            return;
        }
        settings.forEach(setting -> {
            registerStrategy(setting);
        });
    }

    private void registerStrategy(BusinessStrategySetting setting) {
        if (null == setting) {
            return;
        }
        BusinessType businessType = BusinessType.getBusinessType(setting.getBusinessType());
        if (null == businessType) {
            return;
        }
        BusinessStrategyEnum strategy = BusinessStrategyEnum.getStrategy(setting.getStrategy());
        if (null == strategy) {
            throw new IllegalArgumentException(" illegal business strategy .");
        }
        UrlLink callBack = StringUtils.isEmpty(setting.getCallBackUrl()) ? null :
                new UrlLink(setting.getCallBackUrl());
        registerStrategy(businessType, strategy, callBack);
    }

    public WeixinAppId getApp() {
        return app;
    }

    public List<BusinessStrategy> listStrategies() {
        return strategies.values().stream().collect(Collectors.toList());
    }

}
