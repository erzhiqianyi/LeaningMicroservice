package top.erzhiqian.weixin.security.domain.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.security.domain.entity.WeixinApp;
import top.erzhiqian.weixin.security.domain.valueobject.ICheckMessageToken;
import top.erzhiqian.weixin.security.infrastructure.po.WeiXinMessagePushSettingPO;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class WeixinServerVerifyTokenRepositoryTest {

    @Autowired
    private WeixinServerVerifyTokenRepository repository;

    private String appId;

    private WeixinApp weixinApp;

    @Before
    public void init() {
        appId = "234325";
        weixinApp = WeixinApp.app(appId);
    }

    @Test
    public void findServerVerifyToken() {
        ICheckMessageToken token = repository.findServerVerifyToken(weixinApp);
        assertNotNull(token);
    }


}