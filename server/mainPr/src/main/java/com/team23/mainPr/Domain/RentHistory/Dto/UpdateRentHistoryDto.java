package com.team23.mainPr.Domain.RentHistory.Dto;

import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class UpdateRentHistoryDto {

    Integer rentHistoryId;
    String rentStatus;
    ZonedDateTime rentStartDate;
    ZonedDateTime rentEndDate;
    String msg;
}

