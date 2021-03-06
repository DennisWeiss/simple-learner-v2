package com.simplelearner.simplelearner.category;

import com.simplelearner.simplelearner.section.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ResponseEntity<Category> newCategory(@RequestParam String name) {
        Category category = new Category(name);
        categoryService.save(category);
        return new ResponseEntity<>(category, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "{name}/addsections", method = RequestMethod.POST)
    public ResponseEntity<Category> addSections(@PathVariable String name, @RequestBody List<Section> sections) {
        Category category = categoryService.addSections(name, sections);
        return new ResponseEntity<>(category, new HttpHeaders(), category == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
}
