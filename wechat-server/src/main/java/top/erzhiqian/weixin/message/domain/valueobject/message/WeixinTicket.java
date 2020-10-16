package top.erzhiqian.weixin.message.domain.valueobject.message;

import org.springframework.util.StringUtils;

public class WeixinTicket {

    private final String ticket;

    public WeixinTicket(String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            throw new IllegalArgumentException("ticket can't be null");
        }
        this.ticket = ticket;
    }

    public String ticket() {
        return ticket;
    }
}
