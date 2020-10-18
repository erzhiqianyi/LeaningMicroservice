package top.erzhiqian.weixin.message.infrastructure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.message.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.message.domain.entity.AppDevProfile;
import top.erzhiqian.weixin.message.infrastructure.po.AppDevProfilePO;

import java.time.Instant;

@Component
public class AppDevProfileConvert {

    public AppDevProfile convertToEntity(AppDevProfilePO po) {
        AppDevProfile profile = AppDevProfile.profile(po.getId(), po.getAppId());
        OpenAppDevProfileCmd cmd = new OpenAppDevProfileCmd();
        cmd.setServerUrl(po.getServerUrl());
        cmd.setServerToken(po.getServerToken());
        cmd.setAesKey(po.getAesKey());
        cmd.setEncryptionMethod(po.getEncryptionMethod());
        cmd.setDataType(po.getDataType());
        profile.openDevProfile(cmd);
        return profile;
    }


    public AppDevProfilePO convertToPO(AppDevProfile profile, AppDevProfilePO original) {
        AppDevProfilePO po = null == original ? new AppDevProfilePO() : original;
        po.setId(profile.id());
        po.setAppId(profile.appId());
        if (null != profile.id()) {
            po.setLastModified(System.currentTimeMillis());
        } else {
            po.setCreateAt(System.currentTimeMillis());
            po.setLastModified(System.currentTimeMillis());
        }
        po.setServerUrl(profile.getUrl());
        po.setServerToken(profile.getToken());
        po.setAesKey(profile.getAesKey());
        po.setEncryptionMethod(profile.getEncryptionMethod());
        po.setDataType(profile.getDataType());
        return po;
    }
}
