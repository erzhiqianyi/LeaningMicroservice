package top.erzhiqian.weixin.security.domain.valueobject;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class ExpiredTime {
    private final Instant createAt;
    private final Long timeToLive;
    private final TemporalUnit timeUnit;

    public ExpiredTime(Instant createAt, Long timeToLive) {
        if (null == createAt) {
            this.createAt = Instant.now();
        } else {
            this.createAt = createAt;
        }
        if (null == timeToLive) {
            throw new IllegalArgumentException(" time to live can't be null.");
        }
        this.timeToLive = timeToLive;
        this.timeUnit = ChronoUnit.SECONDS;
    }

    public boolean expired() {
        return Instant.now().isAfter(createAt.plus(timeToLive,timeUnit));
    }
}
