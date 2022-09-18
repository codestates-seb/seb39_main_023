package com.team23.mainPr.Global.Jwt.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team23.mainPr.Domain.Member.Entity.Member;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <pre>
 * Dologin 의 서브 기능 : 로그인 결과 값을 이용하여 토큰을 생성한다.
 * 암호화키가 공개되어있고, 만료시간이 길어 테스트용으로만 사용해야 한다. - gitignore + env 파일을 이용해서 암호화키를 숨기고, 리프레시 토큰 기능을 적용해야한다.
 * jwt 라이브러리는 다양한 회사에서 제공되고 있다. 사용하는 라이브러리에 따라 생성하는 코드가 달라진다. -  auth0의 jwt 사용 중
 * </pre>
 */

@Component
public class JwtBuilder {

    public String buildJwt(Member member) {

        return JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 10000)))
                .withClaim("memberId", member.getMemberId())
                .sign(Algorithm.HMAC512("cos_jwt_token"));
    }
}
