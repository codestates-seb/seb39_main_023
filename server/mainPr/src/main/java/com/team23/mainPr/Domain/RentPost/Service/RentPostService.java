package com.team23.mainPr.Domain.RentPost.Service;

import com.team23.mainPr.Domain.Picture.Entity.Picture;
import com.team23.mainPr.Domain.Picture.Repository.PictureRepository;
import com.team23.mainPr.Domain.RentPost.Dto.Request.CreateRentPostEntityDto;
import com.team23.mainPr.Domain.RentPost.Dto.Request.UpdateRentPostDto;
import com.team23.mainPr.Domain.RentPost.Dto.Response.PagedRentPostResponseDtos;
import com.team23.mainPr.Domain.RentPost.Dto.Response.RentPostResponseDto;
import com.team23.mainPr.Domain.RentPost.Entity.RentPost;
import com.team23.mainPr.Domain.RentPost.Mapper.RentPostMapper;
import com.team23.mainPr.Domain.RentPost.Repository.RentPostRepository;
import com.team23.mainPr.Global.DefaultTimeZone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public String postImages(List<MultipartFile> files, Integer postId) throws IOException {

        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                String uuid = UUID.randomUUID().toString();
                File newFileName = new File(System.getProperty("user.home") + uploadPath + "/" + uuid + "_" + file.getOriginalFilename());
                file.transferTo(newFileName);

                pictureRepository.save(new Picture(uuid + "_" + file.getOriginalFilename(), postId)).getImageId();
            }
        }

        return SUCCESS.getMsg();
    }

    public List<Integer> getPostImages(Integer postId) {

        List<Integer> result = new ArrayList<>();

        pictureRepository.findByPostId(postId).stream().forEach(picture -> {
            result.add(picture.getImageId());
        });

        return result;
    }

    public Resource getImage(Integer imageId) throws IOException {

        Path path = Paths.get(System.getProperty("user.home") + uploadPath + "/" + pictureRepository.getReferenceById(imageId).getFileName());

        return new InputStreamResource(Files.newInputStream(path));
    }


    public PagedRentPostResponseDtos getRentPosts(Pageable pageable, Boolean rentStatus, String category) {

        Page<RentPost> result = rentPostRepository.findAllByRentStatusAndCategoryContaining(pageable, rentStatus, category);

        List<RentPostResponseDto> mappedResult = new ArrayList<>();
        result.stream().forEach(rentPost -> {
            mappedResult.add(rentPostMapper.RentPostToRentPostResponseDto(rentPost));
        });

        PagedRentPostResponseDtos response = rentPostMapper.PagedRentPostToRentPostPagedResponseDto(mappedResult, result.getPageable());

        return response;
    }

    public List<RentPostResponseDto> searchAll(String phrase) {
        List<RentPostResponseDto> result = new ArrayList<>();
        rentPostRepository.search("" + phrase.replace(" ", "|").trim() + "")
                .stream().forEach(
                        rentPostId ->{
                            result.add(rentPostMapper.RentPostToRentPostResponseDto(rentPostRepository.getReferenceById(rentPostId)));
                        });
        return result;
    }

    public List<RentPostResponseDto> ftSearchAll(String phrase) {
        List<RentPostResponseDto> result = new ArrayList<>();
        rentPostRepository.ftSearch(""+phrase+"")
                .stream().forEach(
                        rentPost ->{
                            result.add(rentPostMapper.RentPostToRentPostResponseDto((RentPost) rentPost));
                        });
        return result;
    }
}
