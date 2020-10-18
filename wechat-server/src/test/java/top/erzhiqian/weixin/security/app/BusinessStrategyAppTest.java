package top.erzhiqian.weixin.security.app;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.security.client.cmd.BusinessStrategySetting;
import top.erzhiqian.weixin.security.client.vo.BusinessStrategyVO;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategyEnum;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class BusinessStrategyAppTest {

    @Autowired
    private BusinessStrategyApp app;

    private WeixinAppId weixinAppId;

    @Before
    public void init() {
        weixinAppId = WeixinAppId.app("wx4ff4f9c7af819999");
    }

    @Test
    public void getBusinessStrategy() {
        List<BusinessStrategyVO> strategy = app.listBusinessStrategy(weixinAppId);
        assertNotNull(strategy);
        strategy.forEach(item -> log.info(item));
    }


    @Test
    public void setBusinessStrategy() {
        List<BusinessStrategySetting> settings = new ArrayList<>();

        BusinessStrategySetting refreshToken = new BusinessStrategySetting();
        refreshToken.setBusinessType(BusinessType.GET_ACCESS_TOKEN.getCode());
        refreshToken.setStrategy(BusinessStrategyEnum.WEIXIN_SERVER.getCode());

        BusinessStrategySetting generateCode = new BusinessStrategySetting();
        generateCode.setBusinessType(BusinessType.GENERATE_QR_CODE.getCode());
        generateCode.setStrategy(BusinessStrategyEnum.THIRD_SERVER.getCode());
        generateCode.setCallBackUrl("https://erzhiqian.top/weixin/app/weixin/wx7886d971aa7bcde7");
        settings.add(refreshToken);
        settings.add(generateCode);

        app.setBusinessStrategy(weixinAppId, settings);
        List<BusinessStrategyVO> strategy = app.listBusinessStrategy(weixinAppId);
        assertNotNull(strategy);
        Map<String, BusinessStrategyVO> map = strategy
                .stream()
                .collect(toMap(item -> item.getBusinessType().getCode(), item -> item));
        settings.stream()
                .forEach(item -> {
                    BusinessStrategyVO vo = map.get(item.getBusinessType());
                    assertEquals(item.getStrategy(),vo.getBusinessStrategy().getCode());
                    if (null != item.getCallBackUrl()){
                        assertEquals(item.getCallBackUrl(),vo.getUrlLink().url());
                    }
                });
    }


}