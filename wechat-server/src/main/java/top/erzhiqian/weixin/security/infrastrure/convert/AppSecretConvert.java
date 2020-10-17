package top.erzhiqian.weixin.security.infrastrure.convert;

import org.springframework.stereotype.Component;
import top.erzhiqian.weixin.security.domain.entity.AppSecret;
import top.erzhiqian.weixin.security.infrastrure.po.AppSecretPO;

import java.time.Instant;

@Component
public class AppSecretConvert {

    public AppSecretPO convertToPO(AppSecret appSecret, AppSecretPO original) {
        AppSecretPO po = null == original ? new AppSecretPO() : original;
        if (null != appSecret.id()) {
            po.setLastModified(Instant.now());
        } else {
            po.setCreateAt(Instant.now());
            po.setLastModified(Instant.now());
        }
        po.setAppId(appSecret.getApp().appId());
        po.setAppSecret(appSecret.getAppSecret().secret());
        return po;
    }
}
