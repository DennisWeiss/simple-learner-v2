package com.simplelearner.simplelearner.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@RequestParam String name, @RequestParam String password,
                                              @RequestParam(name = "firstname") String firstName,
                                              @RequestParam(name = "lastname") String lastName) {
        Student student = new Student(name, password, firstName, lastName);
        studentService.save(student);
        return new ResponseEntity<>(student, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "{studentName}/addsections", method = RequestMethod.POST)
    public ResponseEntity<Student> addSections(@PathVariable String studentName,
                                               @RequestParam(name = "sectionnames") String sectionNames) {
        Student student = studentService.addSections(studentName, sectionNames.split("\\s*,\\s*"));
        return new ResponseEntity<>(student, new HttpHeaders(), student == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }

    @RequestMapping(value = "{studentName}/addtasks", method = RequestMethod.POST)
    public ResponseEntity<Student> addTasks(@PathVariable String studentName,
                                            @RequestParam(name = "taskids") String taskIds) {
        Student student = studentService.addTasks(studentName,
                Arrays.stream(taskIds.split("\\s*,\\s*")).map(Long::parseLong).collect(Collectors.toList()));
        return new ResponseEntity<>(student, new HttpHeaders(), student == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
}
