package top.erzhiqian.wechat.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import top.erzhiqian.wechat.security.client.cmd.WeixinVerifyMessageCmd;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeixinVerifyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String verifyMessageUrl;

    private String appId;

    @Before
    public void init() {
        verifyMessageUrl = "/weixin/message/verify/";
        appId = "3253454";
    }

    @Test
    public void verifyWeixinMessage() throws Exception {
        WeixinVerifyMessageCmd cmd = new WeixinVerifyMessageCmd();
        cmd.setSignature("432");
        cmd.setEchostr("234");
        cmd.setTimestamp(System.currentTimeMillis());
        cmd.setNonce(3343);
        verifyMessageUrl = verifyMessageUrl + appId;
        mockMvc.perform(MockMvcRequestBuilders.get(verifyMessageUrl)
                .param("signature", cmd.getSignature())
                .param("echostr", cmd.getEchostr())
                .param("timestamp", String.valueOf(cmd.getTimestamp()))
                .param("nonce", String.valueOf(cmd.getNonce()))
        ).andExpect(MockMvcResultMatchers.content().string(cmd.getEchostr()))
                .andDo(MockMvcResultHandlers.print())
        ;

    }
}