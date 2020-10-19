package top.erzhiqian.weixin.account.domain.entity;

import top.erzhiqian.weixin.account.domain.valueobject.HostAccountId;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;

public class WeixinAppHost extends AutoIncrementEntity {

    private final HostAccountId host;

    private final WeixinAppAccount weixinAccount;

    private WeixinAppHost(HostAccountId host, WeixinAppAccount app, Long id) {
        if (null == host) {
            throw new IllegalArgumentException("illegal host");
        }
        this.host = host;
        this.weixinAccount = app;
        if (null != id) {
            setId(id);
        }
    }

    public static WeixinAppHost hostApp(HostAccountId host, WeixinAppAccount weixinAccount) {
        return new WeixinAppHost(host, weixinAccount, null);
    }

    public static WeixinAppHost restoreApp(HostAccountId host, WeixinAppAccount weixinAccount, Long id) {
        return new WeixinAppHost(host, weixinAccount, id);
    }




}
