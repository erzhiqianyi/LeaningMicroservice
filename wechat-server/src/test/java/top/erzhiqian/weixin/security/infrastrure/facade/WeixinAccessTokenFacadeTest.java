package top.erzhiqian.weixin.security.infrastrure.facade;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.security.dto.AccessTokenDTO;
import top.erzhiqian.weixin.security.infrastrure.facade.param.AccessTokenQueryParam;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class WeixinAccessTokenFacadeTest {

    @Autowired
    private WeixinAccessTokenFacade facade;


    @Test
    public void getAccessToken() {
        String appId = "wx9a026bc04a865227";
        String appSecret = "e67afb896a61321ea6652f67ef0af46b";
        AccessTokenQueryParam param = new AccessTokenQueryParam(appId, appSecret);
        AccessTokenDTO result = facade.getAccessToken(param);
        assertNotNull(result);
        log.info(result);
    }
}