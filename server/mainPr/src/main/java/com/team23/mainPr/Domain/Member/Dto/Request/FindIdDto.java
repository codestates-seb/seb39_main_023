package com.team23.mainPr.Domain.Member.Dto.Request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FindIdDto {

	@NotNull(message = "email must not be null")
	String email;

	@NotNull(message = "name must not be null")
	String name;
}