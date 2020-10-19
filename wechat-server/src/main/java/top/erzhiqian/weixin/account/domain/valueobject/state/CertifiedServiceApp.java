package top.erzhiqian.weixin.account.domain.valueobject.state;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;

/**
 * 已认证非服务号
 */
public class CertifiedServiceApp extends WeixinAppState {
    public CertifiedServiceApp(WeixinAppContext weixinAppContext) {
        setContext(weixinAppContext);
    }

    @Override
    public boolean certified() {
        return true;
    }
}
