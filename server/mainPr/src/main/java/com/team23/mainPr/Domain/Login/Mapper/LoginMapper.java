package com.team23.mainPr.Domain.Login.Mapper;

import com.team23.mainPr.Domain.Login.Dto.CreateLoginDto;
import com.team23.mainPr.Domain.Login.Dto.DoLoginResponseDto;
import com.team23.mainPr.Domain.Login.Entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    Login createMap(CreateLoginDto dto);

    DoLoginResponseDto doLoginMap(Login login);
}
