package com.team23.mainPr.RentPost.Controller;

import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.RentPost.Dto.CreateRentPostDto;
import com.team23.mainPr.RentPost.Service.RentPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/RentPost")
@RequiredArgsConstructor
public class RentPostController {

    private final RentPostService RentPostService;

    /**
     * <pre>
     * MemberController 와 URI 통일성을 위해 register 사용, 대안으로는 write 를 사용가능
     * </pre>
     */

    @Operation
    @PostMapping("/register")
    public ResponseEntity<ChildCommonDto> createRentPost(@RequestBody @Parameter(name = "CreateRentPostDto", description = "입력한 렌트 게시글 데이터.", required = true) CreateRentPostDto dto) {
        ChildCommonDto response = RentPostService.createRentPost(dto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**
     * <pre>
     * api 설명 작성할때 비슷한 기능은 같은 용어를 사용해야 혼동을 줄이고, 통일감을 줄 수 있다.
     * </pre>
     */

    @Operation
    @PutMapping("/update")
    public ResponseEntity<ChildCommonDto> updateRentPost(@RequestBody @Parameter(name = "CreateRentPostDto", description = "입력한 게시글 데이터.", required = true) CreateRentPostDto dto,
                                                         @RequestParam @Parameter(name = "postId", description = "게시글 식별 번호.", required = true) Integer postId) {
        ChildCommonDto response = RentPostService.updateRentPost(postId, dto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation
    @DeleteMapping("/delete")
    public ResponseEntity<ChildCommonDto> updateRentPost(@RequestParam @Parameter(name = "postId", description = "게시글 식별 번호.", required = true) Integer postId) {
        ChildCommonDto response = RentPostService.deleteRentPost(postId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**
     * <pre>
     * 조회에 관련된 uri는 PathVariable 사용하기
     * </pre>
     */

    @Operation
    @GetMapping("/{postId}")
    public ResponseEntity<ChildCommonDto> getRentPost(@PathVariable @Parameter(name = "postId", description = "게시글 식별 번호.", required = true) Integer postId) {
        ChildCommonDto response = RentPostService.getRentPost(postId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
