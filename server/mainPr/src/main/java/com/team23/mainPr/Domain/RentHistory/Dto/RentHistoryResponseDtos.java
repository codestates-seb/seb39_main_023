package com.team23.mainPr.domain.RentHistory.Dto;

import com.team23.mainPr.global.Dto.ParentCommonDto;
import lombok.Data;
import java.util.List;

@Data
public class RentHistoryResponseDtos extends ParentCommonDto {
    private final List<RentHistoryResponseDto> responses;
}
