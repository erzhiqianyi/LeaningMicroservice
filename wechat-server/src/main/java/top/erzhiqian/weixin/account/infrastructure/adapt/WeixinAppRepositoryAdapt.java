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
    public WeixinApp findWeixinApp(WeixinAppId weixinAppId) {
        Optional<WeixinAppAccountPO> optional = findByAppId(weixinAppId);
//        return optional.map(convert::convertToEntity);
        return null;
    }

    private Optional<WeixinAppAccountPO> findByAppId(WeixinAppId weixinAppId) {
        if (null == weixinAppId) {
            throw new IllegalArgumentException("illegal app.");
        }
        return jdbcRepository.findByAppId(weixinAppId.appId());
    }

    @Override
    public void save(WeixinAppAccount weixinApp) {
        Optional<WeixinAppAccountPO> optional = findByAppId(weixinApp.getAppId());
        WeixinAppAccountPO po = optional.isPresent() ? optional.get() : null;
        po = convert.convertToPO(weixinApp, po);
        jdbcRepository.save(po);

    }


}
