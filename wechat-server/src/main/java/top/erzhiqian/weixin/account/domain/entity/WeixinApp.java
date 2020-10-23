package top.erzhiqian.weixin.account.domain.entity;

import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;

public class WeixinApp extends AutoIncrementEntity {

    private final WeixinAppAccount weixinAccount;

    private final WeixinAppContext context;

    public WeixinApp(WeixinAppAccount weixinAccount) {
        if (null == weixinAccount) {
            throw new IllegalArgumentException("illegal app.");
        }
        this.weixinAccount = weixinAccount;
        this.context = new WeixinAppContext(weixinAccount);
    }


    public boolean certified() {
        return context.certified();
    }



}
