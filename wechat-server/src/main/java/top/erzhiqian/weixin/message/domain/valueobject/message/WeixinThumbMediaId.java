package top.erzhiqian.weixin.message.domain.valueobject.message;

import org.springframework.util.StringUtils;

public class WeixinThumbMediaId {
    private final String id;

    public WeixinThumbMediaId(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("illegal ThumbMediaId");
        }
        this.id = id;
    }

    public String id() {
        return id;
    }
}
