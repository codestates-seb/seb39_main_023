package com.team23.mainPr.Profile.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
* study : getter는 쓰는게 맞는데 setter 꼭 있어야 하나? : 라이브러리 종속도가 너무 높은 상태
* study : http converter 의 동작: 자동 맵핑 동작 개념 확인
*
*
*
* */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUpdateDto {
    public String Nickname;
    public String About;

}
