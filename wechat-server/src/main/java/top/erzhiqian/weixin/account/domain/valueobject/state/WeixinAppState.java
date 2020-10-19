package top.erzhiqian.weixin.account.domain.valueobject.state;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;

public abstract class WeixinAppState {

    protected WeixinAppContext context;

    public abstract boolean certified();

    protected void setContext(WeixinAppContext context) {
        if (null == context){
            throw new IllegalArgumentException(" illegal context.");
        }
        this.context = context;
    }
}
