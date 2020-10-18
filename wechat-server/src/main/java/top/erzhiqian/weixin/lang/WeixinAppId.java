package top.erzhiqian.weixin.lang;

import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
public class WeixinAppId {
    private static final int MAX_LENGTH = 50;

    private final String appId;

    private WeixinAppId(String appId) {
        if (StringUtils.isEmpty(appId)) {
            throw new IllegalArgumentException("illegal appId");
        }
        if (appId.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("illegal appId");
        }
        this.appId = appId;
    }

    public static WeixinAppId app(String appId) {
        return new WeixinAppId(appId);
    }

    public String appId() {
        return appId;
    }
}
