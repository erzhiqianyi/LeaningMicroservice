package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@ToString
public class LocationMessage {

    private LocationValue x;

    private LocationValue y;

    private Integer scale;

    private String label;

    public LocationMessage(String x, String y, String scale, String label) {
        if (StringUtils.isEmpty(x) || StringUtils.isEmpty(y) || StringUtils.isEmpty(scale)) {
            throw new IllegalArgumentException(" illegal location.");
        }
        this.x = new LocationValue(x);
        this.y = new LocationValue(y);
        this.scale = Integer.parseInt(scale);
        this.label = label;
    }
}
