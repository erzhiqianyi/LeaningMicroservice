package top.erzhiqian.weixin.account.infrastructure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.account.infrastructure.po.WeixinAppAccountPO;

@Component
public class WeixinAppConvert {

    public WeixinAppAccountPO convertToPO(WeixinAppAccount weixinApp, WeixinAppAccountPO original) {
        original = null == original ? new WeixinAppAccountPO() : original;
        original.setAppId(weixinApp.getAppId().appId());
        original.setOriginalId(weixinApp.getOriginalId().appId());
        original.setAppName(weixinApp.getName().value());
        original.setWeixinId(weixinApp.getWeixinId().value());
        original.setAppName(weixinApp.getName().value());
        original.setAppType(weixinApp.getType().getCode());
        original.setCertifiedState(weixinApp.getState().getCode());
        original.setHostType(weixinApp.getHostType().getCode());
        return original;
    }

    public WeixinApp convertToEntity(WeixinAppAccountPO po){
        WeixinAppAccount account  =    new WeixinAppAccount
                .Builder(po.getAppId(),po.getAppName(),po.getAppType())
                .originalId(po.getOriginalId())
                .weixinId(po.getWeixinId())
                .state(po.getCertifiedState())
                .hostType(po.getHostType())
                .build();
        return new WeixinApp(account);
    }
}
