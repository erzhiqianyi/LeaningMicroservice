package top.erzhiqian.weixin.account.domain.entity;

import org.junit.Test;
import top.erzhiqian.weixin.account.client.cmd.WeixinAppRegisterCmd;
import top.erzhiqian.weixin.account.domain.valueobject.HostAccountId;

public class WeixinAppTest {

    @Test
    public void personalRegister() {
        String hostId = "p_10034334" ;
        HostAccountId hostAccountId = new HostAccountId(hostId) ;
        WeixinApp app = WeixinApp.app(hostAccountId);
        WeixinAppRegisterCmd registerCmd = new WeixinAppRegisterCmd();
        app.register(registerCmd);

    }


}