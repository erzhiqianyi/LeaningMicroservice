package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.Getter;
import lombok.ToString;
import top.erzhiqian.weixin.message.domain.valueobject.UrlLink;

@Getter
@ToString
public class LinkMessage {

    private String title;

    private String description;

    private UrlLink link;

    public LinkMessage(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.link = new UrlLink(url);
    }
}
