package top.erzhiqian.weixin.message.domain.repository;

import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.lang.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMessageType;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMsgId;

public interface WeixinMessageRepository {
    WeixinMessage findMessage(WeixinAppId app, WeixinMessageType msgType, WeixinMsgId msgId);

    void saveMessage(WeixinMessage message);
}
