package top.erzhiqian.weixin.account.domain.valueobject.state;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;

/**
 * 已认证小程序
 */
public class CertifiedMiniProgram extends WeixinAppState{

    public CertifiedMiniProgram(WeixinAppContext weixinAppContext) {
        setContext(weixinAppContext);
    }

    @Override
    public boolean certified() {
        return true;
    }
}
