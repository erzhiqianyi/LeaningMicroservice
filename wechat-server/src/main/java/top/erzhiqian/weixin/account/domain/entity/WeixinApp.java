package top.erzhiqian.weixin.account.domain.entity;

import lombok.Getter;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;

@Getter
public class WeixinApp {

    private final WeixinAppAccount weixinAccount;

    private final WeixinAppContext context;

    public WeixinApp(WeixinAppAccount weixinAccount) {
        if ( null == weixinAccount) {
            throw new IllegalArgumentException("illegal app.");
        }
        this.weixinAccount = weixinAccount;
        this.context =  new WeixinAppContext(weixinAccount);
    }





}
