package top.erzhiqian.weixin.message.domain.repository;

import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMsgId;

public interface WeixinMessageRepository {
    WeixinMessage findMessage(WeixinAppId app, WeixinMsgId msgId);
}
