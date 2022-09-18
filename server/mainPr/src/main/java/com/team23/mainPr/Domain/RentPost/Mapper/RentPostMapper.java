package com.team23.mainPr.domain.RentPost.Mapper;

import com.team23.mainPr.domain.RentPost.Dto.CreateRentPostDto;
import com.team23.mainPr.domain.RentPost.Dto.RentPostResponseDto;
import com.team23.mainPr.domain.RentPost.Entity.RentPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentPostMapper {
    RentPostResponseDto RentPostToRentPostResponse(RentPost rentPost);

    RentPost createMap(CreateRentPostDto dto);
}
