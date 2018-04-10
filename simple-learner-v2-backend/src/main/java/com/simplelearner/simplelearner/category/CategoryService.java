package com.simplelearner.simplelearner.category;

import com.simplelearner.simplelearner.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    void save(Category category) {
        categoryRepository.save(category);
    }

    Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    Category addSection(String categoryName, Section section) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryName);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            category.addSection(section);
            categoryRepository.save(category);
            return category;
        }
        return null;
    }
}
