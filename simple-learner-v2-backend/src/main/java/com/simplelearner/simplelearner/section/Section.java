package com.simplelearner.simplelearner.section;

import com.simplelearner.simplelearner.task.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {
    @Id
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Section() {
        tasks = new ArrayList<>();
    }

    public Section(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTasks(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }
}
