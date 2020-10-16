package top.erzhiqian.weixin.message.client.cmd;

import lombok.Data;

@Data
public class OpenAppDevProfileCmd {

    private String serverUrl;

    private String serverToken;

    private String aesKey;

    private String encryptionMethod;

    private String dataType;

}
