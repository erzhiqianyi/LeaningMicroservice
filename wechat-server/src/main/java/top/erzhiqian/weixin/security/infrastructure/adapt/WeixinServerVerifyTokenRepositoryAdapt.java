package top.erzhiqian.weixin.security.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.security.domain.entity.WeixinApp;
import top.erzhiqian.weixin.security.domain.valueobject.ICheckMessageToken;
import top.erzhiqian.weixin.security.domain.repository.WeixinServerVerifyTokenRepository;
import top.erzhiqian.weixin.security.infrastructure.po.WeiXinMessagePushSettingPO;
import top.erzhiqian.weixin.security.infrastructure.repository.jdbc.WeiXinMessagePushSettingJdcRepository;

import java.util.Optional;

@Service
public class WeixinServerVerifyTokenRepositoryAdapt implements WeixinServerVerifyTokenRepository {

    private WeiXinMessagePushSettingJdcRepository jdcRepository;

    public WeixinServerVerifyTokenRepositoryAdapt(WeiXinMessagePushSettingJdcRepository jdcRepository) {
        this.jdcRepository = jdcRepository;
    }

    @Override
    public ICheckMessageToken findServerVerifyToken(WeixinApp app) {
        if (null == app) {
            throw new IllegalArgumentException(" illegal app");
        }
        Optional<WeiXinMessagePushSettingPO> optional = jdcRepository.selectMessageToken(app.appId());
        optional.orElseThrow(() -> new IllegalArgumentException("illegal app"));
        WeiXinMessagePushSettingPO po = optional.get();
        return ICheckMessageToken.defaultToken(po.getToken());
    }
}
