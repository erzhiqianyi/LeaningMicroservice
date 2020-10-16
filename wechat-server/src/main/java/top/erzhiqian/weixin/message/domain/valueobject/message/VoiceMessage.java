package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class VoiceMessage {


    private final WeixinMediaId mediaId;

    private final VoiceFormat format;

    private final String recognition;

    public VoiceMessage(String mediaId, String format, String recognition) {
        this.mediaId = new WeixinMediaId(mediaId);
        this.format = new VoiceFormat(format);
        this.recognition = recognition;
    }
}
