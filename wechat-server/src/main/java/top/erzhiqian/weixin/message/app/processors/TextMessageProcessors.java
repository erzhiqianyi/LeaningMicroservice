package top.erzhiqian.weixin.message.app.processors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.app.IMessageProcessors;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.TextMessage;


@Service("textMessageProcessors")
@Log4j2
public class TextMessageProcessors implements IMessageProcessors<TextMessage> {


    @Override
    public void processMessage(WeixinMessage message, TextMessage textMessage) {
        log.info("处理文本消息 " + textMessage);
    }
}
