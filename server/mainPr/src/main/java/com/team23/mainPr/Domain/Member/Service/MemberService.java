package com.team23.mainPr.Domain.Member.Service;

import com.team23.mainPr.Domain.Comment.Repository.CommentRepository;
import com.team23.mainPr.Domain.Login.Repository.LoginRepository;
import com.team23.mainPr.Domain.Member.Dto.Request.CreateMemberDto;
import com.team23.mainPr.Domain.Member.Dto.Request.FindIdDto;
import com.team23.mainPr.Domain.Member.Dto.Request.FindPasswordDto;
import com.team23.mainPr.Domain.Member.Dto.Request.UpdateMemberDto;
import com.team23.mainPr.Domain.Member.Dto.Request.UpdatePasswordDto;
import com.team23.mainPr.Domain.Member.Dto.Response.MemberProfileDto;
import com.team23.mainPr.Domain.Member.Dto.Response.MemberResponseDto;
import com.team23.mainPr.Domain.Member.Entity.Member;
import com.team23.mainPr.Domain.Member.Mapper.MemberMapper;
import com.team23.mainPr.Domain.Member.Repository.MemberRepository;
import com.team23.mainPr.Domain.Picture.Entity.Picture;
import com.team23.mainPr.Domain.Picture.Repository.PictureRepository;
import com.team23.mainPr.Domain.RentPost.Dto.Response.RentPostResponseDto;
import com.team23.mainPr.Domain.RentPost.Mapper.RentPostMapper;
import com.team23.mainPr.Domain.RentPost.Repository.RentPostRepository;
import com.team23.mainPr.Global.CommonMethod.MemberIdExtractorFromJwt;
import com.team23.mainPr.Global.CustomException.CustomException;
import com.team23.mainPr.Global.CustomException.ErrorData;
import com.team23.mainPr.Global.DefaultTimeZone;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final DefaultTimeZone defaultTimeZone;
    private final PictureRepository pictureRepository;
    private final RentPostRepository rentPostRepository;
    private final RentPostMapper rentPostMapper;
    private final CommentRepository commentRepository;
    private final LoginRepository loginRepository;
    private final MemberIdExtractorFromJwt memberIdExtractorFromJwt;

    @Value("${multipart.upload.path}") String uploadPath;
    String homePath = System.getProperty("user.home");

    /**
     * <pre>
     * ???????????? ?????? ??????
     * ?????? ??? ?????? ???????????? ?????? ?????? ???????????? null ?????? ?????? ??? ?????? ??????
     * ??????, ?????? ???????????? ???????????? ????????? ??????????????? - ?????? ????????? ????????? ???????????? ??????
     * rdbms ?????? ?????? auto increment ?????? ??????????(????????? ????????? ????????? ?????? ???????????? DB ?????????, ????????? ?????? ???????????? ????????????)
     * ????????? find ?????? DB??? ????????? 1??? ???????????? ???????????? ??? ?????????? entityManager ?????? detach ????????? ????????? ???????????? ?????? ??????.
     * ->JpaRepository ????????? ???????????? ????????? ????????????? ???????????? ??????????????? ?????? ??? ?????? -> ????????? 1??? ?????? ??????
     * -> detach ?????? ?????? ????????? ?????????????????? find ?????? ????????? ???????????? ???????????? ?????? ??? ??? ??????.
     */
    public MemberResponseDto createMember(CreateMemberDto dto) {

        Member member = memberMapper.CreateMemberDtoToMember(dto);

        return memberMapper.MemberToMemberResponse(memberRepository.save(member));
    }

    public MemberResponseDto getMember(String token) {
        Member member = memberRepository.getReferenceById(memberIdExtractorFromJwt.getMemberId(token));
        return memberMapper.MemberToMemberResponse(member);
    }

    //PR
    public void deleteMember(String token) {
        Integer memberId = memberIdExtractorFromJwt.getMemberId(token);
        loginRepository.findByMemberId(memberId).ifPresentOrElse(login -> {
            if (!login.getLogouted()) {
                loginRepository.delete(login);
            }
        }, () -> {
            throw new CustomException(ErrorData.NOT_EXIST_LOGIN_INFORMATION);
        });
        String fileName = pictureRepository.getReferenceById(memberRepository.getReferenceById(memberId).getProfileImageId()).getFileName();
        if (!fileName.equals("defaultProfileImage.png")) {
            if (new File(homePath + uploadPath + fileName).delete()) {
                throw new CustomException(ErrorData.INTERNAL_SERVER_ERROR);
            }
        }
        rentPostRepository.findByWriterId(memberId).forEach(rentPostRepository::delete);
        commentRepository.findByWriterId(memberId).forEach(commentRepository::delete);
        memberRepository.deleteById(memberId);
    }

    public MemberProfileDto getProfile(Integer memberId) {
        return memberMapper.MemberToMemberProfileDto(memberRepository.getReferenceById(memberId));
    }

    public MemberProfileDto updateProfile(UpdateMemberDto dto, String token) {

        Member member = memberRepository.getReferenceById(dto.getMemberId());
        if (!memberIdExtractorFromJwt.getMemberId(token).equals(member.getMemberId())) {
            throw new CustomException(ErrorData.NOT_ALLOWED_ACCESS_RESOURCE);
        }
        member.setNickname(dto.getNickname());
        memberRepository.flush();
        return memberMapper.MemberToMemberProfileDto(member);
    }

    /**
     * ?????? ???????????? TRUE, ?????????(?????? ??????)?????? FALSE
     */
    public String checkExistEmail(String email) {
        final String[] result = new String[1];
        // optional ??? ???????????? ???????????? ???????????? if ?????? ??????, null ??? ?????? ????????? ????????? ??? ??? ?????????.
        memberRepository.findByEmail(email).ifPresentOrElse(member -> result[0] = "exist", () -> result[0] = "not exist");
        return result[0];
    }

    public String checkExistId(String id) {
        final String[] result = new String[1];
        memberRepository.findByLoginId(id).ifPresentOrElse(member -> result[0] = "exist", () -> result[0] = "not exist");
        return result[0];
    }

    public String findId(FindIdDto dto) {
        final String[] result = new String[1];
        memberRepository.findByEmail(dto.getEmail()).ifPresent(member -> {
            if (member.getName().equals(dto.getName())) {
                result[0] = member.getLoginId();
            }
        });

        return result[0];
    }

    public String findPassword(FindPasswordDto dto) {
        final String[] result = new String[1];
        memberRepository.findByEmail(dto.getEmail()).ifPresent(member -> {
            if (member.getName().equals(dto.getName()) && member.getLoginId().equals(dto.getLoginId())) {
                result[0] = member.getPassword();
            }
        });

        return result[0];
    }

    public void setProfilePicture(MultipartFile file, String token) throws IOException {

        Member member = memberRepository.getReferenceById(memberIdExtractorFromJwt.getMemberId(token));
        if (!memberIdExtractorFromJwt.getMemberId(token).equals(member.getMemberId())) {
            throw new CustomException(ErrorData.NOT_ALLOWED_ACCESS_RESOURCE);
        }

        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            File newFileName = new File(homePath + uploadPath + uuid + "_" + file.getOriginalFilename());
            file.transferTo(newFileName);

            member.setProfileImageId(pictureRepository.save(new Picture(uuid + "_" + file.getOriginalFilename())).getPictureId());
            memberRepository.flush();
        }
    }

    public Resource getProfilePicture(Integer memberId) throws IOException {

        String filename = pictureRepository.getReferenceById(memberRepository.getReferenceById(memberId).getProfileImageId()).getFileName();
        Path path = Paths.get(homePath + uploadPath + filename);

        return new InputStreamResource(Files.newInputStream(path));
    }
    //PR

    /**
     * <p>
     * ????????? ?????? ?????? ?????? ??? ????????? ???????????? ?????? ?????? ???????????? memberId??? ???????????? ??????????????? ?????? ??????
     * </p>
     */
    public List<RentPostResponseDto> getRentPostMember(String token) {
        return rentPostRepository.findByWriterIdOrderByRentPostIdDesc(memberIdExtractorFromJwt.getMemberId(token)).stream().map(rentPostMapper::RentPostToRentPostResponseDto).collect(Collectors.toList());
    }

    public Boolean checkInter(Integer memberId) {
        return Boolean.TRUE;
    }

    public String checkNickname(String nickname) {
        final String[] result = new String[1];
        memberRepository.findByNickname(nickname).ifPresentOrElse(member -> result[0] = "exist", () -> result[0] = "not exist");
        return result[0];
    }

    public void updatePassword(UpdatePasswordDto dto, String token) {
        Member member = memberRepository.getReferenceById(memberIdExtractorFromJwt.getMemberId(token));
        if (!memberIdExtractorFromJwt.getMemberId(token).equals(member.getMemberId())) {
            throw new CustomException(ErrorData.NOT_ALLOWED_ACCESS_RESOURCE);
        }
        member.setPassword(dto.getNewPassword());
        memberRepository.flush();
    }
}
