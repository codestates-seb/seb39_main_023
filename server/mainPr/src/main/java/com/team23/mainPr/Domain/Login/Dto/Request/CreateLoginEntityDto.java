package com.team23.mainPr.Domain.Login.Dto.Request;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateLoginEntityDto {

	@NotNull(message = "loginId must not be null")
	private String loginId;

	@NotNull(message = "password must not be null")
	private String password;

	@NotNull(message = "memberId must not be null")
	private Integer memberId;

	@NotNull(message = "token must not be null")
	private String token;

	private ZonedDateTime lastLoginData;
	private ZonedDateTime logoutData = null;
	private Boolean logouted = false;
}