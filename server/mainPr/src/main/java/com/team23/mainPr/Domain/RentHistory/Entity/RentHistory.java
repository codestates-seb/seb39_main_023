package com.team23.mainPr.Domain.RentHistory.Entity;

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
    boolean rentDataType = false;//send, receive 구별
    public boolean getRentDataType()
    {
        return this.rentDataType;
    }
    String rentStatus = "not selected";//수락-미선택-거절 3case 표현 필요
    ZonedDateTime rentStartDate;
    ZonedDateTime rentEndDate;
    Integer requesterId;//ref member
    String msg = "nothing";
    Integer targetPosId;//ref rentPost
    ZonedDateTime createdTime;
    ZonedDateTime updateTime;
    Integer relateRentHistory;
}
