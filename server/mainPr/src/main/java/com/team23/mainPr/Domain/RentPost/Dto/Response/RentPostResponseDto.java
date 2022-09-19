package com.team23.mainPr.Domain.RentPost.Dto.Response;

import com.team23.mainPr.Global.Dto.ParentCommonDto;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class RentPostResponseDto extends ParentCommonDto {

    Boolean rented;
    private Integer rentPostId;
    private String rentPostContents;
    private String rentPostName;
    private ZonedDateTime writeDate;
    private ZonedDateTime updateDate;
    private Integer writerId;
    private Integer viewCount;

}
