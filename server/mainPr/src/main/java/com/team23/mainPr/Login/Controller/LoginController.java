package com.team23.mainPr.Login.Controller;

import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.Login.Dto.CreateLoginDto;
import com.team23.mainPr.Login.Service.LoginService;
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
    public ResponseEntity<ChildCommonDto> doLogin(@RequestBody CreateLoginDto dto) {

        ChildCommonDto response = loginService.doLogin(dto);

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @Operation
    @PostMapping("/refeshToken")
    public ResponseEntity<ChildCommonDto> refeshToken(@RequestParam String Authorization) {

        ChildCommonDto response = loginService.refreshToken(Authorization);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
