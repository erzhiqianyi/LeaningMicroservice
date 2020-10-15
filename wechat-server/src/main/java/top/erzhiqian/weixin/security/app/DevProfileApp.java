package top.erzhiqian.weixin.security.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.security.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.security.client.vo.AppDevProfileVO;
import top.erzhiqian.weixin.security.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;
import top.erzhiqian.weixin.security.domain.repository.AppDevProfileRepository;

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
