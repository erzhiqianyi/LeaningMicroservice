package top.erzhiqian.weixin.security.infrastrure.facade;

import lombok.Data;

@Data
public class WeixinResponse {

    private static final Integer SUCCESS_CODE = 0;

    private Integer errcode;

    private String errmsg;

    public boolean success() {
        return null == errcode ||  SUCCESS_CODE == errcode;
    }


}
