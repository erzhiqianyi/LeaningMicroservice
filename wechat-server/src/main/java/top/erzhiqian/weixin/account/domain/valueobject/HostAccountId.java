package top.erzhiqian.weixin.account.domain.valueobject;

import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

public class HostAccountId {
    private static final String SPLIT = "_";
    private static final Integer LENGTH = 2;

    private final Long id;

    private final RegisterType type;

    public HostAccountId(String id) {
        if (null == id) {
            throw new IllegalArgumentException("illegal id.");
        }
        String[] array = id.split(SPLIT);
        if (array.length != LENGTH) {
            throw new IllegalArgumentException();
        }
        this.type = RegisterType.getRegisterType(array[0]);
        this.id = Long.valueOf(array[1]);
    }

    public enum RegisterType {
        PERSONAL("p", "个人注册"),
        COMPANY("c", "公司"),
        GOVERNMENT("g", "政府"),
        NGO("n", "公益组织");
        private String code;
        private String remark;

        RegisterType(String code, String remark) {
            this.code = code;
            this.remark = remark;
        }

        public static RegisterType getRegisterType(String type) {
            if (StringUtils.isEmpty(type)) {
                throw new IllegalArgumentException(" register type can't be null.");
            }
            Optional<RegisterType> optional = Stream.of(values())
                    .filter(item -> item.code.equals(type))
                    .findFirst();

            optional.orElseThrow(() -> new IllegalArgumentException("illegal register type"));
            return optional.get();
        }

    }


}
