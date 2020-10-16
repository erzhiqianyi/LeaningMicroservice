package top.erzhiqian.weixin.message.app.processors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.app.IMessageProcessors;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.VoiceMessage;

@Service("voiceMessageProcessors")
@Log4j2
public class VoiceMessageProcessors implements IMessageProcessors<VoiceMessage> {

    @Override
    public void processMessage(WeixinMessage message, VoiceMessage voiceMessage) {
        log.info("处理语音消息" + voiceMessage);
    }
}
