package com.github.hippoom.ldd.time;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class Clock {

    public Long toEpochSecond(LocalDateTime localDateTime) {
        // FIXME extract field
        return localDateTime.atZone(zoneId()).toEpochSecond();
    }

    public LocalDateTime now() {
        return LocalDateTime.now(zoneId());
    }

    private ZoneId zoneId() {
        return ZoneId.of("Asia/Shanghai");
    }

    public ZonedDateTime nowWithZone() {
        return now().atZone(zoneId());
    }

    public ZonedDateTime of(Date date) {
        return date.toInstant().atZone(zoneId());
    }
}
