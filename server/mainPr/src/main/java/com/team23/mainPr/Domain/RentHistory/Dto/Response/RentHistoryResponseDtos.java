package com.team23.mainPr.Domain.RentHistory.Dto.Response;

import com.team23.mainPr.Global.Dto.ParentCommonDto;
import lombok.Data;

import java.util.List;

@Data
public class RentHistoryResponseDtos extends ParentCommonDto {
    private final List<RentHistoryResponseDto> responses;
}
