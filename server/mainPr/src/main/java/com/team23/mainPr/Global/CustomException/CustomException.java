package com.team23.mainPr.Global.CustomException;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorData errordata;

    public CustomException(ErrorData e) {
        this.errordata = e;
    }
}
