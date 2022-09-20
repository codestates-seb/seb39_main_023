package com.team23.mainPr.Domain.RentPost.Service;

import com.team23.mainPr.Domain.Member.Entity.Member;
import com.team23.mainPr.Domain.Picture.Entity.Picture;
import com.team23.mainPr.Domain.Picture.Repository.PictureRepository;
import com.team23.mainPr.Domain.RentPost.Dto.Request.CreateRentPostEntityDto;
import com.team23.mainPr.Domain.RentPost.Dto.Request.UpdateRentPostDto;
import com.team23.mainPr.Domain.RentPost.Dto.Response.RentPostResponseDto;
import com.team23.mainPr.Domain.RentPost.Entity.RentPost;
import com.team23.mainPr.Domain.RentPost.Mapper.RentPostMapper;
import com.team23.mainPr.Domain.RentPost.Repository.RentPostRepository;
import com.team23.mainPr.Global.DefaultTimeZone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.team23.mainPr.Global.Enum.ChildCommonDtoMsgList.SUCCESS;
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
    private final PictureRepository pictureRepository;

    @Value("${multipart.upload.path}")
    String uploadPath;

    public RentPostResponseDto createRentPost(CreateRentPostEntityDto dto) {

        RentPost result = rentPostRepository.save(rentPostMapper.CreateRentPostEntityDtoToRentPost(dto));

        return rentPostMapper.RentPostToRentPostResponseDto(result);
    }

    public RentPostResponseDto updateRentPost(Integer postId, UpdateRentPostDto dto) {

        RentPost post = rentPostRepository.getReferenceById(postId);

        RentPost updatedPost = dto.updateData(post, dto);

        rentPostRepository.flush();

        return rentPostMapper.RentPostToRentPostResponseDto(updatedPost);
    }

    public String deleteRentPost(Integer postId) {

        rentPostRepository.delete(rentPostRepository.getReferenceById(postId));

        return TRUE.getMsg();
    }

    public RentPostResponseDto getRentPost(Integer postId) {

        RentPost result = rentPostRepository.getReferenceById(postId);

        result.setViewCount(result.getViewCount() + 1);
        rentPostRepository.flush();

        return rentPostMapper.RentPostToRentPostResponseDto(result);
    }


}
