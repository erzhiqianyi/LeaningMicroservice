package top.erzhiqian.weixin.security.domain.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import top.erzhiqian.weixin.lang.UrlLink;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.BusinessStrategySetting;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategyEnum;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class AppBusinessStrategyTest {

    private AppBusinessStrategy appBusinessStrategy;


    @Before
    public void init() {
        WeixinAppId app = WeixinAppId.app("wx4ff4f9c7af819999");
        appBusinessStrategy = new AppBusinessStrategy(app);

    }

    @Test
    public void weixinStrategy() {
        BusinessType getAccessToken = BusinessType.GET_ACCESS_TOKEN;
        BusinessStrategyEnum weixinStrategy = BusinessStrategyEnum.WEIXIN_SERVER;
        appBusinessStrategy.registerStrategy(getAccessToken, weixinStrategy, null);
        String serviceName = weixinStrategy.getCode() + getAccessToken.getCode();
        assertEquals(serviceName, appBusinessStrategy.getStrategy(getAccessToken).strategyName());
    }

    @Test
    public void thirdStrategy() {
        BusinessType getAccessToken = BusinessType.GET_ACCESS_TOKEN;
        BusinessStrategyEnum thirdStrategy = BusinessStrategyEnum.THIRD_SERVER;
        UrlLink link = new UrlLink("https://erzhiqian.top");
        appBusinessStrategy.registerStrategy(getAccessToken, thirdStrategy, link);
        String serviceName = thirdStrategy.getCode() + getAccessToken.getCode();
        assertEquals(serviceName, appBusinessStrategy.getStrategy(getAccessToken).strategyName());
    }

    @Test
    public void registerStrategy() {
        String[] settingArrays = {
                "RefreshAccessToken,weixin",
                "GenerateQrCode,third,https://erzhiqian.top/weixin/app/weixin/wx4ff4f9c7af819999",
                "GetAppSecret,weixin",
                "CodeToSession,weixin"
        };
        List<BusinessStrategySetting> settings = Stream.of(settingArrays)
                .map(item -> item.split(","))
                .map(array -> {
                    BusinessStrategySetting setting = new BusinessStrategySetting();
                    setting.setBusinessType(array[0]);
                    setting.setStrategy(array[1]);
                    if (array.length == 3) {
                        setting.setCallBackUrl(array[2]);
                    }
                    return setting;
                })
                .collect(toList());
        appBusinessStrategy.registerStrategy(settings);
        settings.forEach(item -> {
            BusinessStrategy strategy = appBusinessStrategy.getStrategy(BusinessType.getBusinessType(item.getBusinessType()));
            assertNotNull(strategy);
            assertEquals(item.getStrategy(),strategy.getStrategy().getCode());
            if (StringUtils.isNotBlank(item.getCallBackUrl())){
                assertEquals(item.getCallBackUrl(),strategy.getCallback().url());
            }
        });
    }

}