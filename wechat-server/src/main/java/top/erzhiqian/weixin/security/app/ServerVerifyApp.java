package top.erzhiqian.weixin.security.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.security.client.cmd.WeixinVerifyMessageCmd;
import top.erzhiqian.weixin.security.domain.entity.WeixinApp;
import top.erzhiqian.weixin.security.domain.repository.WeixinServerVerifyTokenRepository;
import top.erzhiqian.weixin.security.domain.valueobject.ICheckMessageToken;

@Component
public class ServerVerifyApp {

    private WeixinServerVerifyTokenRepository repository;

    public ServerVerifyApp(WeixinServerVerifyTokenRepository repository) {
        this.repository = repository;
    }

    public boolean checkMessage(WeixinApp app, WeixinVerifyMessageCmd cmd) {
        ICheckMessageToken checkMessageToken = repository.findServerVerifyToken(app);
        return checkMessageToken.checkMessage(cmd);
    }
}
