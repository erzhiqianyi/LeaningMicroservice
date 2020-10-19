package top.erzhiqian.weixin.account.domain.entity;

import top.erzhiqian.weixin.account.domain.valueobject.HostAccountId;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HostWeixinApps {

    private final HostAccountId host;

    private Map<WeixinAppId, WeixinAppAccount> apps;

    private HostWeixinApps(HostAccountId host) {
        if (null == host) {
            throw new IllegalArgumentException("illegal host");
        }
        this.host = host;
        apps = new ConcurrentHashMap<>();
    }

    public static HostWeixinApps host(HostAccountId host) {
        return new HostWeixinApps(host);
    }

    public void addApp(WeixinAppAccount app) {
        if (null == app) {
            throw new IllegalArgumentException(" app can't be null.");
        }
        apps.put(app.getAppId(), app);
    }


    public void deleteApp(WeixinAppAccount app){
       if (null == app){
           throw new IllegalArgumentException(" app can't be null.");
       }
       apps.remove(app.getAppId());
    }
}
