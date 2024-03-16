package com.roze.service.admin.category;

import com.roze.dto.CategoryDto;
import com.roze.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
    List<Category>getAllCategories();
}
