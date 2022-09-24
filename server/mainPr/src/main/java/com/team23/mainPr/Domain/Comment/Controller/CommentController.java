package com.team23.mainPr.Domain.Comment.Controller;

import com.team23.mainPr.Domain.Comment.Dto.Request.CreateCommentEntityDto;
import com.team23.mainPr.Domain.Comment.Dto.Request.UpdateCommentEntityDto;
import com.team23.mainPr.Domain.Comment.Dto.Response.CommentEntityResponseDto;
import com.team23.mainPr.Domain.Comment.Dto.Response.CommentEntityResponseDtos;
import com.team23.mainPr.Domain.Comment.Service.CommentService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public CommentEntityResponseDto postComment(@RequestBody @Valid CreateCommentEntityDto dto,
        @RequestHeader(value = "Authorization") String token) {
        return commentService.createCommentEntity(dto, token);
    }

    @GetMapping
    public CommentEntityResponseDto getComment(
        @RequestParam @Min(value = 1, message = "commentId must be above 1") Integer commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/update")
    public CommentEntityResponseDto updateComment(@RequestBody @Valid UpdateCommentEntityDto dto,
        @RequestHeader(value = "Authorization") String token) {
        return commentService.updateCommentEntity(dto, token);
    }

    @PostMapping("/delete")
    public String deleteCommentEntity(
        @RequestParam @Min(value = 1, message = "commentId must be above 1") Integer commentId,
        @RequestHeader(value = "Authorization") String token) {
        return commentService.deleteCommentEntity(commentId, token);
    }

    @GetMapping("/getComments")
    public CommentEntityResponseDtos getComments(
        @RequestParam @Valid @Min(value = 1, message = "postId must be above 1") Integer postId) {
        return commentService.getComments(postId);
    }
}
