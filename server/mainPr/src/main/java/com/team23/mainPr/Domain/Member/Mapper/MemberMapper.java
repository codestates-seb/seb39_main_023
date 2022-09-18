package com.team23.mainPr.Domain.Member.Mapper;

import com.team23.mainPr.Domain.Member.Dto.MemberProfileDto;
import com.team23.mainPr.Domain.Member.Dto.MemberResponseDto;
import com.team23.mainPr.Domain.Member.Entity.Member;
import org.mapstruct.Mapper;

/**
 * <pre>
 * mapstruct 사용할때 엔티티 대소문자 구별 안하는건가? 왜 동작하지? member 가 아니라 Member 이어야하지 않나
 * 구현체 들어가보니 변환 대상이 되는 쪽은 파라미터 부분에서 거의 다 처리되서 크게 의미없고, 변환될 타입 클래스 이름이 중요한것 같다.
 * </pre>
 */

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberResponseDto MemberToMemberResponse(Member member);

    MemberProfileDto MemberToMemberProfileDto(Member member);
}
