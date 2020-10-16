package top.erzhiqian.weixin.message.domain.valueobject.message;

import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@ToString
public class EventMessage {

    private final WeixinEvent event;

    private final EventKey eventKey;

    private final WeixinTicket ticket;

    public EventMessage(String event, String eventKey, String ticket) {
        this.event = new WeixinEvent(event);
        this.eventKey = this.event.eventKey(eventKey);
        if (!StringUtils.isEmpty(ticket)) {
            this.ticket = new WeixinTicket(ticket);
        } else {
            this.ticket = null;
        }
    }
}
