package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
public class TextMessage {

    private final String content;

    public TextMessage(String content) {
        if (StringUtils.isEmpty(content)) {
            throw new IllegalArgumentException(" illegal message.");
        }
        this.content = content;
    }


    public String content() {
        return content;
    }

}
