package top.erzhiqian.weixin.security;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.entity.AppBusinessStrategy;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.domain.repository.AppSecretRepository;
import top.erzhiqian.weixin.security.domain.valueobject.AccessTokenString;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;
import top.erzhiqian.weixin.security.infrastrure.po.AppBusinessStrategyPO;
import top.erzhiqian.weixin.security.infrastrure.repository.jdbc.AppBusinessStrategyJdbcRepository;
import top.erzhiqian.weixin.security.infrastrure.repository.jdbc.AppSecretJdbcRepository;

import javax.swing.plaf.synth.SynthTreeUI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
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