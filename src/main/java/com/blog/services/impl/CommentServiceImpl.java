 package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDto;
import com.blog.payloads.PostResponse;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepository;
import com.blog.services.CommentService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;  
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer pId) {
		
		Post post= this.postRepository.findById(pId).orElseThrow(()->
				new ResourceNotFoundException("Post", "Post id", pId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment =  this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer cId) {
		Comment comment = this.commentRepo.findById(cId).orElseThrow(()->
				new ResourceNotFoundException("Post", "Post id", cId));
		this.commentRepo.delete(comment);
 	}

}
