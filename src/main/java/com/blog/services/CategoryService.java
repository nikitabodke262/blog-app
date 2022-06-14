package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {

	 CategoryDto createCategory(CategoryDto categoryDto);
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);
	 void deleteCategory(Integer catId);
	 CategoryDto getCategoryById(Integer catId) ;
     List <CategoryDto> getCategories();
	
}
