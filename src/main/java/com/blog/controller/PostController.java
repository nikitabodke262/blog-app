package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.App_Constants;
import com.blog.entities.Post;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
//	create posts
	@PostMapping("/user/{uId}/category/{cId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer uId,
			@PathVariable Integer cId ){
		
		PostDto createPost = this.postService.createPost(postDto, uId, cId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
//	Get posts by user
	@GetMapping("/user/{uId}/posts")
	public ResponseEntity <List<PostDto>> getPostByUser(
			@PathVariable Integer uId){
	
		List<PostDto>  posts=this.postService.getPostByUser(uId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
//	.Get by category
	@GetMapping("/category/{cId}/posts")
	public ResponseEntity <List<PostDto>> getPostByCategory(
			@PathVariable Integer cId){
	
		List<PostDto>  posts=this.postService.getPostByCategory(cId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	
	
//	get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber", defaultValue = App_Constants.PAGE_NUMBER, required = false) Integer pageNumber ,
			@RequestParam(value = "pageSize", defaultValue = App_Constants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value= "sortBy", defaultValue = App_Constants.SORT_BY, required = false) String sortBy){
		PostResponse  postResponse=this.postService.getAllPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);	
	}
	 
	
//	Get by id
	@GetMapping("/posts/{pId}")
	public ResponseEntity<PostDto> getPostById(
					@PathVariable Integer pId){
		PostDto postDto=this.postService.getPostById(pId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);	
	}
	
	
//	Update post 
	@PutMapping("/posts/{pId}")
	public ResponseEntity<PostDto> updaPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer pId){
			PostDto pDto=this.postService.updaPost(postDto, pId);
			return new ResponseEntity<PostDto>(pDto, HttpStatus.OK);	
	}

	
//	Delete posts
	@DeleteMapping("/posts/{pId}")
	public ResponseEntity<ApiResponse> deletePost(
			@PathVariable Integer pId){
			this.postService.deletePost( pId);
			return new ResponseEntity<ApiResponse>(new ApiResponse
					("Deleted successfully", true), HttpStatus.OK);	
	}

	
	
//	search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostsByTitle(
			@PathVariable String keywords){
		
		List <PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.FOUND);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
