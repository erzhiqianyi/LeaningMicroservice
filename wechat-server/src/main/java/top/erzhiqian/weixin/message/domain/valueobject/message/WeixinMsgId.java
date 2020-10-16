package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.ToString;

@ToString
public class WeixinMsgId {

    private final Long id;

    public WeixinMsgId(Long id) {
        if (null ==  id) {
            this.id = System.currentTimeMillis();
        }else {
            this.id = id;
        }
    }

    public Long id() {
        return id;
    }
}
