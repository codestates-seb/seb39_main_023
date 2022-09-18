package com.team23.mainPr.Global.Enum;

import lombok.Getter;

/**
 * <pre>
 * 자바 enum으로 ChildCommonDto의 가능한 메세지 목록을 작성
 * @see ChildCommonDtoMsgList#getMsg() 함수로 저장된 메세지 출력
 * name() 함수는 final 함수라 오버라이드 불가능
 * </pre>
 */

@Getter
public enum ChildCommonDtoMsgList {
    TRUE("true"),
    SUC("success"),
    FAIL("fail"),
    FALSE("false"),
    CREATED("created"),
    NOT_MATCH_PASSWORD("not matched password"),
    NOT_MATCH_ID("not matched loginId"),
    ERROR("Error");

    private final String msg;

    // public 시 컴파일 에러 발생
    ChildCommonDtoMsgList(String msg) {
        this.msg = msg;
    }
}
