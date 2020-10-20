package top.erzhiqian.weixin.account.infrastructure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppAccountPO;

@Component
public class WeixinAppConvert {

    public WeixinAppAccountPO converToPO(WeixinAppAccount weixinApp, WeixinAppAccountPO original) {
        original = null == original ? new WeixinAppAccountPO() : original;
        original.setOriginalId(weixinApp.getOriginalId().appId());
        original.setAppName(weixinApp.getName().value());
        original.setWeixinId(weixinApp.getWeixinId().value());
        original.setAppName(weixinApp.getName().value());
        original.setAppType(weixinApp.getType().getCode());
        original.setCertifiedState(weixinApp.getState().getCode());
        original.setHostType(weixinApp.getHostType().getCode());
        return original;
    }
}
