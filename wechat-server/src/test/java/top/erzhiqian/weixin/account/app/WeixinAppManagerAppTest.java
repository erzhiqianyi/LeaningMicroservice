package top.erzhiqian.weixin.account.app;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.account.client.cmd.WeixinAppRegisterCmd;
import top.erzhiqian.weixin.account.domain.entity.WeixinApp;
import top.erzhiqian.weixin.account.domain.repository.WeixinAppRepository;
import top.erzhiqian.weixin.account.domain.valueobject.*;
import top.erzhiqian.weixin.lang.WeixinAppId;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class WeixinAppManagerAppTest {

    @Autowired
    private WeixinAppManagerApp managerApp;


    @Autowired
    private WeixinAppRepository repository;

    private HostAccountId hostAccountId;

    private WeixinAppRegisterCmd registerCmd;

    private Optional<WeixinApp> weixinApp;

    @Before
    public void init() {
        hostAccountId = new HostAccountId("g_12033234");

        String[] registerInfo = {
                "SUBSCRIBE",
                "gh_67663cf2ed33",
                "wx9a026bc04a865227",
                "二之前士多测试账号",
                "erzhiqianstore",
                "UN_CERTIFIED",
                "PERSONAL"
        };
        registerCmd = new WeixinAppRegisterCmd();
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
        managerApp.registerWeixinApp(hostAccountId, registerCmd);
        weixinApp = repository.findWeixinApp(WeixinAppId.app(registerCmd.getAppId()));
        assertTrue(weixinApp.isPresent());
        assertFalse(weixinApp.get().certified());
    }


    @Test
    public void personalMiniProgram() {
        registerCmd.setAppType(WeixinAppType.MINI_PROGRAM.getCode());

    }

    @Test
    public void certifiedSubscribe() {
        registerCmd.setAppType(WeixinAppType.SUBSCRIBE.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.CERTIFIED.getCode());

    }

    @Test
    public void uncertifiedSubscribe() {
        registerCmd.setAppType(WeixinAppType.SUBSCRIBE.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.UN_CERTIFIED.getCode());

    }


    @Test
    public void certifiedServiceApp() {
        registerCmd.setAppType(WeixinAppType.SERVICES.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.CERTIFIED.getCode());

    }

    @Test
    public void uncertifiedServiceApp() {
        registerCmd.setAppType(WeixinAppType.SERVICES.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.UN_CERTIFIED.getCode());

    }

    @Test
    public void certifiedMiniProgram() {
        registerCmd.setAppType(WeixinAppType.MINI_PROGRAM.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.CERTIFIED.getCode());

    }

    @Test
    public void uncertifiedMiniProgram() {
        registerCmd.setAppType(WeixinAppType.MINI_PROGRAM.getCode());
        registerCmd.setHostType(WeixinAccountHostType.COMPANY.getCode());
        registerCmd.setCertifiedStatus(WeixinCertifiedState.UN_CERTIFIED.getCode());


    }




}