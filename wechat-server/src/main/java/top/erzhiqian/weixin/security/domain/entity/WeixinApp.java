package top.erzhiqian.weixin.security.domain.entity;

import org.springframework.util.StringUtils;

public class WeixinApp extends AutoIncrementEntity {
    private final String appId;

    public WeixinApp(String appId) {
        if (StringUtils.isEmpty(appId)) {
            throw new IllegalArgumentException(" illegal appId");
        }
        this.appId = appId;
    }

    public static WeixinApp app(String appId) {
        return new WeixinApp(appId);
    }

    public String appId() {
        return appId;
    }
}
