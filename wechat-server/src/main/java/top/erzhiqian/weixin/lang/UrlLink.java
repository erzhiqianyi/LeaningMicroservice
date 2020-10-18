package top.erzhiqian.weixin.lang;

import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
public class UrlLink {

    private final String url;

    public UrlLink(String url) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException(" url can't be null.");
        }
        this.url = url;
    }


    public String url() {
        return url;
    }


}
