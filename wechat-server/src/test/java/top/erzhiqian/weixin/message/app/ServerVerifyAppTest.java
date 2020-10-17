package top.erzhiqian.weixin.message.app;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.message.client.cmd.WeixinVerifyMessageCmd;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.infrastructure.po.AppDevProfilePO;
import top.erzhiqian.weixin.message.infrastructure.repository.jdbc.AppDevProfileJdcRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ServerVerifyAppTest {

    @Autowired
    private ServerVerifyApp verifyApp;

    @Autowired
    private AppDevProfileJdcRepository repository;


    private WeixinVerifyMessageCmd cmd;

    private String appId;

    @Before
    public void init() {
        initVerifyCmd();
    }

    private void initVerifyCmd() {
        List<AppDevProfilePO> devProfilePOS = (List<AppDevProfilePO>) repository.findAll();
        AppDevProfilePO po = devProfilePOS
                .stream()
                .findFirst().get();
        appId = po.getAppId();
        String nonce = "234234";
        String echostr = "324ret";
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String sortedValue = Stream
                .of(po.getServerToken(), time, nonce)
                .sorted()
                .collect(Collectors.joining());
        String sign = DigestUtils.sha1Hex(sortedValue);
        cmd = new WeixinVerifyMessageCmd();
        cmd.setSignature(sign);
        cmd.setEchostr(echostr);
        cmd.setTimestamp(time);
        cmd.setNonce(nonce);

    }

    @Test
    public void validMessage() {
        WeixinAppId app = WeixinAppId.app(appId);
        boolean result = verifyApp.checkMessage(app, cmd);
        assertTrue(result);
    }

    @Test
    public void invalidMessage(){
        cmd.setSignature("dfdsfdfjsalfjdlsjfkldsd");
        WeixinAppId app = WeixinAppId.app(appId);
        boolean result = verifyApp.checkMessage(app, cmd);
        assertFalse(result);
    }
}