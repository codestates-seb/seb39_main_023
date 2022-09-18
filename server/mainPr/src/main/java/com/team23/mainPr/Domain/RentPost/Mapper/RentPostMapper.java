package com.team23.mainPr.Domain.RentPost.Mapper;

import com.team23.mainPr.Domain.RentPost.Dto.CreateRentPostDto;
import com.team23.mainPr.Domain.RentPost.Dto.RentPostResponseDto;
import com.team23.mainPr.Domain.RentPost.Entity.RentPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentPostMapper {
    RentPostResponseDto RentPostToRentPostResponse(RentPost rentPost);

    RentPost createMap(CreateRentPostDto dto);
}
