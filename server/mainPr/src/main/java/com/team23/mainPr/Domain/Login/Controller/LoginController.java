package com.team23.mainPr.Domain.Login.Controller;

import com.team23.mainPr.Domain.Login.Dto.Request.DoLoginDto;
import com.team23.mainPr.Domain.Login.Dto.Response.DoLoginResponseDto;
import com.team23.mainPr.Domain.Login.Service.LoginService;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
import com.team23.mainPr.Global.Dto.ParentCommonDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @Operation
    @PostMapping
    public ResponseEntity<ChildCommonDto<DoLoginResponseDto>> doLogin(@RequestBody DoLoginDto doLoginDto) {

        ChildCommonDto<DoLoginResponseDto> response = loginService.doLogin(doLoginDto);

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @Operation
    @PostMapping("/refeshToken")
    public ResponseEntity<ChildCommonDto<ParentCommonDto>> refeshToken(@RequestParam String token) {

        ChildCommonDto<ParentCommonDto> response = loginService.refreshToken(token);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
