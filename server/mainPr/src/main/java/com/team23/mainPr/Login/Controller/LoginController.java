package com.team23.mainPr.Login.Controller;

import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.Login.Dto.CreateLoginDto;
import com.team23.mainPr.Login.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ChildCommonDto> doLogin(@RequestBody CreateLoginDto dto) {

        ChildCommonDto response = loginService.doLogin(dto);

        if (!response.getMsg().contains("not matched") && !response.getMsg().equals("Error"))
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/logout")
    public ResponseEntity<ChildCommonDto> doLogout(@RequestParam String Authorization) {

        ChildCommonDto response = loginService.doLogout(Authorization);

        if (!response.getMsg().contains("not matched") && !response.getMsg().equals("Error"))
            return new ResponseEntity<>(response, HttpStatus.OK);
        else
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
