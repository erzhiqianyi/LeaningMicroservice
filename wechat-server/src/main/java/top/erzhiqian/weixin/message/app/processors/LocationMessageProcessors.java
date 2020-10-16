package top.erzhiqian.weixin.message.app.processors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.app.IMessageProcessors;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.LocationMessage;



@Service("locationMessageProcessors")
@Log4j2
public class LocationMessageProcessors implements IMessageProcessors<LocationMessage> {

    @Override
    public void processMessage(WeixinMessage message, LocationMessage locationMessage) {
        log.info("处理位置消息 " + locationMessage);
    }
}
