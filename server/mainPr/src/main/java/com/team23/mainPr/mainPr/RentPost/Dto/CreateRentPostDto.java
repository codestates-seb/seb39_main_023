package com.team23.mainPr.mainPr.RentPost.Dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "게시글 생성에 필요한 데이터를 한번에 모아서 요청", value = "CreateRentPostDto")
@Data
public class CreateRentPostDto {

    private String rentPostContents;
    private String rentPostName;

}
