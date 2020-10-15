package top.erzhiqian.weixin.security.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;
import top.erzhiqian.weixin.security.domain.repository.WeixinServerTokenRepository;
import top.erzhiqian.weixin.security.domain.valueobject.IWeixinServerToken;
import top.erzhiqian.weixin.security.infrastructure.repository.jdbc.AppDevProfileJdcRepository;

import java.util.Optional;

@Service
public class WeixinServerTokenRepositoryAdapt implements WeixinServerTokenRepository {

    private AppDevProfileJdcRepository jdcRepository;

    public WeixinServerTokenRepositoryAdapt(AppDevProfileJdcRepository jdcRepository) {
        this.jdcRepository = jdcRepository;
    }

    @Override
    public IWeixinServerToken findWeixinServerToken(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException(" illegal app");
        }
        Optional<String> optional = jdcRepository.selectMessageToken(app.appId());
        optional.orElseThrow(() -> new IllegalArgumentException("illegal app"));
        return IWeixinServerToken.defaultToken(optional.get());
    }
}
