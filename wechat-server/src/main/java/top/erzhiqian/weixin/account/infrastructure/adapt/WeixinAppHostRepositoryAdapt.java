package top.erzhiqian.weixin.account.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.account.domain.entity.AppHost;
import top.erzhiqian.weixin.account.domain.repository.WeixinAppHostRepository;
import top.erzhiqian.weixin.account.infrastructure.convert.WeixinAppHostConvert;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppHostPO;
import top.erzhiqian.weixin.account.infrastructure.repository.jdbc.WeixinAppHostJdbcRepository;
import top.erzhiqian.weixin.account.infrastructure.repository.jdbc.WeixinAppJdbcRepository;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Optional;

@Service
public class WeixinAppHostRepositoryAdapt implements WeixinAppHostRepository {

    private WeixinAppHostConvert convert;

    private WeixinAppHostJdbcRepository appHostRepository;

    private WeixinAppJdbcRepository weixinAppRepository;

    public WeixinAppHostRepositoryAdapt(WeixinAppHostConvert convert,
                                        WeixinAppHostJdbcRepository appHostRepository,
                                        WeixinAppJdbcRepository weixinAppRepository) {
        this.convert = convert;
        this.appHostRepository = appHostRepository;
        this.weixinAppRepository = weixinAppRepository;
    }

    @Override
    public AppHost findWeixinAppHost(WeixinAppId appId) {
        if (null == appId) {
            throw new IllegalArgumentException("illegal app.");
        }
        Optional<WeixinAppHostPO> optional = appHostRepository.findByAppId(appId.appId());
//        return optional.map(convert::convertToEntity);
        return null;
    }

    @Override
    public void save(AppHost appHost) {
//        appHostRepository.save(po);
    }
}
