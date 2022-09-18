package com.team23.mainPr.domain.Login.Mapper;

import com.team23.mainPr.domain.Login.Dto.CreateLoginDto;
import com.team23.mainPr.domain.Login.Dto.DoLoginResponseDto;
import com.team23.mainPr.domain.Login.Entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    Login createMap(CreateLoginDto dto);

    DoLoginResponseDto doLoginMap(Login login);
}
