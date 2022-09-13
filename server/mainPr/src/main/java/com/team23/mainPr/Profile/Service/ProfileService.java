package com.team23.mainPr.Profile.Service;

import com.team23.mainPr.Profile.Dto.ProfileUpdateDto;
import com.team23.mainPr.Profile.Entity.Profile;
import com.team23.mainPr.Profile.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/*
* refactor : 커스텀 예외 클래스 만들기
*
* */
public class ProfileService {

    /*
    * description : 프로필 내용 조회
    * refactor : 커스텀 예외 클래스 생성으로 오류 상황에 대해서 친절한 응답 만들기
    * */
    @Autowired
    ProfileRepository profileRepository;
    public Profile getProfile(Integer postId) {
        try{
            Profile profile = profileRepository.findById(postId).orElseThrow();

            return profile;

        }catch(Exception e)
        {
            return null;
        }

    }
    /*
    * description : 프로필 데이터 업데이트 , 프로필 식별자와 업데이트 내용이 들어있는 ProfileUpdateDto 를 가져와서 업데이트를 수행한다.
    *
    * refactor : DB 조회 등 여러번 DB 접속이 발생 : 커스텀 에러 객체 사용 등 응답 분기점이 많이 나뉜다. 처리 필요
    * refactor : 영속성 컨텍스트를 사용하고 있다. 1차캐시에만 저장되어 있을 수 있다 이걸 어떻게 처리할 것 인가에 대한 코드 필요
    *
    *
    * */
    public Profile updateProfile(Integer profileId, ProfileUpdateDto dto) {
        try{
            Profile profile = profileRepository.findById(profileId).orElseThrow();
            //else 문 줄이기
            //래퍼클래스 끼리 값 비교 - 내부캐시(-128~127)를 넘어서는 값은 논리에러 발생 가능. equals 쓰기 
            if(profile.getId().equals(profileId))
            {
                return null;
            }

            profile.setNickname(dto.getNickname());
            profileRepository.flush();

            return profile;
        }
        catch(Exception e)
        {
            return null;
        }




    }
}
