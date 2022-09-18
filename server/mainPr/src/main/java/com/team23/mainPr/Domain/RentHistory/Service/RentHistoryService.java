package com.team23.mainPr.Domain.RentHistory.Service;

import com.team23.mainPr.Domain.RentHistory.Dto.Request.CreateRentHistoryEntityDto;
import com.team23.mainPr.Domain.RentHistory.Dto.Request.UpdateRentHistoryEntityDto;
import com.team23.mainPr.Domain.RentHistory.Dto.Response.RentHistoryResponseDto;
import com.team23.mainPr.Domain.RentHistory.Dto.Response.RentHistoryResponseDtos;
import com.team23.mainPr.Domain.RentHistory.Entity.RentHistory;
import com.team23.mainPr.Domain.RentHistory.Mapper.RentHistoryMapper;
import com.team23.mainPr.Domain.RentHistory.Repository.RentHistoryRepository;
import com.team23.mainPr.Global.DefaultTimeZone;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.team23.mainPr.Global.Enum.ChildCommonDtoMsgList.*;

/**
 * <pre>
 * ZonedDateTime 관련해서 jackson이 제대로 역직렬화를 하지 못하는 상황 발생
 * spring.jackson.deserialization.ADJUST_DATES_TO_CONTEXT_TIME_ZONE= false
 * 설정을 yml 혹은 properties 에 추가하여 iso8061 포맷을 사용 하도록 하였다.
 * json 데이터 예시 : "rentEndDate":"2018-07-18T15:16:33.647+09:00"
 * </pre>
 */

@Service
@RequiredArgsConstructor
public class RentHistoryService {

    private final RentHistoryRepository rentHistoryRepository;
    private final RentHistoryMapper rentHistoryMapper;
    private final DefaultTimeZone defaultTimeZone;

    public ChildCommonDto<RentHistoryResponseDtos> getReceiveRentHistory(Integer memberId) {

        List<RentHistory> rentHistoryList = rentHistoryRepository.findByTargetMemberIdAndRentDataTypeTrue(memberId);

        if (rentHistoryList.size() != 0) {
            List<RentHistoryResponseDto> responses = rentHistoryMapper.map(rentHistoryList);
            return new ChildCommonDto<>(SUCSESS.getMsg(), HttpStatus.OK, new RentHistoryResponseDtos(responses));
        }

        return new ChildCommonDto<>(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);
    }

    public ChildCommonDto<RentHistoryResponseDtos> getSendRentHistory(Integer memberId) {

        List<RentHistory> rentHistoryList = rentHistoryRepository.findByRequesterIdAndRentDataTypeFalse(memberId);

        if (rentHistoryList.size() != 0) {
            List<RentHistoryResponseDto> responses = rentHistoryMapper.map(rentHistoryList);

            return new ChildCommonDto<>(SUCSESS.getMsg(), HttpStatus.OK, new RentHistoryResponseDtos(responses));
        }

        return new ChildCommonDto<>(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);
    }

    public ChildCommonDto<RentHistoryResponseDto> createRentHistory(CreateRentHistoryEntityDto dto) {

        if (dto.getTargetMemberId().equals(dto.getRequesterId()))
            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        RentHistory rentHistory = rentHistoryMapper.CreateMap(dto);
        rentHistory.setCreatedTime(defaultTimeZone.getNow());
        rentHistory.setUpdateTime(defaultTimeZone.getNow());

        RentHistory created = rentHistoryRepository.save(rentHistory);

        RentHistory relatedRentHistory = rentHistoryMapper.RentHistoryToRelatedRentHistory(created);
        relatedRentHistory.setRentDataType(true);

        rentHistoryRepository.save(relatedRentHistory);

        rentHistoryRepository.flush();

        return new ChildCommonDto<>(SUCSESS.getMsg(), HttpStatus.OK, rentHistoryMapper.responseMap(created));
    }

    public ChildCommonDto<RentHistoryResponseDto> deleteRentHistory(Integer rentHistoryId) {

        RentHistory rentHistory = rentHistoryRepository.findById(rentHistoryId).orElse(null);

        if (rentHistory != null) {
            Integer relatedRentHistoryId = rentHistory.getRentHistoryId();

            rentHistoryRepository.delete(rentHistory);
            rentHistoryRepository.deleteById(relatedRentHistoryId);

            new ChildCommonDto<>(SUCSESS.getMsg(), HttpStatus.OK, null);
        }

        return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);
    }

    public ChildCommonDto<RentHistoryResponseDto> updateRentHistoryData(UpdateRentHistoryEntityDto dto) {

        RentHistory rentHistory = rentHistoryRepository.findById(dto.getRentHistoryId()).orElse(null);
        RentHistory relatedRentHistory = rentHistoryRepository.findById(rentHistory.getRentHistoryId()).orElse(null);

        if (dto.getRentHistoryId() == null && dto.getRentStartDate() == null
                && dto.getRentEndDate() == null && dto.getMsg() == null
                && dto.getRentStatus().equals(rentHistory.getRentStatus()) && relatedRentHistory != null)
        {
            return new ChildCommonDto<>(FAIL.getMsg(), HttpStatus.BAD_REQUEST, null);
        }

        if (dto.getRentHistoryId() != null)
            rentHistory.setRentStatus(dto.getRentStatus());
        if (dto.getRentStartDate() != null)
            rentHistory.setRentStartDate(dto.getRentStartDate());
        if (dto.getRentEndDate() != null)
            rentHistory.setRentEndDate(dto.getRentEndDate());
        if (dto.getMsg() != null)
            rentHistory.setMsg(dto.getMsg());
        if (!rentHistory.getRentDataType() && dto.getRentStatus().equals(rentHistory.getRentStatus())) //렌트 요청 타입이 Receive 일때만 수락, 거절 선택 가능
            rentHistory.setRentStatus(dto.getRentStatus());

        rentHistory.setUpdateTime(defaultTimeZone.getNow());

        if (dto.getRentHistoryId() != null)
            relatedRentHistory.setRentStatus(dto.getRentStatus());
        if (dto.getRentStartDate() != null)
            relatedRentHistory.setRentStartDate(dto.getRentStartDate());
        if (dto.getRentEndDate() != null)
            relatedRentHistory.setRentEndDate(dto.getRentEndDate());
        if (dto.getMsg() != null)
            relatedRentHistory.setMsg(dto.getMsg());

        relatedRentHistory.setUpdateTime(defaultTimeZone.getNow());

        rentHistoryRepository.flush();

        return new ChildCommonDto<>(SUCSESS.getMsg(), HttpStatus.OK, rentHistoryMapper.responseMap(rentHistory));
    }
}
