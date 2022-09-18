package com.team23.mainPr.RentPost.Dto;

import com.team23.mainPr.Dto.ParentCommonDto;
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

}
