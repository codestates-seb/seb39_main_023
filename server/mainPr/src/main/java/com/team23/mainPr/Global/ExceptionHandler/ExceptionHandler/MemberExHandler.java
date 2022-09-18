package com.team23.mainPr.Global.ExceptionHandler.ExceptionHandler;

import com.team23.mainPr.Global.CustomException.CustomException;
import com.team23.mainPr.Global.CustomException.ErrorData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.team23.mainPr.Member")
public class MemberExHandler {

    @ExceptionHandler
    public ErrorData handleMemberNameException(Exception e) {

        CustomException ex = (CustomException) e;

        return ex.getErrordata();
    }
}
