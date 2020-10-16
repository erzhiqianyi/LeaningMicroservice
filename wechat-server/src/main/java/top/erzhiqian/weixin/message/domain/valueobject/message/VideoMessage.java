package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VideoMessage {

    private final WeixinMediaId mediaId;

    private final WeixinThumbMediaId weixinThumbMediaId;

    public VideoMessage(String mediaId, String thumbMediaId) {
        this.mediaId = new WeixinMediaId(mediaId);
        this.weixinThumbMediaId = new WeixinThumbMediaId(thumbMediaId);
    }
}
