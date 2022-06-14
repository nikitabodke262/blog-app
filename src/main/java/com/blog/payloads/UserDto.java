package com.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//following is the data taken from the api and not from entity
// used for data transfer 
public class UserDto {
	
	private int id;
	
	@NotEmpty(message = "Can not be empty")
	@Size(min = 4 , message ="Username must have atleast 4 characters")
	private String name;
	
	@Email(message="Invalid Email")
	private String email;
	
	@NotEmpty(message = "Can not be empty")
	@Size(min = 3, max=10 , message ="Password must be atleast of 3 char and maximum of 10")
	private String password;
	
	@NotEmpty(message = "Can not be empty")
	private String about;

//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private List<Post> posts = new ArrayList<>(); 
//	
}
