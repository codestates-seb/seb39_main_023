package com.team23.mainPr.Global.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChildCommonDto<T extends ParentCommonDto> {

    String msg = "";

    @JsonIgnore
    HttpStatus httpStatus;


    T t;

    public void SetDto(T dto) {
        this.t = dto;
    }

    public T getDto() {
        return this.t;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
