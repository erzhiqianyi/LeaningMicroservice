package top.erzhiqian.weixin.security.domain.entity;

import org.junit.Before;
import org.junit.Test;
import top.erzhiqian.weixin.lang.UrlLink;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategyEnum;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

import static org.junit.Assert.*;

public class WeixinAppBusinessStrategyEnumTest {

    private AppBusinessStrategy appBusinessStrategy;


    @Before
    public void init() {
        WeixinAppId app = WeixinAppId.app("wx4ff4f9c7af819999");
        appBusinessStrategy = new AppBusinessStrategy(app);

    }

    @Test
    public void weixinStrategy() {
        BusinessType getAccessToken = BusinessType.REFRESH_ACCESS_TOKEN;
        BusinessStrategyEnum weixinStrategy = BusinessStrategyEnum.WEIXIN_SERVER;
        appBusinessStrategy.registerStrategy(getAccessToken, weixinStrategy, null);
        String serviceName = weixinStrategy.getCode() + getAccessToken.getCode();
        assertEquals(serviceName, appBusinessStrategy.strategyName(getAccessToken));
    }

    @Test
    public void thirdStrategy() {
        BusinessType getAccessToken = BusinessType.REFRESH_ACCESS_TOKEN;
        BusinessStrategyEnum thirdStrategy = BusinessStrategyEnum.THIRD_SERVER;
        UrlLink link = new UrlLink("https://erzhiqian.top");
        appBusinessStrategy.registerStrategy(getAccessToken, thirdStrategy, link);
        String serviceName = thirdStrategy.getCode() + getAccessToken.getCode();
        assertEquals(serviceName, appBusinessStrategy.strategyName(getAccessToken));
    }
}