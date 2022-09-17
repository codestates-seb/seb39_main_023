package com.team23.mainPr.Login.Controller;

import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.Login.Dto.CreateLoginDto;
import com.team23.mainPr.Login.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ChildCommonDto> doLogin(@RequestBody CreateLoginDto dto) {

        ChildCommonDto response = loginService.doLogin(dto);

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @PostMapping("/logout")
    public ResponseEntity<ChildCommonDto> doLogout(@RequestParam String Authorization) {

        ChildCommonDto response = loginService.doLogout(Authorization);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/refeshToken")
    public ResponseEntity<ChildCommonDto> refeshToken(@RequestParam String Authorization) {

        ChildCommonDto response = loginService.refreshToken(Authorization);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
