package com.team23.mainPr.Domain.Logout.Controller;

import com.team23.mainPr.Domain.Login.Service.LoginService;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
import com.team23.mainPr.Global.Dto.ParentCommonDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor

public class LogoutController {

    private final LoginService loginService;

    @Operation
    @PostMapping("/logout")
    public ResponseEntity<ChildCommonDto<ParentCommonDto>> doLogout(@RequestParam String Authorization) {

        ChildCommonDto<ParentCommonDto> response = loginService.doLogout(Authorization);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
