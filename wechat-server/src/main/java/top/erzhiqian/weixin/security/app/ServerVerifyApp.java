package top.erzhiqian.weixin.security.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.security.client.cmd.WeixinVerifyMessageCmd;
import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;
import top.erzhiqian.weixin.security.domain.repository.WeixinServerTokenRepository;
import top.erzhiqian.weixin.security.domain.valueobject.IWeixinServerToken;

@Component
public class ServerVerifyApp {

    private WeixinServerTokenRepository repository;

    public ServerVerifyApp(WeixinServerTokenRepository repository) {
        this.repository = repository;
    }

    public boolean checkMessage(WeixinAppId app, WeixinVerifyMessageCmd cmd) {
        IWeixinServerToken checkMessageToken = repository.findWeixinServerToken(app);
        return checkMessageToken.checkMessage(cmd);
    }
}
