package com.team23.mainPr.Member.Service;

import com.team23.mainPr.CustomException.CustomException;
import com.team23.mainPr.CustomException.ErrorData;
import com.team23.mainPr.DefaultTimeZone;
import com.team23.mainPr.Dto.ChildCommonDto;
import com.team23.mainPr.Member.Dto.CreateMemberDto;
import com.team23.mainPr.Member.Dto.MemberResponseDto;
import com.team23.mainPr.Member.Dto.UpdateMemberDto;
import com.team23.mainPr.Member.Entity.Member;
import com.team23.mainPr.Member.Mapper.MemberMapper;
import com.team23.mainPr.Member.Repository.MemberRepository;
import com.team23.mainPr.Profile.Entity.Profile;
import com.team23.mainPr.Profile.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import static com.team23.mainPr.Enum.ChildCommonDtoMsgList.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;
    private final MemberMapper memberMapper;
    private final DefaultTimeZone defaultTimeZone;


    /**<pre>
     * spring Bean validate 적용시 서비스 삭제 가능 할듯
     * 컨트롤러 단으로 예외 위임 하기전에 , 서비스 단에서 발생하는 예외는 처리가 안되니까 조건문에서 확실히 걸러줘야한다
     * dto.getLoginId()==null 와 같이 가장 발생확률이 높은 null 에러에 대해서 처리가 필요
     * -> 그냥 dto 만들때 초기화 하면 되지 않나? 왜 굳이 더 힘든 방법을 쓴거지?
     * refactor : 중복 확인 기능과 결합 될 예정
     */

    public ChildCommonDto loginValidation(CreateMemberDto dto) throws RuntimeException {


        String idPattern = "^[a-zA-Z][\\w]{4,20}@{1,1}[\\w]{2,10}(\\.com|\\.net)$";
        String passworedPattern = "^([@!#%&]{0,}[\\w]{0,}[@!#%&]{0,}){1,}$";
        String nickPattern = "^[\\w가-힣]{2,30}$";

        if (!Pattern.matches(idPattern, dto.getLoginId()))
            throw new CustomException(ErrorData.INVALID_REGISTER_MEMBER_ID);

        if (!Pattern.matches(passworedPattern, dto.getPassword()))
            throw new CustomException(ErrorData.INVALID_REGISTER_MEMBER_PASSWORD);

        if (!(dto.getPassword().length() <= 20 && dto.getPassword().length() >= 6))
            throw new CustomException(ErrorData.INVALID_REGISTER_MEMBER_PASSWORD);

        if (!Pattern.matches(nickPattern, dto.getNickname()))
            throw new CustomException(ErrorData.INVALID_REGISTER_MEMBER_NICKNAME);

        return new ChildCommonDto(TRUE.getMsg(), HttpStatus.OK, null);
    }

    /**<pre>
     * 트랜젝션 적용 필요
     * 생성 후 확인 과정에서 어떤 필드 하나라도 null 이면 실패 및 롤백 필요
     * 캐시, 샤딩 적용하면 저장하는 방법이 복잡해질듯 - 캐싱 전략은 추후에 공부하여 적용
     * rdbms 쓸때 처럼 auto increment 적용 안되나?(제대로 유니크 하지도 않고 불필요한 DB 엑세스, 데이터 양이 많아지면 느려진다)
     * 생성후 find 하면 DB가 아니라 1차 캐쉬에서 가져오는 것 아닌가? entityManager 에서 detach 시켜야 제대로 테스트가 될것 같다.
     * ->JpaRepository 이용시 내부에서 엔티티 매니저?를 생성해서 트랜잭션을 설정 및 수행 -> 완료후 1차 캐시 파기
     * -> detach 필요 없이 새로운 트랜잭션으로 find 실행 되므로 문제없이 실행결과 확인 할 수 있다.
     *
     */

    public ChildCommonDto createMember(CreateMemberDto dto) throws NullPointerException {

//        try {
            if(memberRepository.findByLoginId(dto.getLoginId())!=null)
            {
                return new ChildCommonDto(FAIL.getMsg(), HttpStatus.OK, null);
            }
            Member member = new Member();
            member.setLoginId(dto.getLoginId());
            member.setPassword(dto.getPassword());
            member.setNickname(dto.getNickname());
            member.setEmail(dto.getEmail());
            member.setProfileImageId(dto.getProfileImageId());
            member.setCreatedAt(defaultTimeZone.getNow());

            Member created = memberRepository.save(member);

            Profile profile = new Profile();
            profile.setNickname(member.getNickname());
            profileRepository.save(profile);

            Profile Presult = profileRepository.findById(profile.getId()).orElseThrow();

            if (created != null && Presult != null)
                return new ChildCommonDto(TRUE.getMsg(), HttpStatus.OK, memberMapper.MemberToMemberResponse(created));

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, memberMapper.MemberToMemberResponse(created));
//        } catch (Exception e) {
//
//            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
//        }
    }//createMember

    public ChildCommonDto getMember(Integer memberId) {

        try {
            Member member = memberRepository.findById(memberId).orElse(null);

            if (member != null)
                return new ChildCommonDto(TRUE.getMsg(), HttpStatus.OK, memberMapper.MemberToMemberResponse(member));

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    /**<pre>
     * Todo : 연관될 리소스 - 게시글, 댓글, 프로필, 좋아요 도 함꼐 삭제되도록 구성하여야 한다 - cascade 사용 예정
     * delete 수행시 영속성 컨텍스트의 1차 캐시에서도 삭제(detach)된다. - entityManager.detach(member) 로 테스트 완료
     * </pre>
     * */

    public ChildCommonDto deleteMember(Integer memberId) {

        try {
            Member member = memberRepository.findById(memberId).orElseThrow();

            memberRepository.delete(member);

            if (memberRepository.findById(memberId).orElse(null) == null)
                return new ChildCommonDto(TRUE.getMsg(), HttpStatus.OK, null);

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ChildCommonDto<MemberResponseDto> getProfile(Integer memberId) {

        try {
            Member member = memberRepository.findById(memberId).orElse(null);

            if (member != null)
                return new ChildCommonDto(TRUE.getMsg(), HttpStatus.OK, memberMapper.MemberToMemberProfileDto(member));

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ChildCommonDto<MemberResponseDto> updateProfile(UpdateMemberDto dto,Integer memberId) {
        try {
            Member member = memberRepository.findById(memberId).orElse(null);

            if (member != null) {
                if(dto.getNickname()!=null)
                    member.setNickname(dto.getNickname());
                if(dto.getProfileImageId()!=null)
                    member.setProfileImageId(dto.getNickname());
                memberRepository.flush();
                return new ChildCommonDto(TRUE.getMsg(), HttpStatus.OK, memberMapper.MemberToMemberProfileDto(member));
            }

            return new ChildCommonDto(FALSE.getMsg(), HttpStatus.BAD_REQUEST, null);

        } catch (Exception e) {

            return new ChildCommonDto(ERROR.getMsg(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
