package com.simplelearner.simplelearner.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public void saveTeacher(@RequestParam String name, @RequestParam String password,
                            @RequestParam(name = "firstname") String firstName,
                            @RequestParam(name = "lastname") String lastName) {
        teacherService.save(new Teacher(name, password, firstName, lastName));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }
}
