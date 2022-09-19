package com.team23.mainPr.Domain.RentHistory.Controller;

import com.team23.mainPr.Domain.RentHistory.Dto.Request.CreateRentHistoryEntityDto;
import com.team23.mainPr.Domain.RentHistory.Dto.Request.UpdateRentHistoryEntityDto;
import com.team23.mainPr.Domain.RentHistory.Dto.Response.RentHistoryResponseDto;
import com.team23.mainPr.Domain.RentHistory.Dto.Response.RentHistoryResponseDtos;
import com.team23.mainPr.Domain.RentHistory.Service.RentHistoryService;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rentHistory")
@RequiredArgsConstructor
public class RentHistoryController {

    private final RentHistoryService rentHistoryService;


    @GetMapping("/receive")
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDtos>> getReceiveRentHistoryData(@RequestParam Integer memberId) {

        ChildCommonDto<RentHistoryResponseDtos> response = rentHistoryService.getReceiveRentHistory(memberId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @GetMapping("/send")
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDtos>> getSendRentHistoryData(@RequestParam Integer memberId) {

        ChildCommonDto<RentHistoryResponseDtos> response = rentHistoryService.getSendRentHistory(memberId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @PostMapping("/post")
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDto>> addRentHistoryData(@RequestBody CreateRentHistoryEntityDto createRentHistoryEntityDto) {

        ChildCommonDto<RentHistoryResponseDto> response = rentHistoryService.createRentHistory(createRentHistoryEntityDto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @PutMapping
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDto>> updateRentHistoryData(@RequestBody UpdateRentHistoryEntityDto updateRentHistoryDto) {

        ChildCommonDto<RentHistoryResponseDto> response = rentHistoryService.updateRentHistoryData(updateRentHistoryDto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @PostMapping("/delete/{rentHistoryId}")
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDto>> delete(@PathVariable Integer rentHistoryId) {

        ChildCommonDto<RentHistoryResponseDto> response = rentHistoryService.deleteRentHistory(rentHistoryId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}

