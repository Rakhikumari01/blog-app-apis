package com.codewithrakhi.blog.services;

import com.codewithrakhi.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

  //create
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);


    void deleteCategory(Integer categoryId);


    CategoryDto getCategory(Integer categoryId);

    List<CategoryDto> getCategories();
}
