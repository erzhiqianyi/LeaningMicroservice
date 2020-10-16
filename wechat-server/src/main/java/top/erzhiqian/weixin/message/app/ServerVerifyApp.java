package top.erzhiqian.weixin.message.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.client.cmd.WeixinVerifyMessageCmd;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.repository.WeixinServerTokenRepository;
import top.erzhiqian.weixin.message.domain.valueobject.IWeixinServerToken;

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
