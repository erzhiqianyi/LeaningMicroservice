package top.erzhiqian.weixin.security;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;
import top.erzhiqian.weixin.security.infrastrure.po.AppBusinessStrategyPO;
import top.erzhiqian.weixin.security.infrastrure.repository.jdbc.AppBusinessStrategyJdbcRepository;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class IAccessTokenTest {

    private WeixinAppId appId;

    @Autowired
    private AppBusinessStrategyJdbcRepository strategyRepository;

    @Before
    public void init() {
        List<AppBusinessStrategyPO> businessStrategies = strategyRepository
                .findByBusinessType(BusinessType.GET_ACCESS_TOKEN.getCode());
        assertFalse(businessStrategies.isEmpty());
        AppBusinessStrategyPO businessStrategy = businessStrategies.stream()
                .findAny().get();
        appId = WeixinAppId.app(businessStrategy.getAppId());

    }

    @Test
    public void accessToken() {
        AccessTokenString accessToken = IAccessToken.accessToken(appId);
        assertNotNull(accessToken);
    }
}