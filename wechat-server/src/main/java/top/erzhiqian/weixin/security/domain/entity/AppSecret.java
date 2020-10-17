package top.erzhiqian.weixin.security.domain.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;
import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.AppSecretString;

@Getter
public class AppSecret extends AutoIncrementEntity {

    private final WeixinAppId app;

    private AppSecretString appSecret;

    private AppSecret(WeixinAppId app, AppSecretString appSecret) {
        if (null == app) {
            throw new IllegalArgumentException("illegal app.");
        }
        this.app = app;
        if (null != appSecret) {
            setAppSecret(appSecret);
        }
    }

    public static AppSecret restore(Long id, WeixinAppId app, String secret) {
        AppSecretString secretString = StringUtils.isEmpty(secret) ? null :
                new AppSecretString(secret);
        AppSecret appSecret = new AppSecret(app, secretString);
        if (null != id) {
            appSecret.setId(id);
        }
        return appSecret;
    }

    public static AppSecret emptySecret(WeixinAppId app) {
        return restore(null, app, null);
    }

    public void changeSecret(String appSecret) {
        if (sameSecret(new AppSecretString(appSecret))) {
            return;
        }
        setAppSecret(new AppSecretString(appSecret));
    }

    private void setAppSecret(AppSecretString appSecret) {
        if (null == appSecret) {
            throw new IllegalArgumentException(" illegal app secret");
        }
        this.appSecret = appSecret;
    }


    public boolean sameSecret(AppSecretString other) {
        return null != appSecret ? appSecret.sameSecret(other) : false;
    }


}
