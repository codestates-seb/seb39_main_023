package com.team23.mainPr.RentHistory.Dto;

import com.team23.mainPr.Dto.ParentCommonDto;
import lombok.Data;
import java.time.ZonedDateTime;

/**
 * rentStartDate : ISO 8601 형식 사용 ex)2018-07-18T15:16:33.647+09:00
 */
@Data
public class RentHistoryResponseDto extends ParentCommonDto {

    Integer rentHistoryId;
    Integer ownerId;
    Boolean rentDataType;
    String rentStatus = "not selected";
    ZonedDateTime rentStartDate;
    ZonedDateTime rentEndDate;
    Integer requesterId;
    String msg;
    Integer targetPosId;
    ZonedDateTime createdTime;
    ZonedDateTime updateTime;
}
