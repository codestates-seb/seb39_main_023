package com.team23.mainPr.RentPost.Controller;

import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.RentPost.Dto.CreateRentPostDto;
import com.team23.mainPr.RentPost.Service.RentPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/RentPost")
@RequiredArgsConstructor
public class RentPostController {

    private final RentPostService RentPostService;

    /**<pre>
     * MemberController 와 URI 통일성을 위해 register 사용, 대안으로는 write 를 사용가능
     * </pre>
     * */

    @Operation(summary = "렌트 물품 게시글 등록.", description = "데이터베이스에 물품 렌트 글을 생성하고, 생성된 게시글 데이터를 응답한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입 성공."),
            @ApiResponse(responseCode = "400", description = "잘못된 입력."),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러")})
    @PostMapping("/register")
    public ResponseEntity<ChildCommonDto> createRentPost(@RequestBody @Parameter(name = "CreateRentPostDto", description = "입력한 렌트 게시글 데이터.", required = true) CreateRentPostDto dto) {
        ChildCommonDto response = RentPostService.createRentPost(dto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**<pre>
     * api 설명 작성할때 비슷한 기능은 같은 용어를 사용해야 혼동을 줄이고, 통일감을 줄 수 있다.
     * </pre>
     * */

    @Operation(summary = "렌트 물품 게시글 업데이트.", description = "데이터베이스에 물품 렌트 글을 확인하여, 업데이트 완료한 게시글 데이터를 응답한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입 성공."),
            @ApiResponse(responseCode = "400", description = "잘못된 입력."),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러")})
    @PutMapping("/update")
    public ResponseEntity<ChildCommonDto> updateRentPost(@RequestBody @Parameter(name = "CreateRentPostDto", description = "입력한 게시글 데이터.", required = true) CreateRentPostDto dto,
                                                         @RequestParam @Parameter(name = "postId", description = "게시글 식별 번호.", required = true) Integer postId) {
        ChildCommonDto response = RentPostService.updateRentPost(postId, dto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @Operation(summary = "렌트 물품 게시글 삭제.", description = "데이터베이스에 물품 렌트 글을 확인하여, 게시글 데이터를 삭제하고, 성공 여부를 응답.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입 성공."),
            @ApiResponse(responseCode = "400", description = "잘못된 입력."),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러")})
    @DeleteMapping("/delete")
    public ResponseEntity<ChildCommonDto> updateRentPost(@RequestParam @Parameter(name = "postId", description = "게시글 식별 번호.", required = true) Integer postId) {
        ChildCommonDto response = RentPostService.deleteRentPost(postId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /**<pre>
     * 조회에 관련된 uri는 PathVariable 사용하기
     * </pre>
     * */

    @Operation(summary = "렌트 물품 게시글 조회.", description = "데이터베이스에 물품 렌트 글을 확인하여, 게시글 정보를 응답.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입 성공."),
            @ApiResponse(responseCode = "400", description = "잘못된 입력."),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러")})
    @GetMapping("/{postId}")
    public ResponseEntity<ChildCommonDto> getRentPost(@PathVariable @Parameter(name = "postId", description = "게시글 식별 번호.", required = true) Integer postId) {
        ChildCommonDto response = RentPostService.getRentPost(postId);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
