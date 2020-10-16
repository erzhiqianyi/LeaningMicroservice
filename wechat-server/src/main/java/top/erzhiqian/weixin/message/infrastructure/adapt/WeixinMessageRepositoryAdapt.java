package top.erzhiqian.weixin.message.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.repository.WeixinMessageRepository;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMessageType;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMsgId;

@Service
public class WeixinMessageRepositoryAdapt implements WeixinMessageRepository {


    @Override
    public WeixinMessage findMessage(WeixinAppId app, WeixinMessageType msgType, WeixinMsgId msgId) {
        if (WeixinMessageType.isEventMsg(msgType)) {
            return WeixinMessage.crateEmptyMessage(app,msgType,msgId);
        } else {

            return WeixinMessage.crateEmptyMessage(app, msgType,msgId);
        }
    }

    @Override
    public void saveMessage(WeixinMessage message) {
        message.saveId(System.currentTimeMillis());
    }
}
