package com.team23.mainPr.Domain.RentHistory.Dto.Request;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CreateRentHistoryEntityDto {

    Integer targetMemberId;
    boolean rentDataType = false;
    String rentStatus = "not selected";
    ZonedDateTime rentStartDate;
    ZonedDateTime rentEndDate;
    Integer requesterId;
    String msg = "nothing";
    Integer targetPosId;
    ZonedDateTime createdTime;
    ZonedDateTime updateTime;

}
