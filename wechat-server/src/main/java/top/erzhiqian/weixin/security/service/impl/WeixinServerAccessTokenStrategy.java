package top.erzhiqian.weixin.security.service.impl;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.IAccessToken;
import top.erzhiqian.weixin.security.domain.entity.AccessToken;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.AccessTokenRepository;
import top.erzhiqian.weixin.security.domain.repository.AppSecretRepository;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.domain.valueobject.AppSecretString;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;
import top.erzhiqian.weixin.security.infrastrure.facade.WeixinAccessTokenFacade;
import top.erzhiqian.weixin.security.infrastrure.facade.param.AccessTokenQueryParam;

import java.util.Optional;

@Service("weixinGetAccessToken")
public class WeixinServerAccessTokenStrategy implements IAccessToken {

    private AccessTokenRepository accessTokenRepository;

    private AppSecretRepository secretRepository;

    private WeixinAccessTokenFacade accessTokenFacade;

    public WeixinServerAccessTokenStrategy(
            AccessTokenRepository accessTokenRepository,
            AppSecretRepository secretRepository,
            WeixinAccessTokenFacade accessTokenFacade) {
        this.accessTokenRepository = accessTokenRepository;
        this.secretRepository = secretRepository;
        this.accessTokenFacade = accessTokenFacade;
    }

    @Override
    public AccessTokenString loadAccessToken(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException("illegal app.");
        }
        AccessToken accessToken = accessTokenRepository.findAccessToken(app);

        if (accessToken.expired()) {
            //todo 发邮件处理，并重新获取access token
            throw new IllegalStateException(" token expired.");
        }
        return accessToken.getAccessToken();
    }

    public Optional<AccessTokenDTO> refreshAccessToken(WeixinAppId app) {
        if (null == app) {
            throw new IllegalArgumentException("illegal app.");
        }
        AppSecret appSecret = secretRepository.findAppSecret(app);
        AppSecretString secret = appSecret.getAppSecret();
        if (null == secret) {
            throw new IllegalArgumentException(" illegal app secret.");
        }

        AccessTokenQueryParam param = new AccessTokenQueryParam(appSecret.getApp().appId(),
                appSecret.getAppSecret().secret());
        AccessTokenDTO accessToken = accessTokenFacade.getAccessToken(param);
        if (null != accessToken){
            return Optional.of(accessToken);
        }else {
            return Optional.empty();
        }
    }
}
