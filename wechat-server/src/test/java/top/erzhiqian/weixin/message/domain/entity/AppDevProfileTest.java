package top.erzhiqian.weixin.message.domain.entity;

import org.junit.Test;
import top.erzhiqian.weixin.lang.LetterOrDigitsString;
import top.erzhiqian.weixin.message.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.message.client.vo.AppDevProfileVO;
import top.erzhiqian.weixin.message.domain.valueobject.MessageDataType;
import top.erzhiqian.weixin.message.domain.valueobject.MessageEncodingKey;
import top.erzhiqian.weixin.lang.WeixinAppId;

import static org.junit.Assert.*;

public class AppDevProfileTest {

    @Test
    public void emptyProfile() {
        WeixinAppId appId = WeixinAppId.app("g21245s");
        AppDevProfile emptyProfile = AppDevProfile.emptyProfile(appId);
        assertNotNull(emptyProfile);
    }

    @Test
    public void openDevProfile() {
        WeixinAppId appId = WeixinAppId.app("g21245s");
        AppDevProfile emptyProfile = AppDevProfile.emptyProfile(appId);
        OpenAppDevProfileCmd cmd = new OpenAppDevProfileCmd();
        cmd.setServerUrl("https://erzhiqian.top/wechat/server/verify/wx3776b9225ad085af");
        cmd.setServerToken("4324325646DDF3432");
        cmd.setAesKey(LetterOrDigitsString.ofLength(43).getValue());
        cmd.setEncryptionMethod(MessageEncodingKey.EncodingType.ENCRYPT.getCode());
        cmd.setDataType(MessageDataType.DataType.JSON.name());
        emptyProfile.openDevProfile(cmd);
        AppDevProfileVO vo = AppDevProfileVO.from(emptyProfile);
        assertNotNull(vo);
        assertEquals(cmd.getServerUrl(), vo.getServerUrl());
        assertEquals(cmd.getServerToken(), vo.getServerToken());
        assertEquals(cmd.getAesKey(), vo.getAesKey());
        assertEquals(cmd.getEncryptionMethod(), vo.getEncryptionMethod());
        assertEquals(cmd.getDataType(), vo.getDataType());

    }

    @Test
    public void toVO() {
        WeixinAppId appId = WeixinAppId.app("g21245s");
        AppDevProfile emptyProfile = AppDevProfile.emptyProfile(appId);
        AppDevProfileVO vo =AppDevProfileVO.from(emptyProfile);
        assertNotNull(vo);
    }
}