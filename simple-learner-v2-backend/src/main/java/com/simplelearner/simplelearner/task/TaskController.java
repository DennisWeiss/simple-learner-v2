package com.simplelearner.simplelearner.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
