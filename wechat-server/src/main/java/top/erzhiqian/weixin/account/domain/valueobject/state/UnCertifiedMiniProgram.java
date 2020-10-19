package top.erzhiqian.weixin.account.domain.valueobject.state;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;

/**
 * 未认证小程序
 */
public class UnCertifiedMiniProgram  extends WeixinAppState{
    public UnCertifiedMiniProgram(WeixinAppContext weixinAppContext) {
        setContext(weixinAppContext);
    }

    @Override
    public boolean certified() {
        return false;
    }
}
