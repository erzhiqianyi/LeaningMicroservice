package top.erzhiqian.weixin.security.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.ChangeAppSecretCmd;
import top.erzhiqian.weixin.security.domain.entity.AccessToken;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.AppSecretRepository;
import top.erzhiqian.weixin.security.service.impl.WeixinServerAccessTokenStrategy;

import java.util.Optional;

@Component
public class AppSecretApp {

    private AppSecretRepository repository;

    private AccessTokenApp accessTokenApp;

    public AppSecretApp(AppSecretRepository repository, AccessTokenApp accessTokenApp) {
        this.repository = repository;
        this.accessTokenApp = accessTokenApp;
    }

    public void changeAppSecret(WeixinAppId app, ChangeAppSecretCmd cmd) {
        AppSecret appSecret = repository.findAppSecret(app);
        appSecret.changeSecret(cmd.getAppSecret());
        accessTokenApp.refreshAccessToken(appSecret);
        repository.saveAppSecret(appSecret);
    }
}
