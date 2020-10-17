package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@ToString
public class EventKey {
    private final String key;

    public EventKey(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("illegal key");
        }
        this.key = key;
    }
}
