package com.team23.mainPr.Domain.RentPost.Mapper;

import com.team23.mainPr.Domain.RentPost.Dto.Request.CreateRentPostEntityDto;
import com.team23.mainPr.Domain.RentPost.Dto.Response.RentPostResponseDto;
import com.team23.mainPr.Domain.RentPost.Entity.RentPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentPostMapper {
    RentPostResponseDto RentPostToRentPostResponse(RentPost rentPost);

    RentPost CreateRentPostEntityDtoToRentPost(CreateRentPostEntityDto dto);
}
