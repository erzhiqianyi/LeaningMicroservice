package top.erzhiqian.weixin.security.domain.valueobject;

import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

public enum BusinessStrategyEnum {
    WEIXIN_SERVER("weixin", "去微信服务获取"),
    THIRD_SERVER("third", "去第三方服务获取");

    private String code;

    private String remark;

    BusinessStrategyEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public static BusinessStrategyEnum getStrategy(String strategy) {
        if (StringUtils.isEmpty(strategy)) {
            return null;
        }
        Optional<BusinessStrategyEnum> optional = Stream.of(values())
                .filter(item -> item.code.equals(strategy))
                .findFirst();
        if (optional.isPresent()){
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

    public static boolean isWeixinStrategy(BusinessStrategyEnum strategy) {
        return WEIXIN_SERVER == strategy;
    }
}
