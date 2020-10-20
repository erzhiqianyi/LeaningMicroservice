package top.erzhiqian.weixin.account.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.account.client.cmd.WeixinAppRegisterCmd;
import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.entity.WeixinAppHost;
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

    public void registerWeixinApp(HostAccountId host, WeixinAppRegisterCmd cmd) {
        WeixinAppId appId = WeixinAppId.app(cmd.getAppId());
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
        WeixinAppHost appHost = WeixinAppHost.hostApp(host, weixinAccount);
        hostRepository.save(appHost);
        weixinAppRepository.save(weixinAccount);
    }
}
