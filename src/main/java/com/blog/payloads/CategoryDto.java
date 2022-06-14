package com.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty(message = "Can not be empty")
	@Size(min = 5 , message ="Title must have atleast 5 characters")
	private String categoryTitle;
	
	@NotEmpty(message = "Can not be empty")
	@Size(min = 5 , message ="Description must have atleast 5 characters")
	private String categoryDescription;
}
