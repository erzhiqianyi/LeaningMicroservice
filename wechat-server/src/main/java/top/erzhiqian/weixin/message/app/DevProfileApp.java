package top.erzhiqian.weixin.message.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.message.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.message.domain.repository.AppDevProfileRepository;

@Component
public class DevProfileApp {

    private AppDevProfileRepository repository;

    public DevProfileApp(AppDevProfileRepository repository) {
        this.repository = repository;
    }

    public AppDevProfile openDevProfile(WeixinAppId app, OpenAppDevProfileCmd cmd) {
        AppDevProfile profile = repository.findAppDevProfile(app);
        profile.openDevProfile(cmd);
        repository.saveProfile(profile);
        return profile;
    }
}
