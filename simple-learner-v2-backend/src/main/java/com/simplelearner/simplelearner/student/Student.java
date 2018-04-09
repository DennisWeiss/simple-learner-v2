package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.section.Section;
import com.simplelearner.simplelearner.task.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    private String name;

    private String password;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(name = "student_section",
            joinColumns = @JoinColumn(name = "student"),
            inverseJoinColumns = @JoinColumn(name = "section"))
    private List<Section> sections;

    @ManyToMany
    @JoinTable(name = "student_task",
            joinColumns = @JoinColumn(name = "student"),
            inverseJoinColumns = @JoinColumn(name = "task"))
    private List<Task> tasks;

    public Student() {
        this.sections = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    public Student(String name, String password, String firstName, String lastName) {
        this();
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
