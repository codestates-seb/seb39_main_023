package com.team23.mainPr.Global.Enum;

import lombok.Getter;

import java.time.ZoneId;

/**
 * <pre>
 * 자바 enum으로 사용가능한 TIME ZONE 을 제한
 * name() 함수는 final 함수라 오버라이드 불가능
 * </pre>
 *
 * @see TimeZone#getZoneId() 함수로 저장된 메세지 출력
 */

@Getter
public enum TimeZone {

    ASIA_SEOUL("Asia/Seoul");

    private final ZoneId zoneId;

    // public 시 컴파일 에러 발생
    TimeZone(String location) {
        this.zoneId = ZoneId.of(location);
    }
}
