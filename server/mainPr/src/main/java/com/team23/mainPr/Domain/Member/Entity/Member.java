package com.team23.mainPr.Domain.Member.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 * <pre>
 * timetolive 속성 응용
 *
 * hash : redis colletion 중 하나 - 내부에 여러 key-value를 가진다 - 객체와 가장 유사한 구조
 * convention - 필드명 upper camel case 사용 - application.properties_back 설정 참고
 * RedisHash : 특정 해쉬값을 키, 해당 클래스의 인스턴스를 값으로 적재
 *
 * \@GeneratedValue(strategy = GenerationType.IDENTITY)는 적절하지 않다. - 불필요한 DB 엑세스 발생, 이후 해시값 사용 등 정말 유니크한 값을 만드는 방식을 취해야 한다.
 * </pre>
 */

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;
    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private ZonedDateTime createdAt;
    private String profileImageId;
    private String name;

}
