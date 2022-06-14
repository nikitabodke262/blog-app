package com.blog.services;

import java.util.List;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updaPost (PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	PostDto getPostById(Integer postId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	List<PostDto> getPostByCategory(Integer catId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> searchPosts(String keyword);
	
}
