package top.erzhiqian.weixin.security.service.impl;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.IBusinessStrategy;
import top.erzhiqian.weixin.security.app.BusinessStrategyApp;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;
import top.erzhiqian.weixin.spring.BeanFactory;

@Service
public class BusinessStrategyFactory implements IBusinessStrategy {

    private BusinessStrategyApp strategyApp;


    public BusinessStrategyFactory(BusinessStrategyApp strategyApp) {
        this.strategyApp = strategyApp;
    }

    @Override
    public <T> T getBusinessStrategy(WeixinAppId app, String businessType, Class<T> strategy) {
        if (null == app) {
            throw new IllegalArgumentException("illegal app.");
        }
        BusinessType business = BusinessType.getBusinessType(businessType);
        if (null == business) {
            throw new IllegalArgumentException(" illegal business.");
        }
        if (strategy != business.strategy()) {
            throw new IllegalArgumentException(" illegal strategy.");
        }
        BusinessStrategy businessStrategy = strategyApp.getBusinessStrategy(app, business);
        return BeanFactory.getBusinessImpl(businessStrategy.strategyName(), strategy);
    }
}
