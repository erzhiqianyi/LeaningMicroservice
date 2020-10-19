package top.erzhiqian.weixin.account.domain.valueobject;

import lombok.Getter;
import top.erzhiqian.weixin.lang.SimpleText;
import top.erzhiqian.weixin.lang.WeixinAppId;

@Getter
public class WeixinAppAccount {

    private final WeixinAppId appId;

    private WeixinAppId originalId;

    private SimpleText weixinId;

    private SimpleText name;

    private WeixinAppType type;

    private WeixinCertifiedState state;

    private WeixinAccountHostType hostType;

    private WeixinAppAccount(Builder builder) {
        this.appId = builder.appId;
        this.originalId = builder.originalId;
        this.weixinId = builder.weixinId;
        this.name = builder.name;
        this.type = builder.type;
        this.state = builder.state;
        this.hostType = builder.hostType;

    }

    public static class Builder {

        private WeixinAppId appId;

        private WeixinAppId originalId;

        private SimpleText weixinId;

        private SimpleText name;

        private WeixinAppType type;

        private WeixinCertifiedState state;

        private WeixinAccountHostType hostType;

        public Builder(String appId, String name, String type) {
            this.appId = WeixinAppId.app(appId);
            this.name = new SimpleText(name);
            this.type = WeixinAppType.valueOf(type);
        }

        public Builder originalId(String originalId) {
            this.originalId = WeixinAppId.app(originalId);
            return this;
        }

        public Builder weixinId(String weixinId) {
            this.weixinId = new SimpleText(weixinId);
            return this;
        }

        public Builder state(String state) {
            this.state = WeixinCertifiedState.valueOf(state);
            return this;
        }

        public Builder hostType(String hostType) {
            this.hostType = WeixinAccountHostType.valueOf(hostType);
            return this;

        }

        public WeixinAppAccount build() {
            checkData();
            return new WeixinAppAccount(this);
        }

        private void checkData() {
            if (null == appId) {
                throw new IllegalArgumentException("appid can't be null");
            }
            if (null == originalId) {
                throw new IllegalArgumentException("original id can't be null");
            }
            if (null == name) {
                throw new IllegalArgumentException("app name can't be null");
            }
            if (null == type) {
                throw new IllegalArgumentException("app type can't be null");
            }
            if (null == state) {
                this.state = WeixinCertifiedState.UN_CERTIFIED;
            }
            if (null == hostType) {
                throw new IllegalArgumentException("host type can't be null");
            }
        }

    }


}
