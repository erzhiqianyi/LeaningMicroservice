package top.erzhiqian.weixin.security.domain.valueobject;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ExpiredTime {

    private final Long createAt;

    private final Integer timeToLive;

    public ExpiredTime(Long createAt, Integer timeToLive) {
        Instant.now().toEpochMilli();
        if (null == createAt) {
            this.createAt = System.currentTimeMillis();
        } else {
            this.createAt = createAt;
        }
        if (null == timeToLive) {
            throw new IllegalArgumentException(" time to live can't be null.");
        }
        this.timeToLive = timeToLive;
    }

    public boolean expired() {
        long now = System.currentTimeMillis();
        return now > createAt + timeToLive * 1000;
    }
}
