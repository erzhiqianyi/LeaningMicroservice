package top.erzhiqian.weixin.security;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import top.erzhiqian.weixin.security.client.cmd.BusinessStrategySetting;
import top.erzhiqian.weixin.security.client.cmd.ChangeAppSecretCmd;
import top.erzhiqian.weixin.security.client.cmd.ChangeBusinessStrategyCmd;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategy;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessStrategyEnum;
import top.erzhiqian.weixin.security.domain.valueobject.BusinessType;

import java.util.Arrays;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusinessStrategyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String appId;

    @Before
    public void init() {
        appId = "wx4ff4f9c7af819999";
    }

    @Test
    public void listBusinessStrategy() throws Exception {
        String url = "/business/strategies/" + appId;
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(MockMvcResultHandlers.print())
                .andDo(item -> assertNotNull(item));

    }

    @Test
    public void setBusinessStrategy() throws Exception {
        String url = "/business/strategy/setting/" + appId;
        ChangeBusinessStrategyCmd cmd = new ChangeBusinessStrategyCmd();
        BusinessStrategySetting refreshToken = new BusinessStrategySetting();
        refreshToken.setBusinessType(BusinessType.REFRESH_ACCESS_TOKEN.getCode());
        refreshToken.setStrategy(BusinessStrategyEnum.WEIXIN_SERVER.getCode());
        cmd.setStrategies(Arrays.asList(refreshToken));
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(cmd)))
                .andDo(MockMvcResultHandlers.print())
                .andDo(item -> assertNotNull(item));
    }

    @Test
    public void emptyBusinessType() throws Exception {
        String url = "/business/strategy/setting/" + appId;
        ChangeBusinessStrategyCmd cmd = new ChangeBusinessStrategyCmd();
        BusinessStrategySetting refreshToken = new BusinessStrategySetting();
        refreshToken.setStrategy(BusinessStrategyEnum.WEIXIN_SERVER.getCode());
        cmd.setStrategies(Arrays.asList(refreshToken));
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(cmd)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andDo(item -> assertNotNull(item));
    }

    @Test
    public void emptyBusinessStrategy() throws Exception {
        String url = "/business/strategy/setting/" + appId;
        ChangeBusinessStrategyCmd cmd = new ChangeBusinessStrategyCmd();
        BusinessStrategySetting refreshToken = new BusinessStrategySetting();
        refreshToken.setBusinessType(BusinessType.REFRESH_ACCESS_TOKEN.getCode());
        cmd.setStrategies(Arrays.asList(refreshToken));
        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(cmd)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print())
                .andDo(item -> assertNotNull(item));

    }

}