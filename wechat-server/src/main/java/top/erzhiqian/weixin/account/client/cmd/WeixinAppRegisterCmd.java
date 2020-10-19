package top.erzhiqian.weixin.account.client.cmd;

import lombok.Data;

@Data
public class WeixinAppRegisterCmd {

    private String appId;

    private String originalId;

    private String appName;

    private String weixinId;

    private String appType;

    private String certifiedStatus ;

    private String hostType;


}
