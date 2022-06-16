 package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.Comment;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDto;
import com.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/{pId}")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
			@PathVariable Integer pId ){
		CommentDto createCommentDto = this.commentService.createComment(commentDto, pId);
		return new ResponseEntity<CommentDto>(createCommentDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{pId}")
	public ResponseEntity<ApiResponse> deleteComment(
			@PathVariable Integer cId ){
		this.commentService.deleteComment(cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted comment successfully", true), HttpStatus.OK);
	}
	

}
