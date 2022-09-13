package com.team23.mainPr.Profile.Controller;

import com.team23.mainPr.Profile.Dto.ProfileUpdateDto;
import com.team23.mainPr.Profile.Entity.Profile;
import com.team23.mainPr.Profile.Service.ProfileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//구현할 기능
/*
* 조회
* 생성-삭제 : member 에서 수행 : 서비스 분리할때 문제 발생가능 : member 와 profile 을 합하는 건 어때?
* 업데이트
*
* 서브 기능 : 프로필 이미지 업로드+수정, 다운로드
*
* refactor : 예외 사항 처리 : 서비스 응답 결과 null 체크
* refactor : 진행 상태, 결과를 모니터링 및 로깅
* */
@RestController
@RequestMapping(value = "/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @ApiOperation(
            value = "프로필 데이터 조회하기."
            , notes = "프로필 식별자 번호를 파라미터로 받아, 해당하는 프로필 정보 응답")
    @PostMapping("/")
    public ResponseEntity getProfileData(@RequestParam @ApiParam(name ="ProfileId",value = "프로필 식별 번호", required = true) Integer profileId)
    {

        Profile profile = profileService.getProfile(profileId);

        return new ResponseEntity(profile, HttpStatus.OK);
    }

    /*
    * description : 프로필 데이터를 리퀘스트 바디로 받아와서 업데이트 수행, 프로필 식별자(유저 식별자와 동일한 번호)를 함께 입력받아야 한다
    * */
    @ApiOperation(
            value = "프로필 데이터 업데이트."
            , notes = "프로필 식별자 번호, 를 파라미터로 받아, 해당하는 프로필의 데이터를")
    @PutMapping("/")
    public ResponseEntity updateProfileData(@RequestParam @ApiParam(name ="PostId",value = "포스트 식별 번호", required = true) Integer profileId,
                                            @RequestBody @ApiParam(name ="PostId",value = "포스트 식별 번호", required = true) ProfileUpdateDto dto)
    {

        Profile profile = profileService.updateProfile(profileId,dto);

        return new ResponseEntity(profile, HttpStatus.OK);
    }





}
