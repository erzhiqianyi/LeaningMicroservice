package top.erzhiqian.weixin.message.domain.valueobject.message;

import org.springframework.util.StringUtils;

public class LocationValue {
    private final Integer value;
    private final Integer precision;

    public LocationValue(String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException(" illegal location");
        }
        String[] array = value.split("\\.");
        if (array.length == 2) {
            this.value = Integer.valueOf(array[0]);
            this.precision = Integer.valueOf(array[1]);
        } else if (array.length == 1) {
            this.value = Integer.valueOf(array[0]);
            this.precision = 0;
        } else {
            this.value = null;
            this.precision = null;
        }
    }

    @Override
    public String toString() {
        if (null != value && null != precision) {
            return value + "." + precision;
        }
        if (null == precision) {
            return String.valueOf(value);
        }
        return null;
    }
}
