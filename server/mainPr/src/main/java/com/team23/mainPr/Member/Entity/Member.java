package com.team23.mainPr.Member.Entity;

import com.team23.mainPr.Profile.Entity.Profile;
import lombok.Getter;
import lombok.Setter;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

//study : timetolive 속성 응용
@Getter
@Setter
@Entity
//@RedisHash(value = "member")
//hash : redis colletion 중 하나 - 내부에 여러 key-value를 가진다 - 객체와 가장 유사한 구조
//convention - 필드명 upper camel case 사용 - application.properties 설정 참고
//RedisHash : 특정 해쉬값을 키, 해당 클래스의 인스턴스를 값으로 적재
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String LoginId;
    private String Password;
    private String NickNam;
    private String Email;
    private String Phone;

    private LocalDateTime CreatedAt = LocalDateTime.now();

    private String ProfileId;

}
