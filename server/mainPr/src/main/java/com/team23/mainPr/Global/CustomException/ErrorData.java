package com.team23.mainPr.Global.CustomException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 예외 객체 자체가 아니라 예외의 정보를 감싸는 열거 객체
 * 실제 사용시에는 예외를 상속받은 예외 객체에 사용해야 한다.
 * 내부에 저장된 예외 정보가 수정(국제화 : 다른나라에서도 사용할 수 있게)이 어렵다.
 * 코드 정보를 확인해서 국제화된 메세지로 교체 하는 로직을 사용할 수도 있다 - 불필요한 로직 구현 필요
 * 초기화 없이 private로 템플릿과 같이 껍데기 객체만 선언하여 외부 데이터 소스(DB, 프로그램 외부에서 관리되고 있는 에러 코드 정보를 가진 파일)를 이용하여 실시간으로 변경된 에러 정보 응답 가능
 * 대신 구현이 어렵고, 공통적인 에러코드만 응답하면 되는 작은 시스템에서는 과한면이 있다. - 대형, 다양한 언어로 제공되는 서비스에 적합
 * 에러코드 자체는 거의 고정, 메세지에 대해서 다양한 언어로 표현 필요 - 어느정도 한정된 언어만 커버해야한다면 내부에 국가코드-메세지(자료구조 : 맵) 이런식으로 저장해서 사용하는 방식은 어떨까
 * enum 사용시 하나의 타입에 여러 이름의 객체 사용가능 - 비슷하게 인터페이스와 구현체들을 이용해보는 건? - 한눈에 관리하기 힘들다. 중복 코드가 많이 발생한다. 코드 구현이 간결하지 않다.
 * </pre>
 */

@RequiredArgsConstructor
@Getter
public enum ErrorData {

    INVALID_REGISTER_MEMBER_ID("1001", "잘못된 로그인 ID 형식"),
    INVALID_REGISTER_MEMBER_PASSWORD("1002", "잘못된 로그인 비밀번호 형식"),
    INVALID_REGISTER_MEMBER_NICKNAME("1001", "잘못된 로그인 ID 형식"),
    CLASS_CASTING_EXCEPTION("1501", "잘못된 BODY 입력"),
    ;

    private final String code;
    private final String reason;
}
