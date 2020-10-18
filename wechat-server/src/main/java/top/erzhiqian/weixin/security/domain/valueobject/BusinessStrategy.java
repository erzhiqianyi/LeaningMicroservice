package top.erzhiqian.weixin.security.domain.valueobject;

import lombok.Getter;
import top.erzhiqian.weixin.lang.UrlLink;

@Getter
public class BusinessStrategy {

    private final BusinessType businessType;

    private final BusinessStrategyEnum strategy;

    private final UrlLink callback;

    public BusinessStrategy(BusinessType businessType, BusinessStrategyEnum strategy, UrlLink callback) {
        if (null == businessType || null == strategy) {
            throw new IllegalArgumentException(" illegal business strategy.");
        }
        this.businessType = businessType;
        this.strategy = strategy;
        checkCallBack(callback);
        this.callback = callback;

    }

    private void checkCallBack(UrlLink callback) {
        if (BusinessStrategyEnum.isWeixinStrategy(strategy)) {
            return;
        }
        if (null == callback) {
            throw new IllegalArgumentException(" business callback can't be null.");
        }
    }


    public String callBackUrl() {
        return null == callback ? null : callback.url();
    }

    public String strategyName() {
        return strategy.getCode() + businessType.getCode();
    }

    public boolean isWeixinBusiness() {
        return BusinessStrategyEnum.isWeixinStrategy(strategy);
    }
}
