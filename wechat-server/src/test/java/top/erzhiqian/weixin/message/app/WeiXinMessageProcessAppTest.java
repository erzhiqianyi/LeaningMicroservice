package top.erzhiqian.weixin.message.app;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.erzhiqian.weixin.message.client.weixin.WeixinMessageCmd;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMessageType;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class WeiXinMessageProcessAppTest {

    @Autowired
    private WeiXinMessageProcessApp processApp;

    private WeixinMessageCmd cmd;

    private WeixinAppId weixinApp;

    @Before
    public void init() {
        weixinApp = WeixinAppId.app("2345");
        cmd = new WeixinMessageCmd();
        cmd.setMsgId(System.currentTimeMillis());
        cmd.setToUserName("3423432");
        cmd.setFromUserName("324234532");
        cmd.setCreateTime(System.currentTimeMillis());
    }


    @Test
    public void processTextMessage() {
        cmd.setMsgType(WeixinMessageType.TEXT.getCode());
        cmd.setContent("测试文本消息。");
        processApp.processMessage(weixinApp, cmd);

    }


    @Test
    public void processImageMessage() {
        cmd.setMsgType(WeixinMessageType.IMAGE.getCode());
        cmd.setPicUrl("this is a url");
        cmd.setMediaId("media_id");
        processApp.processMessage(weixinApp, cmd);
    }

    @Test
    public void processVoiceMessage() {
        cmd.setMsgType(WeixinMessageType.VOICE.getCode());
        cmd.setMediaId("media_id");
        cmd.setFormat("amr");
        cmd.setRecognition("识别出来的结果");
        processApp.processMessage(weixinApp, cmd);
    }


    @Test
    public void processShortVideoMessage() {
        cmd.setMsgType(WeixinMessageType.SHORT_VIDEO.getCode());
        cmd.setMediaId("media_id");
        cmd.setThumbMediaId("thumb_media_id");
        processApp.processMessage(weixinApp, cmd);
    }


    @Test
    public void processLocationMessage() {
        cmd.setMsgType(WeixinMessageType.LOCATION.getCode());
        cmd.setLocationX("23.134521");
        cmd.setLocationY("113.358803");
        cmd.setScale("20");
        cmd.setLabel("位置信息");
        processApp.processMessage(weixinApp, cmd);
    }

    @Test
    public void processLinkMessage() {
        cmd.setMsgType(WeixinMessageType.LINK.getCode());
        cmd.setTitle("消息标题");
        cmd.setDescription("消息描述");
        cmd.setUrl("消息链接");
        processApp.processMessage(weixinApp, cmd);
    }

    @Test
    public void processSubscribeMessage() {
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("subscribe");
        processApp.processMessage(weixinApp, cmd);

    }

    @Test
    public void processUnsubscribeMessage() {
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("unsubscribe");
        processApp.processMessage(weixinApp, cmd);
    }

    @Test
    public void processUnsubscribeScanEventMessage() {
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("subscribe");
        cmd.setEventKey("qrscene_123123");
        cmd.setTitle("TICKET");
        processApp.processMessage(weixinApp, cmd);

    }


    @Test
    public void processSubscribeScanEventMessage() {
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("SCAN");
        cmd.setEventKey("qrscene_123123");
        cmd.setTitle("TICKET");
        processApp.processMessage(weixinApp, cmd);

    }

}