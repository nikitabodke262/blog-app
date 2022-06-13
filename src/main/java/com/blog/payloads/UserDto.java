package com.blog.payloads;

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
	private String name;
	private String email;
	private String password;
	private String about;


}
