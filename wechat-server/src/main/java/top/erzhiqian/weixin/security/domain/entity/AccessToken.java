package top.erzhiqian.weixin.security.domain.entity;

import lombok.ToString;
import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.repository.IAccessTokenStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.domain.valueobject.ExpiredTime;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;

import java.time.Instant;
import java.util.Optional;

@ToString
public class AccessToken extends AutoIncrementEntity {

    private final WeixinAppId app;

    private AccessTokenString accessToken;

    private ExpiredTime expiredTime;

    public static AccessToken restoreAccessToken(WeixinAppId app, AccessTokenDTO dto) {
        AccessToken accessToken = new AccessToken(app);
        if (null != dto){
            accessToken.refreshToken(dto);
        }
        return accessToken;
    }

    private AccessToken(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException("illegal app.");
        }
        this.app = app;
    }

    public void refreshAccessToken(IAccessTokenStrategy strategy) {
        if (null == strategy) {
            throw new IllegalArgumentException("illegal business .");
        }
        Optional<AccessTokenDTO> optional = strategy.getAccessToken(app);
        optional.orElseThrow(() -> new IllegalStateException(" refresh access token failed."));
        refreshToken(optional.get());
    }

    private void refreshToken(AccessTokenDTO accessTokenDTO) {
        setAccessToken(new AccessTokenString(accessTokenDTO.getAccessToken()));
        setExpiredTime(new ExpiredTime(Instant.now(), accessTokenDTO.getTimeToLive()));
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
}
