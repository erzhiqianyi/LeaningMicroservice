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
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategyEnum;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;
import top.erzhiqian.weixin.security.infrastrure.po.AppBusinessStrategyPO;
import top.erzhiqian.weixin.security.infrastrure.repository.jdbc.AppBusinessStrategyJdbcRepository;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class AccessTokenAppTest {

    @Autowired
    private AccessTokenApp accessTokenApp;

    private WeixinAppId app;


    @Autowired
    private AppBusinessStrategyJdbcRepository repository;

    @Before
    public void init() {
        Optional<AppBusinessStrategyPO> optional = repository.findByBusinessType(BusinessType.GET_ACCESS_TOKEN.getCode())
                .stream().filter(item -> BusinessStrategyEnum.isWeixinStrategy(
                        BusinessStrategyEnum.getStrategy(item.getStrategy())))
                .findAny();
        if (optional.isPresent()){
            app = WeixinAppId.app(optional.get().getAppId());
        }
    }

    @Test
    public void refreshAccessToken() {
        if (null == app){
            log.error("app 不用刷新access token.");
            return;
        }
        accessTokenApp.refreshAccessToken(app);
        AccessTokenString accessToken = IAccessToken.accessToken(app);
        assertNotNull(accessToken);
        log.info(accessToken);
    }
}