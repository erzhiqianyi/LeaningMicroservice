package top.erzhiqian.weixin.security.client.vo;

import lombok.Data;
import top.erzhiqian.weixin.security.domain.entity.AppDevProfile;

import java.util.Objects;

@Data
public class AppDevProfileVO {

    private String serverUrl;

    private String serverToken;

    private String aesKey;

    private String encryptionMethod;

    private String dataType;

    public static AppDevProfileVO from(AppDevProfile profile) {
        AppDevProfileVO vo = new AppDevProfileVO();
        vo.setServerUrl(profile.getUrl());
        vo.setServerToken(profile.getToken());
        vo.setAesKey(profile.getAesKey());
        vo.setEncryptionMethod(profile.getEncryptionMethod());
        vo.setDataType(profile.getDataType());
        return vo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppDevProfileVO)) return false;
        AppDevProfileVO vo = (AppDevProfileVO) o;
        return Objects.equals(serverUrl, vo.serverUrl) &&
                Objects.equals(serverToken, vo.serverToken) &&
                Objects.equals(aesKey, vo.aesKey) &&
                Objects.equals(encryptionMethod, vo.encryptionMethod) &&
                Objects.equals(dataType, vo.dataType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverUrl, serverToken, aesKey, encryptionMethod, dataType);
    }
}
