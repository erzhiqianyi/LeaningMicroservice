package top.erzhiqian.weixin.message.app;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.client.weixin.WeixinMessageCmd;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.repository.WeixinMessageRepository;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMessageType;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMsgId;

@Component
public class WeiXinMessageProcessApp {

    private WeixinMessageRepository repository;

    public WeiXinMessageProcessApp(WeixinMessageRepository repository) {
        this.repository = repository;
    }

    /**
     * 处理微信消息
     * <p>
     * 2020/10/16 8:39
     *
     * @author 二之前一
     */
    public void processMessage(WeixinAppId processApp, WeixinMessageCmd cmd) {
        WeixinMessageType messageType = WeixinMessageType.getMessageType(cmd.getMsgType()) ;
        WeixinMessage message = repository.findMessage(processApp, messageType,new WeixinMsgId(cmd.getMsgId()));
        if (message.success()) {
            return;
        }
        message.createAt(cmd.getCreateTime())
                .from(cmd.getFromUserName())
                .toUser(cmd.getToUserName());
        repository.saveMessage(message);
        IMessageProcessors processors = IMessageProcessors.findMessageProcessors(message.msgType());
        processors.processMessage(message, message.createContent(cmd));

    }
}
