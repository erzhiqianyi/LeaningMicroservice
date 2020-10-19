package top.erzhiqian.weixin.account.domain.entity;

import top.erzhiqian.weixin.account.client.cmd.WeixinAppRegisterCmd;
import top.erzhiqian.weixin.account.domain.valueobject.HostAccountId;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppRemark;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppStatus;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppType;
import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;
import top.erzhiqian.weixin.lang.WeixinAppId;

public class WeixinApp extends AutoIncrementEntity {

    private WeixinAppId appId;

    private WeixinAppId originalId;

    private WeixinAppType type;

    private WeixinAppStatus status;

    private WeixinAppRemark remark;

    private final HostAccountId hostAccount;


    public WeixinApp(HostAccountId hostAccount) {
        if (null == hostAccount){
            throw new IllegalArgumentException(" app host not exists.");
        }
        this.hostAccount = hostAccount;
    }

    public static WeixinApp app(HostAccountId hostAccount) {
        WeixinApp app = new WeixinApp(hostAccount);
        return app;
    }


    public void register(WeixinAppRegisterCmd cmd){


    }


}
