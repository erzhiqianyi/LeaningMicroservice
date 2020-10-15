package top.erzhiqian.weixin.security;

import org.apache.commons.codec.digest.DigestUtils;
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
import top.erzhiqian.weixin.security.client.cmd.WeixinVerifyMessageCmd;
import top.erzhiqian.weixin.security.infrastructure.po.AppDevProfilePO;
import top.erzhiqian.weixin.security.infrastructure.repository.jdbc.AppDevProfileJdcRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeixinVerifyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppDevProfileJdcRepository repository;

    private String verifyMessageUrl;

    private String appId;


    WeixinVerifyMessageCmd cmd;

    @Before
    public void init() {
        initVerifyCmd();
        verifyMessageUrl = "/app/weixin/" + appId;
    }

    private void initVerifyCmd() {
        List<AppDevProfilePO> devProfilePOS = (List<AppDevProfilePO>) repository.findAll();
        AppDevProfilePO po = devProfilePOS
                .stream()
                .findFirst().get();
        appId = po.getAppId();
        String nonce = "234234";
        String echostr = "324ret";
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String sortedValue = Stream
                .of(po.getServerToken(), time, nonce)
                .sorted()
                .collect(Collectors.joining());
        String sign = DigestUtils.sha1Hex(sortedValue);
        cmd = new WeixinVerifyMessageCmd();
        cmd.setSignature(sign);
        cmd.setEchostr(echostr);
        cmd.setTimestamp(time);
        cmd.setNonce(nonce);

    }

    @Test
    public void verifyWeixinMessage() throws Exception {
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