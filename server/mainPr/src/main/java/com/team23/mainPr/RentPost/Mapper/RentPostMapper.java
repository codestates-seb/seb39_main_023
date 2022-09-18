package com.team23.mainPr.RentPost.Mapper;

import com.team23.mainPr.RentPost.Dto.CreateRentPostDto;
import com.team23.mainPr.RentPost.Dto.RentPostResponseDto;
import com.team23.mainPr.RentPost.Entity.RentPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentPostMapper {
    RentPostResponseDto RentPostToRentPostResponse(RentPost rentPost);

    RentPost createMap(CreateRentPostDto dto);
}
