package top.erzhiqian.weixin.security.infrastrure.facade.param;

import lombok.Data;

@Data
public class AccessTokenQueryParam {

    public static final String DEFAULT_GRANT_TYPE = "client_credential";

    private String appid;

    private String secret;

    private String grant_type;

    public AccessTokenQueryParam() {
    }

    public AccessTokenQueryParam(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
        this.grant_type = DEFAULT_GRANT_TYPE;
    }
}
