package com.simplelearner.simplelearner.subject;

import com.simplelearner.simplelearner.category.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {
    @Id
    private String name;

    @Column(length = 4, unique = true, nullable = false)
    private String abbreviation;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> categories;

    public Subject() {
        this.categories = new ArrayList<>();
    }

    public Subject(String name, String abbreviation) {
        this();
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }
}
