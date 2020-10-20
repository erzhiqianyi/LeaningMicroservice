package top.erzhiqian.weixin.account.infrastructure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.account.domain.entity.WeixinAppHost;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppHostPO;

import java.time.Instant;

@Component
public class WeixinAppHostConvert {

    public WeixinAppHost convertToEntity() {
        return null;
    }

    public WeixinAppHostPO convertToHostPO(WeixinAppHost appHost) {
        WeixinAppHostPO po = new WeixinAppHostPO();
        po.setAppId(appHost.getHost().id());
        po.setCreateAt(System.currentTimeMillis());
        po.setAppId(appHost.getWeixinAccount().getAppId().appId());
        return po;

    }
}
