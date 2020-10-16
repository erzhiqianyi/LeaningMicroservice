package top.erzhiqian.weixin.message.domain.valueobject;

import top.erzhiqian.weixin.lang.LetterOrDigitsString;

public class ServerToken {

    private static final int MIN_LENGTH = 3;

    private static final int MAX_LENGTH = 32;

    protected final LetterOrDigitsString token;

    /**
     * 令牌必须为英文或数字，长度为3-32字符
     * 2020/10/15 8:30
     * 曹峰
     */
    public ServerToken(String token) {
        checkLength(token);
        this.token = new LetterOrDigitsString(token);
    }


    /**
     * 长度为3-32字符
     * 2020/10/15 8:29
     * 曹峰
     */
    private void checkLength(String token) {
        if (token.length() < MIN_LENGTH || token.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("illegal length ,length must between " + MIN_LENGTH
                    + " and " + MAX_LENGTH);
        }
    }

    public String getToken() {
        return token.getValue();
    }


}
