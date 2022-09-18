package com.team23.mainPr.RentHistory.Entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Data
public class RentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer rentHistoryId;
    Integer targetMemberId;// ref member
    Boolean rentDataType = false;//send, receive 구별
    String rentStatus = "not selected";
    ZonedDateTime rentStartDate;
    ZonedDateTime rentEndDate;
    Integer requesterId;//ref member
    String msg = "nothing";
    Integer targetPosId;//ref rentPost
    ZonedDateTime createdTime;
    ZonedDateTime updateTime;
}
