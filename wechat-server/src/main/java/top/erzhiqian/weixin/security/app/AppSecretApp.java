package top.erzhiqian.weixin.security.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.ChangeAppSecretCmd;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.AppSecretRepository;

@Component
public class AppSecretApp {

    private AppSecretRepository repository;

    public AppSecretApp(AppSecretRepository repository) {
        this.repository = repository;
    }

    public void changeAppSecret(WeixinAppId app, ChangeAppSecretCmd cmd) {
        AppSecret appSecret = repository.findAppSecret(app);
        appSecret.changeSecret(cmd.getAppSecret());
        repository.saveAppSecret(appSecret);
    }
}
