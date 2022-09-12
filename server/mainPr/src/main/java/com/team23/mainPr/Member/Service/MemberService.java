package com.team23.mainPr.Member.Service;

import com.team23.mainPr.Member.Dto.CreateMemberDto;
import com.team23.mainPr.Member.Entity.Member;
import com.team23.mainPr.Member.Repository.MemberRepository;
import com.team23.mainPr.Profile.Entity.Profile;
import com.team23.mainPr.Profile.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProfileRepository profileRepository;
    //refactor : spring validation 적용시 서비스 삭제 가능 할듯
    public boolean loginValidation(CreateMemberDto dto) {
        try {
            String idPattern = "^[a-zA-Z][\\w]{4,10}$";
            String passworedPattern = "^[@!#%&]{0,}[\\w]{0,10}[@!#%&]{1,}[\\w]{0,10}[@!#%&]{0,}$";
            String nickPattern = "^[\\w]{5,30}$";
            if (Pattern.matches(idPattern, dto.getLoginId()) && Pattern.matches(passworedPattern, dto.getPassword()) && dto.getPassword().length()<=20&& dto.getPassword().length()>=6
            &&Pattern.matches(nickPattern,dto.getNickname()))
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
    // refactor : 캐시, 샤딩 적용하면 저장하는 방법이 복잡해질듯 - 캐싱 전략은 추후에 공부하여 적용
    // study : rdbms 쓸때 처럼 auto increment 적용 안되나?
    //
    public Member createMember(CreateMemberDto dto) {
        try{
            String LoginId = dto.getLoginId();
            String password = dto.getPassword();
            Member member = new Member();
            member.setLoginId(LoginId);
            member.setPassword(password);

            memberRepository.save(member);
            Member result = memberRepository.findById(member.getId()).orElseThrow();

            Profile profile = new Profile();
            profile.setNickname(member.getNickNam());
            profileRepository.save(profile);
            Profile Presult = profileRepository.findById(profile.getId()).orElseThrow();

            return result;

        }catch(Exception e)
        {
            return null;
        }

    }//createMember
}
