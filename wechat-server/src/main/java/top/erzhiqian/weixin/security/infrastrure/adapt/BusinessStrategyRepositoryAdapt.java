package top.erzhiqian.weixin.security.infrastrure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AppBusinessStrategy;
import top.erzhiqian.weixin.security.domain.repository.BusinessStrategyRepository;
import top.erzhiqian.weixin.security.infrastrure.convert.AppBusinessStrategyConvert;
import top.erzhiqian.weixin.security.infrastrure.po.AppBusinessStrategyPO;
import top.erzhiqian.weixin.security.infrastrure.repository.jdbc.AppBusinessStrategyJdbcRepository;

import java.util.List;

@Service
public class BusinessStrategyRepositoryAdapt implements BusinessStrategyRepository {

    private AppBusinessStrategyJdbcRepository repository;

    private AppBusinessStrategyConvert convert;


    public BusinessStrategyRepositoryAdapt(AppBusinessStrategyJdbcRepository repository,
                                           AppBusinessStrategyConvert convert) {
        this.repository = repository;
        this.convert = convert;
    }

    @Override
    public AppBusinessStrategy findBusinessStrategy(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException("illegal app.");
        }
        List<AppBusinessStrategyPO> businessStrategies = repository.findByAppId(app.appId());
        return convert.convertToEntity(app, businessStrategies);
    }

    @Override
    public void save(AppBusinessStrategy strategy) {
        List<AppBusinessStrategyPO> businessStrategies = repository.findByAppId(strategy.getApp().appId());
        businessStrategies = convert.convertToPO(strategy,businessStrategies);
        repository.saveAll(businessStrategies);

    }
}
