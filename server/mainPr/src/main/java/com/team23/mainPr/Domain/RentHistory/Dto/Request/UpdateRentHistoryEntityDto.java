package com.team23.mainPr.Domain.RentHistory.Dto.Request;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UpdateRentHistoryEntityDto {

    Integer rentHistoryId;
    String rentStatus;
    ZonedDateTime rentStartDate;
    ZonedDateTime rentEndDate;
    String msg;
}

