package com.team23.mainPr.Member.Controller;

import com.team23.mainPr.Member.Dto.CreateMemberDto;
import com.team23.mainPr.Member.Entity.Member;
import com.team23.mainPr.Member.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("checkMemberData")
    public boolean validation(@RequestBody CreateMemberDto dto){

        boolean result = memberService.loginValidation(dto);
        return result;
    }//validation

    @PostMapping("createMember")
    public ResponseEntity createMember(@RequestBody CreateMemberDto dto){

        Member member = memberService.createMember(dto);
        return new ResponseEntity(member, HttpStatus.CREATED);
    }









}
