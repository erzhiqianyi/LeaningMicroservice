package top.erzhiqian.weixin.account.domain.valueobject.state;


import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;

/**
 * 已认证非个人订阅号
 */
public class CertifiedOrganizationSubscribe extends WeixinAppState {

    public CertifiedOrganizationSubscribe(WeixinAppContext weixinAppContext) {
        setContext(weixinAppContext);
    }

    @Override
    public boolean certified() {
        return true;
    }
}
