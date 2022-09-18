package com.team23.mainPr.domain.Member.Dto;

import com.team23.mainPr.global.Dto.ParentCommonDto;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class MemberProfileDto extends ParentCommonDto {

    private String loginId;
    private String nickname;
    private String email;
    private ZonedDateTime createdAt;
    private String profileImageId;

}
