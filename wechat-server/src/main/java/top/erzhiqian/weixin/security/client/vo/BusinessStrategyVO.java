package top.erzhiqian.weixin.security.client.vo;

import lombok.Data;
import top.erzhiqian.weixin.lang.UrlLink;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategyEnum;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

@Data
public class BusinessStrategyVO {

    private EnumVO businessType;

    private EnumVO businessStrategy;

    private UrlLink urlLink;


    public BusinessStrategyVO(BusinessType businessType, BusinessStrategy strategy) {
        if (null == businessType) {
            throw new IllegalArgumentException(" illegal business .");
        }
        this.businessType = new EnumVO(businessType.getCode(), businessType.getRemark());

        if (null != strategy) {
            BusinessStrategyEnum strategyEnum = strategy.getStrategy();
            this.businessStrategy = new EnumVO(strategyEnum.getCode(), strategyEnum.getRemark());
            this.urlLink = strategy.getCallback();
        } else {
            this.businessStrategy = notDefinedStrategy();
        }


    }

    private EnumVO notDefinedStrategy() {
        return new EnumVO("NOT_DEFINE", "未设置业务策略。");
    }
}
