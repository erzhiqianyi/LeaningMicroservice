package top.erzhiqian.weixin.lang;

import org.springframework.util.StringUtils;

public class SimpleText {
    private  final  String text;

    public SimpleText(String text) {
        if (StringUtils.isEmpty(text)){
            throw new IllegalArgumentException(" illegal text.");
        }
        this.text = text;
    }
}
