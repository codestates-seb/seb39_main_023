package com.team23.mainPr.Login.Mapper;

import com.team23.mainPr.Dto.ParentCommonDto;
import com.team23.mainPr.Login.Dto.CreateLoginDto;
import com.team23.mainPr.Login.Dto.DoLoginResponseDto;
import com.team23.mainPr.Login.Entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    Login createMap(CreateLoginDto dto);

    DoLoginResponseDto doLoginMap(Login login);
}
