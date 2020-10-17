package top.erzhiqian.weixin.message.app.processors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.app.IMessageProcessors;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.VideoMessage;


@Service("shortvideoMessageProcessors")
@Log4j2
public class ShortVideoMessageProcessors implements IMessageProcessors<VideoMessage> {
    @Override
    public void processMessage(WeixinMessage message, VideoMessage videoMessage) {
        log.info("处理小视频消息: " +  videoMessage);
    }
}
