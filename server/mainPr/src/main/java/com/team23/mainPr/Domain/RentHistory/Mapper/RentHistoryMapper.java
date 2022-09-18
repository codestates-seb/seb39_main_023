package com.team23.mainPr.domain.RentHistory.Mapper;

import com.team23.mainPr.domain.RentHistory.Dto.CreateRentHistoryDto;
import com.team23.mainPr.domain.RentHistory.Dto.RentHistoryResponseDto;
import com.team23.mainPr.domain.RentHistory.Entity.RentHistory;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RentHistoryMapper {
    List<RentHistoryResponseDto> map(List<RentHistory> rentHistoryList);

    RentHistory CreateMap(CreateRentHistoryDto dto);

    RentHistoryResponseDto responseMap(RentHistory created);

}
