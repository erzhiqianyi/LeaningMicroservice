package top.erzhiqian.weixin.security.domain.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.security.domain.entity.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.IWeixinServerToken;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class WeixinServerTokenRepositoryTest {

    @Autowired
    private WeixinServerTokenRepository repository;

    private String appId;

    private WeixinAppId weixinAppId;

    @Before
    public void init() {
        appId = "234325";
        weixinAppId = WeixinAppId.app(appId);
    }

    @Test
    public void findServerVerifyToken() {
        IWeixinServerToken token = repository.findWeixinServerToken(weixinAppId);
        assertNotNull(token);
    }


}