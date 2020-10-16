package top.erzhiqian.weixin.message.app;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.lang.LetterOrDigitsString;
import top.erzhiqian.weixin.message.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.MessageDataType;
import top.erzhiqian.weixin.message.domain.valueobject.MessageEncodingKey;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class DevProfileAppTest {

    @Autowired
    private DevProfileApp app;


    String[] appProfile;

    @Before
    public void init() {
        appProfile = new String[]{
                "wx7886d971aa7bcde7",
                "https://erzhiqian.top/weixin/app/weixin/wx7886d971aa7bcde7",
                LetterOrDigitsString.ofLength(32).getValue(),
                LetterOrDigitsString.ofLength(43).getValue(),
                MessageEncodingKey.EncodingType.ENCRYPT.getCode(),
                MessageDataType.DataType.JSON.name()
        };

    }

    @Test
    public void openDevProfile() {

        WeixinAppId weixinApp = WeixinAppId.app(appProfile[0]);
        OpenAppDevProfileCmd cmd = new OpenAppDevProfileCmd();
        cmd.setServerUrl(appProfile[1]);
        cmd.setServerToken(appProfile[2]);
        cmd.setAesKey(appProfile[3]);
        cmd.setEncryptionMethod(appProfile[4]);
        cmd.setDataType(appProfile[5]);

        app.openDevProfile(weixinApp, cmd);
    }
}