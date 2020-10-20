package top.erzhiqian.weixin.account.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.entity.WeixinAppHost;
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
    public Optional<WeixinAppHost> findWeixinAppHost(WeixinAppId appId) {
        if (null == appId) {
            throw new IllegalArgumentException("illegal app.");
        }
        Optional<WeixinAppHostPO> optional = appHostRepository.findByAppId(appId.appId());
        if (!optional.isPresent()) {
            return Optional.empty();
        }

        return null;
    }

    @Override
    public void save(WeixinAppHost appHost) {
        WeixinAppHostPO po = convert.convertToHostPO(appHost);
        appHostRepository.save(po);
    }
}
