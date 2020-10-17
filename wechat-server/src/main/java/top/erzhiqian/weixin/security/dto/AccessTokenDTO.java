package top.erzhiqian.weixin.security.dto;

import lombok.Data;

@Data

public class AccessTokenDTO {

    private String accessToken;

    private Long timeToLive;


}
