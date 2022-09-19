package com.team23.mainPr.Domain.Login.Mapper;

import com.team23.mainPr.Domain.Login.Dto.Request.CreateLoginEntityDto;
import com.team23.mainPr.Domain.Login.Dto.Response.DoLoginResponseDto;
import com.team23.mainPr.Domain.Login.Entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    Login CreateLoginEntityDtoToLoginEntity(CreateLoginEntityDto createLoginEntityDto);

    DoLoginResponseDto LoginEntityToDoLoginResponseDto(Login loginEntity);
}
