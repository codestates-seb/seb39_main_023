package com.team23.mainPr.Domain.RentHistory.Controller;

import com.team23.mainPr.Domain.RentHistory.Dto.CreateRentHistoryDto;
import com.team23.mainPr.Domain.RentHistory.Service.RentHistoryService;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
import com.team23.mainPr.Domain.RentHistory.Dto.UpdateRentHistoryDto;
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
    @GetMapping
    public ResponseEntity<ChildCommonDto> getRentHistoryData(@RequestParam Integer memberId) {

        ChildCommonDto response = rentHistoryService.getRentHistory(memberId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation
    @PostMapping
    public ResponseEntity<ChildCommonDto> addRentHistoryData(@RequestBody CreateRentHistoryDto dto) {

        ChildCommonDto response = rentHistoryService.addRentHistory(dto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation
    @PutMapping
    public ResponseEntity<ChildCommonDto> updateRentHistoryData(@RequestBody UpdateRentHistoryDto dto) {

        ChildCommonDto response = rentHistoryService.updateRentHistoryData(dto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
