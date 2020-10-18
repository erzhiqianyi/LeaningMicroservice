package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.Getter;
import lombok.ToString;
import top.erzhiqian.weixin.lang.UrlLink;

@ToString
@Getter
public class ImageMessage {

    private final UrlLink picUrl;

    private final WeixinMediaId mediaId;

    public ImageMessage(String picUrl, String mediaId) {
        this.picUrl = new UrlLink(picUrl);
        this.mediaId = new WeixinMediaId(mediaId);
    }

}
