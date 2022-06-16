package com.blog.services.impl;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.blog.entities.*;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.payloads.UserDto;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepo;
import com.blog.services.PostService;

import org.springframework.data.domain.Sort;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public PostDto createPost(PostDto postDto ,Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->
				new ResourceNotFoundException("User", "User id", userId));
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->
			new ResourceNotFoundException("Category", "Category id", categoryId));

		Post  post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepository.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updaPost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() 
				-> new ResourceNotFoundException( "Post", " id ", postId));
		
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setImageName(postDto.getImageName());
	Post updatedPost = this.postRepository.save(post);
	PostDto postDtos = this.modelMapper.map(updatedPost, PostDto.class);
	return postDtos;	
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException( "Post", " id ", postId));
		this.postRepository.delete(post);
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->
				new ResourceNotFoundException("Post", "Post id", postId));
		PostDto postDtos = this.modelMapper.map(post, PostDto.class);
		
		return postDtos;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

		Pageable pag= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page <Post> pagePost = this.postRepository.findAll(pag);
		List <Post> allPosts = pagePost.getContent();
		
		List <PostDto> postDtos = pagePost.stream().map((p) -> 
				this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize( pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer catId) {
		Category category = this.categoryRepository.findById(catId).orElseThrow(()->
				new ResourceNotFoundException("Category", "Category id", catId));
		List <Post> posts = this.postRepository.findByCategory(category);
		List <PostDto> postDtos =  posts.stream().map((post) -> 
					this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User", "User id", userId));
		
		List <Post> posts = this.postRepository.findByUser(user);
		List <PostDto> postDtos =  posts.stream().map((post) -> 
			this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List <Post> list = this.postRepository.findByTitleContaining(keyword);
		List <PostDto> postDtos =  list.stream().map((post) -> 
		this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
		return postDtos;
	}
	

}
