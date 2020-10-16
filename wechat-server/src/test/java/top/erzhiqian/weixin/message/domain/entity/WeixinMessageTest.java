package top.erzhiqian.weixin.message.domain.entity;

import org.junit.Before;
import org.junit.Test;
import top.erzhiqian.weixin.message.client.weixin.WeixinMessageCmd;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.message.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WeixinMessageTest {

    private WeixinMessageCmd cmd;

    private WeixinAppId processApp;

    private WeixinMessage message;

    @Before
    public void init() {
        processApp = new WeixinAppId("2345");
        cmd = new WeixinMessageCmd();
        cmd.setMsgId(String.valueOf(System.currentTimeMillis() / 1000));
        cmd.setToUserName("3423432");
        cmd.setFromUserName("324234532");
        cmd.setCreateTime(System.currentTimeMillis());
        message = WeixinMessage.crateEmptyMessage(processApp, new WeixinMsgId(cmd.getMsgId()));
        message.createAt(cmd.getCreateTime())
                .from(cmd.getFromUserName())
                .toUser(cmd.getToUserName());

    }

    @Test
    public void crateTextMessage() {
        cmd.setMsgType(WeixinMessageType.TEXT.getCode());
        cmd.setContent("测试文本消息。");
        message.messageType(cmd.getMsgType());
        TextMessage textMessage = message.createContent(cmd);
        assertNotNull(textMessage);
        assertEquals(cmd.getContent(), textMessage.content());

    }

    @Test
    public void createImageMessage() {
        cmd.setMsgType(WeixinMessageType.IMAGE.getCode());
        cmd.setPicUrl("this is a url");
        cmd.setMediaId("media_id");
        message.messageType(cmd.getMsgType());
        ImageMessage imageMessage = message.createContent(cmd);
        assertNotNull(imageMessage);
        assertEquals(cmd.getPicUrl(), imageMessage.getPicUrl().url());
        assertEquals(cmd.getMediaId(), imageMessage.getMediaId().id());
    }

    @Test
    public void createVoiceMessage() {
        cmd.setMsgType(WeixinMessageType.VOICE.getCode());
        cmd.setMediaId("media_id");
        cmd.setFormat("amr");
        cmd.setRecognition("识别出来的结果");
        message.messageType(cmd.getMsgType());
        VoiceMessage voiceMessage = message.createContent(cmd);
        assertNotNull(voiceMessage);
        assertEquals(cmd.getMediaId(), voiceMessage.getMediaId().id());
        assertEquals(cmd.getFormat(), voiceMessage.getFormat().format());
        assertEquals(cmd.getRecognition(), voiceMessage.getRecognition());
    }


    @Test
    public void createShortVideoMessage() {
        cmd.setMsgType(WeixinMessageType.SHORT_VIDEO.getCode());
        cmd.setMediaId("media_id");
        cmd.setThumbMediaId("thumb_media_id");
        message.messageType(cmd.getMsgType());
        VideoMessage videoMessage = message.createContent(cmd);
        assertNotNull(videoMessage);
        assertEquals(cmd.getMediaId(), videoMessage.getMediaId().id());
        assertEquals(cmd.getThumbMediaId(), videoMessage.getWeixinThumbMediaId().id());
    }


    @Test
    public void createLocationMessage() {
        cmd.setMsgType(WeixinMessageType.LOCATION.getCode());
        cmd.setLocationX("23.134521");
        cmd.setLocationY("113.358803");
        cmd.setScale("20");
        cmd.setLabel("位置信息");
        message.messageType(cmd.getMsgType());
        LocationMessage locationMessage = message.createContent(cmd);
        assertNotNull(locationMessage);
        assertEquals(cmd.getLocationX(), locationMessage.getX().toString());
        assertEquals(cmd.getLocationY(), locationMessage.getY().toString());
        assertEquals(cmd.getScale(), String.valueOf(locationMessage.getScale()));
        assertEquals(cmd.getLabel(), locationMessage.getLabel());

    }

    @Test
    public void createLinkMessage() {
        cmd.setMsgType(WeixinMessageType.LINK.getCode());
        cmd.setTitle("消息标题");
        cmd.setDescription("消息描述");
        cmd.setUrl("消息链接");
        message.messageType(cmd.getMsgType());
        LinkMessage linkMessage = message.createContent(cmd);
        assertNotNull(linkMessage);
        assertEquals(cmd.getTitle(), linkMessage.getTitle());
        assertEquals(cmd.getDescription(), linkMessage.getDescription());
        assertEquals(cmd.getUrl(), linkMessage.getLink().url());

    }

    @Test
    public void createSubscribeMessage() {
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("subscribe");
        message.messageType(cmd.getMsgType());
        EventMessage eventMessage = message.createContent(cmd);
        assertNotNull(eventMessage);
        assertEquals(cmd.getEvent(), eventMessage.getEvent().getEvent().getCode());

    }

    @Test
    public void createUnsubscribeMessage() {
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("unsubscribe");
        message.messageType(cmd.getMsgType());
        EventMessage eventMessage = message.createContent(cmd);
        assertNotNull(eventMessage);
        assertEquals(cmd.getEvent(), eventMessage.getEvent().getEvent().getCode());
    }

    @Test
    public void createUnsubscribeScanEventMessage(){
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("subscribe");
        cmd.setEventKey("qrscene_123123");
        cmd.setTitle("TICKET");
        message.messageType(cmd.getMsgType());
        EventMessage eventMessage = message.createContent(cmd);
        assertNotNull(eventMessage);
        assertEquals(cmd.getEvent(), eventMessage.getEvent().getEvent().getCode());

    }


    @Test
    public void createSubscribeScanEventMessage(){
        cmd.setMsgType(WeixinMessageType.EVENT.getCode());
        cmd.setEvent("SCAN");
        cmd.setEventKey("qrscene_123123");
        cmd.setTitle("TICKET");
        message.messageType(cmd.getMsgType());
        EventMessage eventMessage = message.createContent(cmd);
        assertNotNull(eventMessage);
        assertEquals(cmd.getEvent(), eventMessage.getEvent().getEvent().getCode());

    }



}