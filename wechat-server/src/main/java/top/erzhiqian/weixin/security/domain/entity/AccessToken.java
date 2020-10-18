package top.erzhiqian.weixin.security.domain.entity;

import lombok.Getter;
import lombok.ToString;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.domain.valueobject.ExpiredTime;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;

@ToString
@Getter
public class AccessToken {

    private final WeixinAppId app;

    private AccessTokenString accessToken;

    private ExpiredTime expiredTime;

    public static AccessToken appAccessToken(WeixinAppId app) {
        return new AccessToken(app);
    }


    private AccessToken(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException(" illegal app.");
        }
        this.app = app;
    }

    public void setToken(AccessTokenDTO accessToken) {
        if (null == accessToken) {
            throw new IllegalArgumentException("illegal access token.");
        }
        setAccessToken(new AccessTokenString(accessToken.getAccessToken()));
        setExpiredTime(new ExpiredTime(System.currentTimeMillis(), accessToken.getExpiresIn()));
    }

    private void setAccessToken(AccessTokenString accessToken) {
        this.accessToken = accessToken;
    }

    private void setExpiredTime(ExpiredTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public boolean expired() {
        return null == expiredTime || expiredTime.expired();
    }


    public void restoreToken(String accessToken, Long createAt, Integer timeToLive) {
        setAccessToken(new AccessTokenString(accessToken));
        setExpiredTime(new ExpiredTime(createAt, timeToLive));
    }



}
