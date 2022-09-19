package com.team23.mainPr.Domain.Member.Dto.Request;

import com.team23.mainPr.Global.Dto.ParentCommonDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * swagger 이슈 - private 일때 제대로 인식이 안되는 경우 발생, public으로 사용시 제대로 스웨거 동작
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "CreateMemberDto", description = "입력 받은 회원 가입 정보를 dto로 맵핑")
public class CreateMemberDto extends ParentCommonDto {

    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private String profileImageId = "default.png";
    private String name;

}
