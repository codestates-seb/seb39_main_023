package com.team23.mainPr.Domain.RentPost.Controller;

import com.team23.mainPr.Domain.RentPost.Dto.Request.CreateRentPostEntityDto;
import com.team23.mainPr.Domain.RentPost.Dto.Request.UpdateRentPostDto;
import com.team23.mainPr.Domain.RentPost.Dto.Response.RentPostResponseDto;
import com.team23.mainPr.Domain.RentPost.Repository.RentPostRepository;
import com.team23.mainPr.Domain.RentPost.Service.RentPostService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

//개별 DTO를 body 에 할당하고 공통적인 상태코드(200)으로 응답하므로 RestController 로 변경
@RestController
@RequestMapping("/rentPost")
@RequiredArgsConstructor
public class RentPostController {

    private final RentPostService RentPostService;
    //스웨거 관련 부분 삭제 진행 - 실제 코드를 읽는데 방해가 많이 된다.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public RentPostResponseDto createRentPost(@RequestBody @Valid CreateRentPostEntityDto dto) {
        return RentPostService.createRentPost(dto);
    }

    /**
     * <pre>
     * api 설명 작성할때 비슷한 기능은 같은 용어를 사용해야 혼동을 줄이고, 통일감을 줄 수 있다.
     * </pre>
     */

    @PutMapping("/update")
    public RentPostResponseDto updateRentPost(@RequestBody @Valid UpdateRentPostDto updateRentPostDto, Integer postId) {
        return RentPostService.updateRentPost(postId, updateRentPostDto);
    }

    @DeleteMapping("/delete")
    public String updateRentPost(@RequestParam Integer postId) {
        return RentPostService.deleteRentPost(postId);
    }

    /**
     * <pre>
     * 조회에 관련된 uri는 PathVariable 사용하기
     * </pre>
     */
    //멱등성이 보장되지 않는 api에 대해서는 put으로 변경 - 요청할 때 마다 조회수가 상승하므로 멱등성이 지켜지지 않는 api
    @PostMapping("/{postId}")
    public RentPostResponseDto getRentPost(@PathVariable Integer postId) {
        return RentPostService.getRentPost(postId);
    }


}
