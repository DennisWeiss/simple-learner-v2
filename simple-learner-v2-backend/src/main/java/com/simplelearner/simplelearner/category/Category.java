package com.simplelearner.simplelearner.category;

import com.simplelearner.simplelearner.section.Section;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Section> sections;

    public Category() {
        sections = new ArrayList<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void addSections(List<Section> sections) {
        this.sections.addAll(sections);
    }
}
