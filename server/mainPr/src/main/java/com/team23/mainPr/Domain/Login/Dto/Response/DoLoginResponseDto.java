package com.team23.mainPr.Domain.Login.Dto.Response;

import com.team23.mainPr.Global.Dto.ParentCommonDto;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class DoLoginResponseDto extends ParentCommonDto {
    Integer memberId;
    Integer loginId;
    ZonedDateTime lastLoginDt;
}
