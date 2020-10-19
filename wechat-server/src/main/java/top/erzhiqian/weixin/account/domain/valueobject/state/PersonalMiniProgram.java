package top.erzhiqian.weixin.account.domain.valueobject.state;

import top.erzhiqian.weixin.account.domain.entity.WeixinAppContext;

/**
 * 个人小程序
 */
public class PersonalMiniProgram extends WeixinAppState  {

    public PersonalMiniProgram(WeixinAppContext weixinAppContext) {
        setContext(weixinAppContext);
    }

    @Override
    public boolean certified() {
        return false;
    }

}
