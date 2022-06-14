package com.blog.payloads;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class PostDto {
	
	private int id;
	
	@NotEmpty(message = "Can not be empty")
	@Size(min = 4 , message ="Content must have atleast 4 characters")
	private String content;
	
	@NotEmpty(message = "Can not be empty")
	private String title;
	
	private String imageName;
	
	private Date addedDate;

	private CategoryDto category;
	
	private UserDto user; 



}
