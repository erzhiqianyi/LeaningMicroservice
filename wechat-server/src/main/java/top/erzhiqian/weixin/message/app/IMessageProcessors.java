package top.erzhiqian.weixin.message.app;

import top.erzhiqian.weixin.message.domain.entity.WeixinMessage;
import top.erzhiqian.weixin.message.domain.valueobject.message.WeixinMessageType;
import top.erzhiqian.weixin.spring.BeanFactory;

public interface IMessageProcessors<T> {
    String PROCESSOR_SUFFIX = "MessageProcessors";

    void processMessage(WeixinMessage message, T messageBody);

    static IMessageProcessors findMessageProcessors(WeixinMessageType messageType) {
        if (null == messageType) {
            throw new IllegalArgumentException(" error msg type.");
        }
        String processorsName = messageType.getCode() + PROCESSOR_SUFFIX;
        return BeanFactory.getBusinessImpl(processorsName, IMessageProcessors.class);
    }
}
