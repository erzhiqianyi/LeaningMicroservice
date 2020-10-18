package top.erzhiqian.weixin.security.app;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.IAccessToken;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.AppSecretRepository;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class AccessTokenAppTest {

    @Autowired
    private AccessTokenApp accessTokenApp;


    @Autowired
    private AppSecretRepository repository;

    private AppSecret appSecret;

    @Autowired
    private IAccessToken accessToken;

    @Before
    public void init() {
        String appId = "wx7886d971aa7bcde7";
        appSecret = repository.findAppSecret(WeixinAppId.app(appId));
    }

    @Test
    public void refreshAccessToken() {
        accessTokenApp.refreshAccessToken(appSecret);
        AccessTokenString accessTokenString = accessToken.loadAccessToken(appSecret.getApp());
        assertNotNull(accessTokenString);
    }
}