package com.team23.mainPr.Domain.Login.Dto.Request;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoLoginDto {

    @NotNull(message = "loginId must not be null") private String loginId;

    @NotNull(message = "password must not be null") private String password;
}
