package com.simplelearner.simplelearner.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("task")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ResponseEntity<Task> newTask(@RequestBody Task task) {
        taskService.save(task);
        return new ResponseEntity<>(task, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
}
