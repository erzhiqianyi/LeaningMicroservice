package top.erzhiqian.weixin.security.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class AccessTokenDTO {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private Integer expiresIn;


}
