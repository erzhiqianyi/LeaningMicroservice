package top.erzhiqian.weixin.message.app.processors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.app.IMessageProcessors;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.EventMessage;


@Service("eventMessageProcessors")
@Log4j2
public class EventMessageProcessors implements IMessageProcessors<EventMessage> {
    @Override
    public void processMessage(WeixinMessage message, EventMessage eventMessage) {
        log.info("处理事件消息 " + eventMessage);
    }
}
