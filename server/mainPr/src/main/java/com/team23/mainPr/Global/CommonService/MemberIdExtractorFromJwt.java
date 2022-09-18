package com.team23.mainPr.Global.CommonService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * <pre>
 * jwt 토큰을 가져와서 유효성 검사를 진행하고, 토큰을 디코딩하여 지정된 회원 정보(회원 식별자 번호)를 생성.
 * 토큰 단독 사용시, 토큰 탈취로 인한 보안 문제가 발생한다. 대신 세션을 사용하지 않아 stateless 속성을 지킬 수 있다.
 * @see <a href="https://velopert.com/2389">jwt 생성 참고</a>
 * </pre>
 */

@Component
public class MemberIdExtractorFromJwt {

    public Integer getMemberId(String jwt) {
        return JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(jwt).getClaim("memberId").asInt();
    }
}
