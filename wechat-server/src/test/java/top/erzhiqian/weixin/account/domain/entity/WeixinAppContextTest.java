package top.erzhiqian.weixin.account.domain.entity;

import org.junit.Before;
import org.junit.Test;
import top.erzhiqian.weixin.account.client.cmd.WeixinAppRegisterCmd;
import top.erzhiqian.weixin.account.domain.valueobject.*;

import static org.junit.Assert.*;

public class WeixinAppContextTest {

    private WeixinAppRegisterCmd registerCmd;

    private WeixinAppAccount.Builder builder;


    private HostAccountId hostAccountId;

    @Before
    public void init() {
        String hostId = "p_10034334";
        hostAccountId = new HostAccountId(hostId);
        registerCmd = new WeixinAppRegisterCmd();
        String[] registerInfo = {
                "SUBSCRIBE",
                "gh_67663cf2ed33",
                "wx9a026bc04a865227",
                "二之前士多测试账号",
                "erzhiqianstore",
                "UN_CERTIFIED",
                "PERSONAL"
        };
        registerCmd.setAppType(registerInfo[0]);
        registerCmd.setOriginalId(registerInfo[1]);
        registerCmd.setAppId(registerInfo[2]);
        registerCmd.setAppName(registerInfo[3]);
        registerCmd.setWeixinId(registerInfo[4]);
        registerCmd.setCertifiedStatus(registerInfo[5]);
        registerCmd.setHostType(registerInfo[6]);
    }

    @Test
    public void personalSubscribe() {
        registerCmd.setAppType(WeixinAppType.SUBSCRIBE.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertFalse(certified);
    }


    @Test
    public void personalMiniProgram() {
        registerCmd.setAppType(WeixinAppType.MINI_PROGRAM.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertFalse(certified);

    }

    @Test
    public void certifiedSubscribe() {
        registerCmd.setAppType(WeixinAppType.SUBSCRIBE.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.CERTIFIED.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertTrue(certified);

    }

    @Test
    public void uncertifiedSubscribe() {
        registerCmd.setAppType(WeixinAppType.SUBSCRIBE.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.UN_CERTIFIED.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertFalse(certified);


    }


    @Test
    public void certifiedServiceApp() {
        registerCmd.setAppType(WeixinAppType.SERVICES.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.CERTIFIED.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertTrue(certified);

    }

    @Test
    public void uncertifiedServiceApp() {
        registerCmd.setAppType(WeixinAppType.SERVICES.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.UN_CERTIFIED.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertFalse(certified);


    }

    @Test
    public void certifiedMiniProgram() {
        registerCmd.setAppType(WeixinAppType.MINI_PROGRAM.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.CERTIFIED.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertTrue(certified);

    }

    @Test
    public void uncertifiedMiniProgram() {
        registerCmd.setAppType(WeixinAppType.MINI_PROGRAM.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.UN_CERTIFIED.getCode());
        setBuilder();
        WeixinApp app = new WeixinApp( builder.build());
        assertNotNull(app);
        boolean certified = app.getContext().certified();
        assertFalse(certified);


    }



    private void setBuilder() {
        builder = new WeixinAppAccount
                .Builder(registerCmd.getAppId(), registerCmd.getAppName(), registerCmd.getAppType());
        builder.originalId(registerCmd.getOriginalId())
                .weixinId(registerCmd.getWeixinId())
                .state(registerCmd.getCertifiedStatus())
                .hostType(registerCmd.getHostType());


    }


}