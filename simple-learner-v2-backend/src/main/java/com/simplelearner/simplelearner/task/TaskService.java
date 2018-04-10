package com.simplelearner.simplelearner.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    void save(Task task) {
        taskRepository.save(task);
    }

    Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
