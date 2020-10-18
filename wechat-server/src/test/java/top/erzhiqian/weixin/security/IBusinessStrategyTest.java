package top.erzhiqian.weixin.security;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

import java.util.stream.Stream;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class IBusinessStrategyTest {

    @Autowired
    private IBusinessStrategy businessStrategy;

    private WeixinAppId app;
    @Before
    public void init(){
        app = WeixinAppId.app("wx4ff4f9c7af819999");
    }
    @Test
    public void accessTokenStrategy() {
        IAccessToken strategy =
                businessStrategy.getBusinessStrategy(app,BusinessType.GET_ACCESS_TOKEN.getCode(),
                        IAccessToken.class);
        assertNotNull(strategy);
    }

    @Test
    public void getBusinessStrategy() {
        Stream.of(BusinessType.values())
                .forEach(item -> {
                    Object o = businessStrategy.getBusinessStrategy(app,item.getCode(), item.strategy());
                    assertNotNull(o);
                });
    }
}