package top.erzhiqian.weixin.account.domain.entity;

import top.erzhiqian.weixin.account.domain.service.WeixinAppStateHelper;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAccountHostType;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppType;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinCertifiedState;
import top.erzhiqian.weixin.account.domain.valueobject.state.WeixinAppState;
import top.erzhiqian.weixin.lang.WeixinAppId;

public class WeixinAppContext {

    private final WeixinAppId appId;

    private WeixinAppState state;

    private WeixinAppType type;

    private WeixinCertifiedState certifiedState;

    private WeixinAccountHostType hostType;


    public WeixinAppContext(WeixinAppAccount account) {
        if (null == account) {
            throw new IllegalArgumentException("illegal weixin app.");
        }
        this.appId = account.getAppId();
        this.type = account.getType();
        this.certifiedState = account.getState();
        this.hostType = account.getHostType();
        this.state = WeixinAppStateHelper.checkState(type, certifiedState, hostType,this);
    }


    public boolean certified() {
        return state.certified();
    }

}
