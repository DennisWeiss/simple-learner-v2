package com.simplelearner.simplelearner.subject;

import com.simplelearner.simplelearner.category.Category;
import com.simplelearner.simplelearner.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    SubjectService(SubjectRepository subjectRepository, CategoryRepository categoryRepository) {
        this.subjectRepository = subjectRepository;
        this.categoryRepository = categoryRepository;
    }

    void save(Subject subject) {
        subjectRepository.save(subject);
    }

    Iterable<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    Subject addCategories(String subjectName, String[] categoryNames) {
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectName);
        if (subjectOptional.isPresent()) {
            Subject subject = subjectOptional.get();
            for (String categoryName : categoryNames) {
                Optional<Category> categoryOptional = categoryRepository.findById(categoryName);
                if (categoryOptional.isPresent()) {
                    Category category = categoryOptional.get();
                    subject.addCategory(category);
                }
            }
            subjectRepository.save(subject);
            return subject;
        }
        return null;
    }
}
