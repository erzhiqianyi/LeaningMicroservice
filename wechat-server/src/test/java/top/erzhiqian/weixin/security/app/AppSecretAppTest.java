package top.erzhiqian.weixin.security.app;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.ChangeAppSecretCmd;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.AppSecretRepository;
import top.erzhiqian.weixin.security.domain.valueobject.AppSecretString;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class AppSecretAppTest {

    @Autowired
    private AppSecretApp appSecretApp;

    @Autowired
    private AppSecretRepository repository;

    private String appId;

    private String secret;

    @Before
    public void init() {
        Long now = System.currentTimeMillis();
        appId = "wx7886d971aa7bcde7" + now;
        secret = UUID.randomUUID().toString();
    }

    @Test
    public void changeAppSecret() {
        WeixinAppId app = WeixinAppId.app(appId);
        ChangeAppSecretCmd cmd = new ChangeAppSecretCmd();
        cmd.setAppSecret(secret);
        appSecretApp.changeAppSecret(app, cmd);
        AppSecret appSecret = repository.findAppSecret(app);
        assertNotNull(appSecret);
        appSecret.sameSecret(new AppSecretString(secret));
    }

    @Test
    public void sameSecret() {
        WeixinAppId app = WeixinAppId.app(appId);
        AppSecret appSecret = AppSecret.restore(null,app,secret);
        AppSecretString appSecretString = new AppSecretString(secret);
        boolean sameSecret = appSecret.sameSecret(appSecretString);
        assertTrue(sameSecret);
    }

    @Test
    public void notSameSecretWithEmptySecret() {
        WeixinAppId app = WeixinAppId.app(appId);
        AppSecret appSecret = AppSecret.restore(null,app,null);
        AppSecretString appSecretString = new AppSecretString(secret);
        boolean sameSecret = appSecret.sameSecret(appSecretString);
        assertFalse(sameSecret);
    }

    @Test
    public void notSameSecret(){
        WeixinAppId app = WeixinAppId.app(appId);
        AppSecret appSecret = AppSecret.restore(null,app,secret);
        AppSecretString appSecretString = new AppSecretString(secret+secret);
        boolean sameSecret = appSecret.sameSecret(appSecretString);
        assertFalse(sameSecret);
    }
}