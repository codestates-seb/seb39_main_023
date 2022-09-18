package com.team23.mainPr.domain.Member.Dto;

import lombok.Data;

@Data
public class FindPasswordDto {
    String email;
    String name;
    String loginId;
}
