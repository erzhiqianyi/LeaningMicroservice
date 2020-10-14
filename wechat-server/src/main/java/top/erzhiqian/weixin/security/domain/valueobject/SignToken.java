package top.erzhiqian.weixin.security.domain.valueobject;

import org.springframework.util.StringUtils;

public class SignToken {
    protected final String token;

    public SignToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException("illegal token.");
        }
        this.token = token;
    }

    protected String getToken() {
        return token;
    }
}
