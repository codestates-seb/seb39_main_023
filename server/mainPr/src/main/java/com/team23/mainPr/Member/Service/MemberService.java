package com.team23.mainPr.Member.Service;

import com.team23.mainPr.Member.Dto.CreateMemberDto;
import com.team23.mainPr.Member.Entity.Member;
import com.team23.mainPr.Member.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    //refactor : spring validation 적용시 서비스 삭제 가능 할듯
    public boolean loginValidation(CreateMemberDto dto) {
        try {
            String idPattern = "^[a-zA-Z][\\w]{4,10}$";
            String passworedPattern = "^[\\w]{0,20}[@!#%&]{1,}[\\w]{0,5}$";
            if (Pattern.matches(idPattern, dto.getLoginId()) && Pattern.matches(passworedPattern, dto.getPassword()))
                return true;
            return false;
        }
        catch(NullPointerException e)
        {
            return false;
        }
    }
    // refactor : 트랜젝션 적용 
    // refactor : 생성 후 확인 과정에서 어떤 필드 하나라도 null 이면 실패 및 롤백 필요
    public Member createMember(CreateMemberDto dto) {
        try{
            String LoginId = dto.getLoginId();
            String password = dto.getPassword();
            Member member = new Member();
            member.setLoginId(LoginId);
            member.setPassword(password);

            memberRepository.save(member);
            Member result = memberRepository.findById(member.getId()).orElseThrow();
            return result;

        }catch(Exception e)
        {
            return null;
        }

    }//createMember
}
