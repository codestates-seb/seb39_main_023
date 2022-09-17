package com.team23.mainPr.Login.Dto;

import com.team23.mainPr.Dto.ParentCommonDto;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class DoLoginResponseDto extends ParentCommonDto {
    Integer memberId;
    Integer loginId;
    ZonedDateTime lastLoginDt;
}
