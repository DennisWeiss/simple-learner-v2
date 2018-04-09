package com.simplelearner.simplelearner.section;

import com.simplelearner.simplelearner.category.Category;
import com.simplelearner.simplelearner.teacher.Teacher;

import javax.persistence.*;

@Entity
public class Section {
    @Id
    private String name;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Category category;

    public Section() {}

    public Section(String name, Teacher teacher, Category category) {
        this.name = name;
        this.teacher = teacher;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
