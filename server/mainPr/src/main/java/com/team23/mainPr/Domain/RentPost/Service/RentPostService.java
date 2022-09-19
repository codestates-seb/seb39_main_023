package com.team23.mainPr.Domain.RentPost.Service;

import com.team23.mainPr.Domain.RentPost.Dto.Request.CreateRentPostEntityDto;
import com.team23.mainPr.Domain.RentPost.Dto.Request.UpdateRentPostDto;
import com.team23.mainPr.Domain.RentPost.Dto.Response.RentPostResponseDto;
import com.team23.mainPr.Domain.RentPost.Entity.RentPost;
import com.team23.mainPr.Domain.RentPost.Mapper.RentPostMapper;
import com.team23.mainPr.Domain.RentPost.Repository.RentPostRepository;
import com.team23.mainPr.Global.DefaultTimeZone;
import com.team23.mainPr.Global.Dto.ChildCommonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import static com.team23.mainPr.Global.Enum.ChildCommonDtoMsgList.FALSE;
import static com.team23.mainPr.Global.Enum.ChildCommonDtoMsgList.TRUE;

/**
 * <pre>
 * 어떤 작성자 정보를 넣을까.
 * 외래키 연관 관계 생성을 통해 데이터 무결성을 지킬것인가? 아니면 분리하여 서비스의 분리도를 높일 것인가?
 * save가 작동하지 않으면 백퍼센트 pk에 널이 들어갔거나 , 생성 시퀀스에 문제가 있는것
 * </pre>
 */

@Service
@RequiredArgsConstructor
public class RentPostService {

    private final RentPostRepository rentPostRepository;
    private final RentPostMapper rentPostMapper;
    private final DefaultTimeZone defaultTimeZone;

    public ChildCommonDto<RentPostResponseDto> createRentPost(CreateRentPostEntityDto dto) {

        RentPost post = rentPostMapper.CreateRentPostEntityDtoToRentPost(dto);
        post.setUpdateDate(defaultTimeZone.getNow());
        post.setWriteDate(defaultTimeZone.getNow());

        RentPost result = rentPostRepository.save(post);

        if (result != null) {
            return new ChildCommonDto<>(TRUE.getMsg(), HttpStatus.OK, rentPostMapper.RentPostToRentPostResponse(result));
        } else
            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, rentPostMapper.RentPostToRentPostResponse(result));
    }

    public ChildCommonDto<RentPostResponseDto> updateRentPost(Integer postId, UpdateRentPostDto dto) {

        RentPost post = rentPostRepository.findById(postId).orElse(null);

        if (dto.getRentPostContents() != null && dto.getRentPostName() != null && dto.getRentStatus().equals(post.getRentStatus()))
            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, rentPostMapper.RentPostToRentPostResponse(post));

        if (dto.getRentPostContents() != null)
            post.setRentPostContents(dto.getRentPostContents());
        if (dto.getRentPostName() != null)
            post.setRentPostName(dto.getRentPostName());
        if (dto.getRentStatus().equals(post.getRentStatus()))
            post.setRentStatus(dto.getRentStatus());

        post.setUpdateDate(defaultTimeZone.getNow());

        rentPostRepository.flush();

        if (rentPostRepository.findById(postId).orElse(null) != null) {

            return new ChildCommonDto<>(TRUE.getMsg(), HttpStatus.OK, rentPostMapper.RentPostToRentPostResponse(post));
        }

        return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, rentPostMapper.RentPostToRentPostResponse(post));
    }

    public ChildCommonDto<RentPostResponseDto> deleteRentPost(Integer postId) {

        RentPost post = rentPostRepository.findById(postId).orElse(null);
        rentPostRepository.delete(post);

        RentPost result = rentPostRepository.findById(post.getRentPostId()).orElse(null);
        if (result == null)
            return new ChildCommonDto<>(TRUE.getMsg(), HttpStatus.OK, rentPostMapper.RentPostToRentPostResponse(result));
        else
            return new ChildCommonDto<>(FALSE.getMsg(), HttpStatus.BAD_REQUEST, rentPostMapper.RentPostToRentPostResponse(result));
    }

    public ChildCommonDto<RentPostResponseDto> getRentPost(Integer postId) {

        RentPost result = rentPostRepository.findById(postId).orElse(null);

        result.setViewCount(result.getViewCount() + 1);
        rentPostRepository.flush();

        return new ChildCommonDto<>(TRUE.getMsg(), HttpStatus.OK, rentPostMapper.RentPostToRentPostResponse(result));
    }
}
