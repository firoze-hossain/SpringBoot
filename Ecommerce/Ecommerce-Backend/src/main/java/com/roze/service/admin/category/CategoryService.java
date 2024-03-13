package com.roze.service.admin.category;

import com.roze.dto.CategoryDto;
import com.roze.entity.Category;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);
}
