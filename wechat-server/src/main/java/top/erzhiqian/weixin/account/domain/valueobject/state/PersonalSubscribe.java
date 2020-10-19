package top.erzhiqian.weixin.account.domain.valueobject.state;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;

/**
 * 个人订阅号
 */
public class PersonalSubscribe extends WeixinAppState {

    public PersonalSubscribe(WeixinAppContext weixinAppContext) {
        setContext(weixinAppContext);
    }

    @Override
    public boolean certified() {
        return false;
    }

}
