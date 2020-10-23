package top.erzhiqian.weixin.account.domain.entity;

import lombok.Getter;
import top.erzhiqian.weixin.account.domain.valueobject.HostAccountId;
import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;

@Getter
public class AppHost extends AutoIncrementEntity {

    private HostAccountId host;

    private WeixinApp app;

    private AppHost(HostAccountId host, WeixinApp app) {
        this.host = host;
        this.app = app;
    }

    public static AppHost hostApp(HostAccountId host, WeixinApp app) {
        return new AppHost(host, app);
    }


}
