package com.team23.mainPr.Domain.RentHistory.Mapper;

import com.team23.mainPr.Domain.RentHistory.Dto.CreateRentHistoryDto;
import com.team23.mainPr.Domain.RentHistory.Dto.RentHistoryResponseDto;
import com.team23.mainPr.Domain.RentHistory.Entity.RentHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentHistoryMapper {
    List<RentHistoryResponseDto> map(List<RentHistory> rentHistoryList);

    RentHistory CreateMap(CreateRentHistoryDto dto);

    RentHistoryResponseDto responseMap(RentHistory created);

}
