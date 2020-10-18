package top.erzhiqian.weixin.security.domain.valueobject;

import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class ExpiredTimeTest {

    @Test
    public void expired() {
        Long createAt = System.currentTimeMillis();
        createAt = createAt - 1400 * 1000;
        ExpiredTime expiredTime = new ExpiredTime(createAt, 1200);
        assertTrue(expiredTime.expired());

    }

    @Test
    public void notExpired() {
        ExpiredTime expiredTime = new ExpiredTime(System.currentTimeMillis(), 1200);
        assertFalse(expiredTime.expired());
    }
}