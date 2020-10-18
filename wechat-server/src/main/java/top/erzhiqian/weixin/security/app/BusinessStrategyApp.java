package top.erzhiqian.weixin.security.app;

import jdk.nashorn.internal.ir.LiteralNode;
import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.BusinessStrategySetting;
import top.erzhiqian.weixin.security.client.vo.BusinessStrategyVO;
import top.erzhiqian.weixin.security.domain.entity.AppBusinessStrategy;
import top.erzhiqian.weixin.security.domain.repository.BusinessStrategyRepository;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class BusinessStrategyApp {

    private BusinessStrategyRepository repository;

    public BusinessStrategyApp(BusinessStrategyRepository repository) {
        this.repository = repository;
    }

    public List<BusinessStrategyVO> getBusinessStrategy(WeixinAppId app) {
        if (null == app) {
            return Collections.emptyList();
        }
        AppBusinessStrategy strategy = repository.findBusinessStrategy(app);
        List<BusinessStrategyVO> strategyVO = Stream
                .of(BusinessType.values())
                .map(businessType -> new BusinessStrategyVO(businessType, strategy.getStrategy(businessType)))
                .collect(toList());
        return strategyVO;
    }

    public void setBusinessStrategy(WeixinAppId app, List<BusinessStrategySetting> settings) {
        if (null == app){
            throw new IllegalArgumentException("illegal app.");
        }
        AppBusinessStrategy strategy = repository.findBusinessStrategy(app);
        strategy.setStrategy(settings);
        repository.save(strategy);
    }
}
