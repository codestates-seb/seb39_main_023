package com.team23.mainPr.Global;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class DefaultTimeZone {

    @Value("${timeZone.location.asia_seoul}")
    String location;

    public DefaultTimeZone() {

    }

    public ZonedDateTime getNow() {
        return ZonedDateTime.now(ZoneId.of(this.location));
    }

}
