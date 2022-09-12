package com.team23.mainPr.Member.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

//study : timetolive 속성 응용
@Getter
@Setter
@RedisHash(value = "member")
//hash : redis colletion 중 하나 - 내부에 여러 key-value를 가진다 - 객체와 가장 유사한 구조
//convention - 필드명 upper camel case 사용 - application.yml 설정 참고
public class Member {
    @Id
    private String Id;

    private String LoginId;
    private String Password;

    private LocalDateTime CreatedAt = LocalDateTime.now();

}
