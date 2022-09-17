package com.team23.mainPr.RentHistory.Dto;

import com.team23.mainPr.Dto.ParentCommonDto;
import lombok.Data;
import java.util.List;

@Data
public class RentHistoryResponseDtos extends ParentCommonDto {
    private final List<RentHistoryResponseDto> responses;
}
