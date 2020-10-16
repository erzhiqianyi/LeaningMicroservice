package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
public class WeixinMsgId {

    private final String id;

    public WeixinMsgId(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException(" msg  id can't be null");
        }
        this.id = id;
    }

    public String id() {
        return id;
    }
}
