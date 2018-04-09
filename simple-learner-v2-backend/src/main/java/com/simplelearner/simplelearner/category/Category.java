package com.simplelearner.simplelearner.category;

import com.simplelearner.simplelearner.subject.Subject;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    private String name;

    @ManyToOne
    private Subject subject;

    public Category() {}

    public Category(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
