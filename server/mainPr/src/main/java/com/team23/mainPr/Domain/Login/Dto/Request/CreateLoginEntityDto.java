package com.team23.mainPr.Domain.Login.Dto.Request;


import lombok.Data;
import java.time.ZonedDateTime;

@Data
public class CreateLoginEntityDto {

    private String loginId;
    private String password;
    private Integer memberId;
    private String token;
    private ZonedDateTime lastLoginDt;
    private ZonedDateTime logoutDt = null;
    private Boolean logouted = false;
}
