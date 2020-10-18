package top.erzhiqian.weixin.message.domain.entity;

import top.erzhiqian.weixin.core.domain.entity.AutoIncrementEntity;
import top.erzhiqian.weixin.lang.UrlLink;
import top.erzhiqian.weixin.message.client.cmd.OpenAppDevProfileCmd;
import top.erzhiqian.weixin.message.domain.valueobject.*;

public class AppDevProfile extends AutoIncrementEntity {

    private WeixinAppId app;

    private UrlLink url;

    private ServerToken token;

    private MessageEncodingKey aesKey;

    private MessageDataType dataType;

    public AppDevProfile(WeixinAppId app) {
        setApp(app);
    }

    public AppDevProfile(Long id, String appId) {
        super(id);
        setApp(WeixinAppId.app(appId));
    }

    public static AppDevProfile emptyProfile(WeixinAppId app) {
        AppDevProfile appDevProfile = new AppDevProfile(app);
        return appDevProfile;
    }

    public static AppDevProfile profile(Long id, String appId) {
        AppDevProfile appDevProfile = new AppDevProfile(id, appId);
        return appDevProfile;
    }

    public void openDevProfile(OpenAppDevProfileCmd cmd) {
        setUrl(new UrlLink(cmd.getServerUrl()));
        setToken(new ServerToken(cmd.getServerToken()));
        setAesKey(new MessageEncodingKey(cmd.getEncryptionMethod(), cmd.getAesKey()));
        setDataType(new MessageDataType(cmd.getDataType()));
    }

    private void setApp(WeixinAppId app) {
        this.app = app;
    }

    public String getUrl() {
        return null != url ? url.url() : null;
    }

    private void setUrl(UrlLink url) {
        this.url = url;
    }

    public String getToken() {
        return null != token ? token.getToken() : null;
    }

    private void setToken(ServerToken token) {
        this.token = token;
    }

    public String getAesKey() {
        return null != aesKey ? aesKey.getKey() : null;
    }

    private void setAesKey(MessageEncodingKey aesKey) {
        this.aesKey = aesKey;
    }

    public String getEncryptionMethod() {
        return null != aesKey ? aesKey.getEncodingType() : null;
    }

    public String getDataType() {
        return null != dataType ? dataType.type() : null;
    }

    private void setDataType(MessageDataType dataType) {
        this.dataType = dataType;
    }

    public String appId() {
        return app.appId();
    }
}
