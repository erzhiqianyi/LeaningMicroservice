package top.erzhiqian.weixin.account.domain.valueobject.state;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;


/**
 * 未认证非个人订阅号
 */
public class UnCertifiedServiceApp extends WeixinAppState {
    public UnCertifiedServiceApp(WeixinAppContext weixinAppContext) {
       setContext(weixinAppContext);
    }

    @Override
    public boolean certified() {
        return false;
    }
}
