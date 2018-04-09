package com.simplelearner.simplelearner.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addStudent(@RequestParam String name, @RequestParam String password,
                           @RequestParam(name = "firstname") String firstName,
                           @RequestParam(name = "lastname") String lastName) {
        studentService.save(new Student(name, password, firstName, lastName));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}
