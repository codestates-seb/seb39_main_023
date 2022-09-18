package com.team23.mainPr.Domain.Login.Dto.Request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateLoginEntityDto {

    private String loginId;
    private String password;
    private Integer memberId;
    private String token;
    private ZonedDateTime lastLoginDt;
    private ZonedDateTime logoutDt = null;
    private Boolean logouted = false;
}
