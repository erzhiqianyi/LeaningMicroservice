package top.erzhiqian.weixin.message.app.processors;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.app.IMessageProcessors;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.ImageMessage;

@Service("imageMessageProcessors")
@Log4j2
public class ImageMessageProcessors implements IMessageProcessors<ImageMessage> {

    @Override
    public void processMessage(WeixinMessage message, ImageMessage imageMessage) {
        log.info("处理图片消息" + imageMessage);
    }
}
