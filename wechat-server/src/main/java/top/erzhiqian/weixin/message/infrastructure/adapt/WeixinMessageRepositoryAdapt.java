package top.erzhiqian.weixin.message.infrastructure.adapt;

import org.springframework.stereotype.Service;
import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.repository.WeixinMessageRepository;
import top.erzhiqian.weixin.message.domain.valueobject.WeixinAppId;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMsgId;

@Service
public class WeixinMessageRepositoryAdapt implements WeixinMessageRepository {

    @Override
    public WeixinMessage findMessage(WeixinAppId app, WeixinMsgId msgId) {
        return WeixinMessage.crateEmptyMessage(app, msgId);
    }
}
