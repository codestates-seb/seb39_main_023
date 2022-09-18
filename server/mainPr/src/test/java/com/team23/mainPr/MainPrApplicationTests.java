package com.team23.mainPr;

import com.team23.mainPr.Global.Dto.ChildCommonDto;
import com.team23.mainPr.Domain.Member.Controller.MemberController;
import com.team23.mainPr.Domain.Member.Dto.CreateMemberDto;
import com.team23.mainPr.Domain.Member.Dto.MemberResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static com.team23.mainPr.Global.Enum.ChildCommonDtoMsgList.TRUE;
import static org.assertj.core.api.Assertions.*;

/**<pre>
 * \@SpringBootTest 사용시 서버가 돌아가고 있으면 에러가 발생한다. 이전에 실행된 스프링부트가 꺼져야 한다.
 * */

@SpringBootTest
class MainPrApplicationTests {

    @Autowired
    MemberController memberController;
    String loginId = "wonyong92@github.com";
    String password = "password@@password";
    String nickname = "닉네임1524";
    CreateMemberDto createMemberDto = new CreateMemberDto(loginId, password, nickname,null,null,null);

    @Test
    void contextLoads() {
    }

    @DisplayName(value = "회원가입 입력 데이터 유효성 검증 기능 테스트 = 성공/실패 검증")
    @Test
    void validateCreateUserData() {

        ResponseEntity<ChildCommonDto<MemberResponseDto>> result = memberController.checkInput(createMemberDto);
        ChildCommonDto<MemberResponseDto> dto = (ChildCommonDto) result.getBody();
        assertThat(dto.getMsg()).isEqualTo(TRUE.getMsg())
                .as("check user ID, password");
    }
	/**<pre>
    *ResponseEntity<ChildCommonDto> 내부에 제네릭으로 body가 선언되어 있다. 가져와서 내부 데이터를 검증할수 있다.
    */

    @DisplayName(value = "회원가입 기능 테스트 = 응답의 아이디,비밀번호, 닉네임 검증")
    @Test
    void createUserTest() {

        ResponseEntity<ChildCommonDto<MemberResponseDto>> result = memberController.createMember(createMemberDto);
        ChildCommonDto<MemberResponseDto> Cdto = (ChildCommonDto) result.getBody();
        MemberResponseDto dto = (MemberResponseDto) Cdto.getDto();

        assertThat(dto.getLoginId()).isEqualTo(loginId)
                .as("check user ID");
        assertThat(dto.getPassword()).isEqualTo(password)
                .as("check user password");
        assertThat(dto.getNickname()).isEqualTo(nickname)
                .as("check user Nickname");
    }

    @Test
    void getUserDataTest() {
    }

    @Test
    void delUserDataTest() {
    }

    @Test
    void autoCreateProfileTest() {
    }

    @Test
    void checkMemberRole() {
    }
}
