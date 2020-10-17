package top.erzhiqian.weixin.message.domain.entity;

import lombok.ToString;
import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;
import top.erzhiqian.weixin.message.client.weixin.WeixinMessageCmd;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinUserId;
import top.erzhiqian.weixin.message.domain.valueobject.message.MessageStatus;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMessageType;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMsgId;

@ToString
public class WeixinMessage extends AutoIncrementEntity {

    private final WeixinAppId appId;

    private WeixinMsgId msgId;

    private final WeixinMessageType msgType;

    private Long createTime;


    private WeixinAppId toUserName;

    private WeixinUserId fromUserName;

    private MessageStatus status;


    private WeixinMessage(WeixinAppId app, WeixinMessageType msgType, WeixinMsgId msgId) {
        if (null == app || null == msgType) {
            throw new IllegalArgumentException("illegal message.");
        }
        this.appId = new WeixinAppId(app.appId());
        this.msgType = msgType;
        this.msgId = new WeixinMsgId(msgId.id());


    }


    public static WeixinMessage crateEmptyMessage(WeixinAppId app, WeixinMessageType type, WeixinMsgId msgId) {
        WeixinMessage message = new WeixinMessage(app, type, msgId);
        return message;
    }

    public WeixinMessage createAt(Long createTime) {
        setCreateTime(createTime);
        return this;
    }

    public WeixinMessage from(String fromUser) {
        setFromUserName(fromUser);
        return this;
    }

    public WeixinMessage toUser(String toUser) {
        setToUserName(toUser);
        return this;
    }

    public boolean success() {
        return null != status && MessageStatus.SUCCESS == status;
    }

    private void setStatus(MessageStatus status) {
        this.status = status;
    }

    public <T> T createContent(WeixinMessageCmd cmd) {
        if (null == cmd) {
            throw new IllegalArgumentException("illegal message");
        }
        return msgType.createContent(cmd);
    }

    private void setCreateTime(Long createTime) {
        if (null == createTime) {
            this.createTime = System.currentTimeMillis();
        } else {
            this.createTime = createTime;
        }
    }

    private void setToUserName(String toUserName) {
        this.toUserName = new WeixinAppId(toUserName);
    }

    private void setFromUserName(String fromUserName) {
        this.fromUserName = new WeixinUserId(fromUserName);
    }

    public WeixinMessageType msgType() {
        return msgType;
    }
}
