package top.erzhiqian.weixin.message.app.processors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.app.IMessageProcessors;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.LinkMessage;


@Service("linkMessageProcessors")
@Log4j2
public class LinkMessageProcessors implements IMessageProcessors<LinkMessage> {

    @Override
    public void processMessage(WeixinMessage message, LinkMessage linkMessage) {
        log.info("处理链接消息 " + linkMessage);
    }
}
