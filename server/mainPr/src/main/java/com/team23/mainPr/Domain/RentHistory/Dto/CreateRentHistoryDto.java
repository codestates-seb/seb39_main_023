package com.team23.mainPr.Domain.RentHistory.Dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CreateRentHistoryDto {

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
