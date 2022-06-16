package com.blog.services;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer pId);
	void deleteComment(Integer cId);
}
