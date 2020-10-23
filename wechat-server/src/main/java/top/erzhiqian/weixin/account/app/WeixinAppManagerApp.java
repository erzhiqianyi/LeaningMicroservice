package top.erzhiqian.weixin.account.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.account.client.cmd.WeixinAppRegisterCmd;
import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.entity.AppHost;
import top.erzhiqian.weixin.account.domain.repository.WeixinAppHostRepository;
import top.erzhiqian.weixin.account.domain.repository.WeixinAppRepository;
import top.erzhiqian.weixin.account.domain.valueobject.HostAccountId;
import top.erzhiqian.weixin.account.domain.valueobject.WeixinAppAccount;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Optional;

@Component
public class WeixinAppManagerApp {

    private WeixinAppRepository weixinAppRepository;

    private WeixinAppHostRepository hostRepository;

    public WeixinAppManagerApp(WeixinAppRepository weixinAppRepository,
                               WeixinAppHostRepository hostRepository) {
        this.weixinAppRepository = weixinAppRepository;
        this.hostRepository = hostRepository;
    }

    public void registerWeixinApp(HostAccountId hostId, WeixinAppRegisterCmd cmd) {
        WeixinAppId appId = WeixinAppId.app(cmd.getAppId());
        WeixinApp app = weixinAppRepository.findWeixinApp(appId);
        /**
        //todo 判断app是否已经存在
        Optional<AppHost> optional = hostRepository.findWeixinAppHost(WeixinAppId.app(cmd.getAppId()));
        if (optional.isPresent()) {
            throw new IllegalArgumentException("app already register.");
        }

        host.registerApp();
        Optional<WeixinApp> optional = weixinAppRepository.findWeixinApp(appId);
        if (optional.isPresent()) {
            throw new IllegalArgumentException("weixin app already exists.");
        }
        WeixinAppAccount weixinAccount = new WeixinAppAccount
                .Builder(cmd.getAppId(), cmd.getAppName(), cmd.getAppType())
                .originalId(cmd.getOriginalId())
                .weixinId(cmd.getWeixinId())
                .state(cmd.getCertifiedStatus())
                .hostType(cmd.getHostType())
                .build();
        AppHost appHost = null;
//        AppHost.registerAccount(host, weixinAccount);
        hostRepository.save(appHost);
        weixinAppRepository.save(weixinAccount);
         */
    }
}
