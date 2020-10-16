package top.erzhiqian.weixin.message.domain.valueobject.message;

import org.springframework.util.StringUtils;

public class WeixinMediaId {
    private final String id;

    public WeixinMediaId(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("illegal media.");
        }
        this.id = id;
    }

    public String id() {
        return id;
    }
}
