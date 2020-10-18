package top.erzhiqian.weixin.security.infrastrure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.BusinessStrategySetting;
import top.erzhiqian.weixin.security.domain.entity.AppBusinessStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategy;
import top.erzhiqian.weixin.security.infrastrure.po.AppBusinessStrategyPO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Component
public class AppBusinessStrategyConvert {
    public AppBusinessStrategy convertToEntity(WeixinAppId app,
                                               List<AppBusinessStrategyPO> businessStrategies) {
        AppBusinessStrategy strategy = new AppBusinessStrategy(app);
        if (null == businessStrategies || businessStrategies.isEmpty()) {
            return strategy;
        }
        List<BusinessStrategySetting> settings = businessStrategies
                .stream()
                .map(item -> {
                    BusinessStrategySetting setting = new BusinessStrategySetting();
                    setting.setBusinessType(item.getBusinessType());
                    setting.setStrategy(item.getStrategy());
                    setting.setCallBackUrl(item.getCallBackUrl());
                    return setting;
                }).collect(toList());
        strategy.setStrategy(settings);
        return strategy;
    }

    public List<AppBusinessStrategyPO> convertToPO(AppBusinessStrategy strategy,
                                                   List<AppBusinessStrategyPO> originalStrategy) {
        List<BusinessStrategy> strategies = strategy.listStrategies();
        originalStrategy = null == originalStrategy ? new ArrayList<>() : originalStrategy;
        Map<String, AppBusinessStrategyPO> originalMap =
                originalStrategy.stream()
                        .collect(toMap(AppBusinessStrategyPO::getBusinessType, item -> item));
        List<AppBusinessStrategyPO> newStrategy = strategies
                .stream()
                .map(item -> {
                    AppBusinessStrategyPO original = originalMap.get(item.getBusinessType().getCode());
                    original = convertToPO(item, original);
                    original.setAppId(strategy.getApp().appId());
                    return original;
                }).collect(toList());
        return newStrategy;
    }

    private AppBusinessStrategyPO convertToPO(BusinessStrategy businessStrategy,
                                              AppBusinessStrategyPO original) {
        original = null == original ? new AppBusinessStrategyPO() : original;
        original.setBusinessType(businessStrategy.getBusinessType().getCode());
        original.setStrategy(businessStrategy.getStrategy().getCode());
        original.setCallBackUrl(businessStrategy.callBackUrl());
        if (null == original.getId()){
            original.setCreateAt(System.currentTimeMillis());
            original.setLastModified(System.currentTimeMillis());
        }else {
           original.setLastModified(System.currentTimeMillis());
        }
        return original;
    }
}
