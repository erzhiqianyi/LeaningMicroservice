package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
public class VoiceFormat {

    private final String code;

    public VoiceFormat(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException(" illegal format.");
        }
        this.code = code;
    }

    public String format() {
        return code;
    }
}
