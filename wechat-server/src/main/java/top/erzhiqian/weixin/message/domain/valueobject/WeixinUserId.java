package top.erzhiqian.weixin.message.domain.valueobject;

import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
public class WeixinUserId {

    private final String id;


    public WeixinUserId(String id) {
        if (StringUtils.isEmpty(id)){
            throw new IllegalArgumentException(" illegal user");
        }
        this.id = id;
    }
}
