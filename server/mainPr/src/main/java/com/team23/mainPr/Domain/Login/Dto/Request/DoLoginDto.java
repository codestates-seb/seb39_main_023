package com.team23.mainPr.Domain.Login.Dto.Request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DoLoginDto {
    private String loginId;
    private String password;
}
