package top.erzhiqian.weixin.account.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.repository.WeixinAppRepository;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.account.infrastructure.convert.WeixinAppConvert;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppAccountPO;
import top.erzhiqian.weixin.account.infrastructure.repository.jdbc.WeixinAppJdbcRepository;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Optional;

@Service
public class WeixinAppRepositoryAdapt implements WeixinAppRepository {

    private WeixinAppConvert convert;

    private WeixinAppJdbcRepository jdbcRepository;

    public WeixinAppRepositoryAdapt(WeixinAppConvert convert,
                                    WeixinAppJdbcRepository jdbcRepository) {
        this.convert = convert;
        this.jdbcRepository = jdbcRepository;
    }

    @Override
    public Optional<WeixinApp> findWeixinApp(WeixinAppId weixinAppId) {
        return Optional.empty();
    }

    @Override
    public void save(WeixinAppAccount weixinApp) {
        Optional<WeixinAppAccountPO> optional = jdbcRepository.findByAppId(weixinApp.getAppId().appId());
        WeixinAppAccountPO po = optional.isPresent() ? optional.get() : null;
        po = convert.converToPO(weixinApp, po);
        jdbcRepository.save(po);

    }


}
