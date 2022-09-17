package com.team23.mainPr.RentHistory.Service;

import com.team23.mainPr.DefaultTimeZone;
import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.RentHistory.Dto.CreateRentHistoryDto;
import com.team23.mainPr.RentHistory.Dto.RentHistoryResponseDto;
import com.team23.mainPr.RentHistory.Dto.RentHistoryResponseDtos;
import com.team23.mainPr.RentHistory.Entity.RentHistory;
import com.team23.mainPr.RentHistory.Mapper.RentHistoryMapper;
import com.team23.mainPr.RentHistory.Repository.RentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.team23.mainPr.Enum.ChildCommonDtoMsgList.*;

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

    public ChildCommonDto getRentHistory(Integer memberId) {

        try {
            List<RentHistory> rentHistoryList = rentHistoryRepository.findAllByOwnerId(memberId);

            if (rentHistoryList.size() != 0) {
                List<RentHistoryResponseDto> responses = rentHistoryMapper.map(rentHistoryList);
                return new ChildCommonDto(FALSE.getMsg(), HttpStatus.OK, new RentHistoryResponseDtos(responses));
            }

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ChildCommonDto addRentHistory(CreateRentHistoryDto dto) {
        try {
            RentHistory rentHistory = rentHistoryMapper.CreateMap(dto);
            rentHistory.setCreatedTime(defaultTimeZone.getNow());
            rentHistory.setUpdateTime(defaultTimeZone.getNow());

            RentHistory created = rentHistoryRepository.save(rentHistory);

            if (created != null) {
                return new ChildCommonDto(SUC.getMsg(), HttpStatus.OK, rentHistoryMapper.responseMap(created));
            }

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ChildCommonDto deleteRentHistory(Integer rentHistoryId) {
        try {
            RentHistory rentHistory = rentHistoryRepository.findById(rentHistoryId).orElse(null);

            if (rentHistory != null) {
                rentHistoryRepository.delete(rentHistory);
                new ChildCommonDto(SUC.getMsg(), HttpStatus.OK, null);
            }

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
