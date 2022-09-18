package com.team23.mainPr.Domain.Login.Controller;

import com.team23.mainPr.Domain.Login.Dto.CreateLoginDto;
import com.team23.mainPr.Domain.Login.Dto.DoLoginDto;
import com.team23.mainPr.Domain.Login.Service.LoginService;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
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
    public ResponseEntity<ChildCommonDto> doLogin(@RequestBody DoLoginDto doLoginDto) {

        ChildCommonDto response = loginService.doLogin(doLoginDto);

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @Operation
    @PostMapping("/refeshToken")
    public ResponseEntity<ChildCommonDto> refeshToken(@RequestParam String Authorization) {

        ChildCommonDto response = loginService.refreshToken(Authorization);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
