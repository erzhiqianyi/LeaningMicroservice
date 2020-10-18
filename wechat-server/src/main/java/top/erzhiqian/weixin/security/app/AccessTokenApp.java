package top.erzhiqian.weixin.security.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AccessToken;
import top.erzhiqian.weixin.security.domain.repository.AccessTokenRepository;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;
import top.erzhiqian.weixin.security.service.impl.WeixinServerAccessTokenStrategy;

import java.util.Optional;

@Component
public class AccessTokenApp {

    private static final BusinessType BUSINESS = BusinessType.GET_ACCESS_TOKEN;

    private WeixinServerAccessTokenStrategy accessTokenStrategy;

    private AccessTokenRepository repository;

    private BusinessStrategyApp strategyApp;

    public AccessTokenApp(WeixinServerAccessTokenStrategy accessTokenStrategy,
                          AccessTokenRepository repository,
                          BusinessStrategyApp strategyApp) {
        this.accessTokenStrategy = accessTokenStrategy;
        this.repository = repository;
        this.strategyApp = strategyApp;
    }

    public void refreshAccessToken(WeixinAppId app) {
        if (null == app) {
            throw new IllegalStateException("illegal app.");
        }
        BusinessStrategy strategy = strategyApp.getBusinessStrategy(app, BUSINESS);
        if (!strategy.isWeixinBusiness()) {
            throw new IllegalStateException(" app  strategy not weixin strategy.");
        }
        Optional<AccessTokenDTO> optional = accessTokenStrategy.refreshAccessToken(app);
        optional.orElseThrow(() -> new IllegalStateException(" refresh access token fail."));
        AccessTokenDTO accessTokenDTO = optional.get();
        AccessToken accessToken = AccessToken.appAccessToken(app);
        accessToken.setToken(accessTokenDTO);
        repository.cacheAccessToken(accessToken);
    }
}
