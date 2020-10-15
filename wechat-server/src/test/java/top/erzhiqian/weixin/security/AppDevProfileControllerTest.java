package top.erzhiqian.weixin.security;

import com.alibaba.fastjson.JSON;
import net.minidev.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import top.erzhiqian.weixin.lang.LetterOrDigitsString;
import top.erzhiqian.weixin.security.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.security.domain.valueobject.MessageDataType;
import top.erzhiqian.weixin.security.domain.valueobject.MessageEncodingKey;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppDevProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void init() {
    }

    @Test
    public void generateLetterOrDigitsString() throws Exception {
        String generateLetterOrDigitsUrl = "/util/letter_or_digits";
        mockMvc.perform(MockMvcRequestBuilders.get(generateLetterOrDigitsUrl)
                .param("min", "4")
                .param("max", "32"))
                .andDo(MockMvcResultHandlers.print())
                .andDo(item -> assertNotNull(item));
    }


    @Test
    public void openAppDevProfile() throws Exception {
        String appId = UUID.randomUUID().toString();
        String openDevProfileUrl = "/devprofile/open/" + appId;

        OpenAppDevProfileCmd cmd = new OpenAppDevProfileCmd();
        cmd.setServerUrl("https://erzhiqian.top/wechat/server/verify/wx3776b9225ad085af");
        cmd.setServerToken("4324325646DDF3432");
        cmd.setAesKey(LetterOrDigitsString.ofLength(43).getValue());
        cmd.setEncryptionMethod(MessageEncodingKey.EncodingType.ENCRYPT.getCode());
        cmd.setDataType(MessageDataType.DataType.JSON.name());
        mockMvc.perform(MockMvcRequestBuilders.post(openDevProfileUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(cmd)))
                .andDo(MockMvcResultHandlers.print())
                .andDo(item -> assertNotNull(item));

    }
}