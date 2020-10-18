package top.erzhiqian.weixin.security.domain.valueobject;

import org.springframework.util.StringUtils;
import top.erzhiqian.weixin.security.domain.repository.IAccessTokenStrategy;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 微信业务类型,不同业务需要不同处理方式。
 * 比如获取access token ,可以直接通过 appId 和 appSecret 获取，也可以去其他服务器获取
 *
 * @author 二之前一
 */
public enum BusinessType {


    REFRESH_ACCESS_TOKEN("RefreshAccessToken", "获取ACCESS_TOKEN") {
        @Override
        public Class strategy() {
            return IAccessTokenStrategy.class;
        }
    },

    GENERATE_QR_CODE("GenerateQrCode", "生成二维码") {
        @Override
        public Class strategy() {
            return null;
        }
    },

    /**
     * 不推荐单独获取应用密钥，获取密钥后，就可以用密钥进行所有操作，要么把密钥给我们托管，或者全部自己实现业务。
     */
    APP_SECRET("GetAppSecret", "获取应用密钥") {
        @Override
        public Class strategy() {
            return null;
        }
    },

    CODE_TO_SESSION("CodeToSession", "用code换取session") {
        @Override
        public Class strategy() {
            return null;
        }
    },
    UNIFORM_MESSAGE("UniformMessage", "统一服务信息") {
        @Override
        public Class strategy() {
            return null;
        }
    },
    SUBSCRIBE_MESSAGE("SubscribeMessage", "订阅消息") {
        @Override
        public Class strategy() {
            return null;
        }
    };
    private String code;
    private String remark;

    BusinessType(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public static BusinessType getBusinessType(String businessType) {
        if (StringUtils.isEmpty(businessType)) {
            return null;
        }
        Optional<BusinessType> optional = Stream.of(values())
                .filter(item -> item.code.equals(businessType))
                .findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public String getRemark() {
        return remark;
    }

    public abstract Class strategy();


}

