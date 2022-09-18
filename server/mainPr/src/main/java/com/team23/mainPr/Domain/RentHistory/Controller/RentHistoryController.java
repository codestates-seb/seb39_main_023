package com.team23.mainPr.Domain.RentHistory.Controller;

import com.team23.mainPr.Domain.RentHistory.Dto.CreateRentHistoryDto;
import com.team23.mainPr.Domain.RentHistory.Dto.RentHistoryResponseDto;
import com.team23.mainPr.Domain.RentHistory.Dto.RentHistoryResponseDtos;
import com.team23.mainPr.Domain.RentHistory.Dto.UpdateRentHistoryDto;
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

    @Operation
    @GetMapping("/getReceive")
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDtos>> getReceiveRentHistoryData(@RequestParam Integer memberId) {

        ChildCommonDto<RentHistoryResponseDtos> response = rentHistoryService.getReceiveRentHistory(memberId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation
    @GetMapping("/getSend")
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDtos>> getSendRentHistoryData(@RequestParam Integer memberId) {

        ChildCommonDto<RentHistoryResponseDtos> response = rentHistoryService.getSendRentHistory(memberId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation
    @PostMapping
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDto>> addRentHistoryData(@RequestBody CreateRentHistoryDto createRentHistoryDto) {

        ChildCommonDto<RentHistoryResponseDto> response = rentHistoryService.createRentHistory(createRentHistoryDto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation
    @PutMapping
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDto>> updateRentHistoryData(@RequestBody UpdateRentHistoryDto updateRentHistoryDto) {

        ChildCommonDto<RentHistoryResponseDto> response = rentHistoryService.updateRentHistoryData(updateRentHistoryDto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation
    @PostMapping("/delete/{rentHistoryId}")
    public ResponseEntity<ChildCommonDto<RentHistoryResponseDto>> delete(@PathVariable Integer rentHistoryId) {

        ChildCommonDto<RentHistoryResponseDto> response = rentHistoryService.deleteRentHistory(rentHistoryId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}

