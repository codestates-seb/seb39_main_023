package com.team23.mainPr.Member.Dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CreateMemberDto", description = "입력 받은 정보를 dto로 맵핑")
public class CreateMemberDto {
    @ApiModelProperty(value="로그인 아이디 - 영어로 시작, 숫자포함 총 5~11자",example = "wonyong92") //public 필드만 swagger에서 제대로 동작
    public String LoginId;
    @ApiModelProperty(value="패스워드 - 특수문자 [@!#%&] 최소 1글자 이상 포함, 최소 6글자 최대 20 글자 ",example = "password@@@password")
    public String Password;
}
