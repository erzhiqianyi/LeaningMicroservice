package top.erzhiqian.weixin.security.domain.valueobject;

import lombok.ToString;
import org.springframework.util.StringUtils;
import top.erzhiqian.weixin.security.domain.entity.AccessToken;

import java.util.UUID;

@ToString
public class AccessTokenString {

    private final  String token;

    public AccessTokenString(String token) {
        if (StringUtils.isEmpty(token)){
            throw new IllegalArgumentException(" illegal token.");
        }
        this.token = token;
    }

    public static AccessTokenString generateToken() {
        String token = UUID.randomUUID().toString() ;
        return new AccessTokenString(token);
    }

    public String token(){
        return token;
    }
}
