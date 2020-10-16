package top.erzhiqian.weixin.message.domain.valueobject.message;


import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

@Getter
public class WeixinEvent {

    private final Event event;

    public WeixinEvent(String event) {
        if (StringUtils.isEmpty(event)) {
            throw new IllegalArgumentException("illegal event.");
        }
        this.event = Event.getEvent(event);
    }

    public EventKey eventKey(String eventKey) {
        switch (event) {
            case SUBSCRIBE:
            case UNSUBSCRIBE:
                if (StringUtils.isEmpty(eventKey)) {
                    return null;
                } else {
                    return new EventKey(eventKey);
                }
            default:
                return new EventKey(eventKey);
        }
    }

    public enum Event {
        SUBSCRIBE("subscribe", "订阅"),
        UNSUBSCRIBE("unsubscribe", "取消订阅"),
        SCAN("SCAN", "扫码事件"),
        LOCATION("LOCATION","上报地理位置事件");
        private String code;
        private String remark;

        Event(String code, String remark) {
            this.code = code;
            this.remark = remark;
        }


        public static Event getEvent(String eventType) {
            if (StringUtils.isEmpty(eventType)) {
                throw new IllegalArgumentException("illegal event.");
            }

            Optional<Event> event = Stream.of(values())
                    .filter(item -> item.code.equals(eventType))
                    .findFirst();
            if (event.isPresent()) {
                return event.get();
            } else {
                throw new IllegalArgumentException("illegal event.");
            }

        }

        public String getCode() {
            return code;
        }

        public String getRemark() {
            return remark;
        }
    }
}
