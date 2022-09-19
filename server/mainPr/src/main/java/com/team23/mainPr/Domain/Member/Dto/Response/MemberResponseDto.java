package com.team23.mainPr.Domain.Member.Dto.Response;

import com.team23.mainPr.Global.Dto.ParentCommonDto;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class MemberResponseDto extends ParentCommonDto {

    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private ZonedDateTime createdAt;
    private String profileImageId;
    private String name;

}
