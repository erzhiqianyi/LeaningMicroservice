package top.erzhiqian.weixin.account.infrastructure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.account.domain.entity.AppHost;
import top.erzhiqian.weixin.account.domain.valueobject.HostAccountId;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppHostPO;
import top.erzhiqian.weixin.lang.WeixinAppId;

@Component
public class WeixinAppHostConvert {

    public AppHost convertToEntity(WeixinAppHostPO po) {
//        AppHost appHost = AppHost.host(new HostAccountId(po.getHostId()));
//        appHost.addApp(WeixinAppId.app(po.getAppId()));
//        return appHost;
        return null;
    }

}
