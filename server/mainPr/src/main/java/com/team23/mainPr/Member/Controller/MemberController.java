package com.team23.mainPr.Member.Controller;

import com.team23.mainPr.Member.Dto.CreateMemberDto;
import com.team23.mainPr.Member.Entity.Member;
import com.team23.mainPr.Member.Service.MemberService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 도메인")
@RestController
@RequestMapping(value = "/user")
public class MemberController {
    //expected feature
    /*
    * create user data
    * create user profile by auto
    * delete user data
    * user data validation check
    *
    * */
    @Autowired
    MemberService memberService;

    /*
    * study : 모델 어트리뷰트 vs 리퀘스트 바디
    * study : 스프링 validation
    *
    * */
    @ApiOperation(
            value = "회원 가입 입력 데이터 유효성 검증"
            , notes = "회원 가입을 위해 입력 받은 정보를 정규식을 이용하여 검증")
    @PostMapping("checkMemberData")

    public boolean validation(@RequestBody @ApiParam(name ="CreateMemberDto",value = "입력한 회원 정보", required = true) CreateMemberDto dto){

        boolean result = memberService.loginValidation(dto);
        return result;
    }//validation
    //refactor : 비밀번호 같은 민감 정보를 전송해도 될까? - response Dto를 만들어서 암호화 할까?
    @ApiOperation(
            value = "회원 가입"
            , notes = "데이터베이스에 회원 정보를 저장하고, 생성된 회원정보를 응답한다.")
    @PostMapping("createMember")
    public ResponseEntity createMember(@RequestBody @ApiParam(name ="CreateMemberDto",value = "입력한 회원 정보", required = true) CreateMemberDto dto){

        Member member = memberService.createMember(dto);
        return new ResponseEntity(member, HttpStatus.CREATED);
    }









}
