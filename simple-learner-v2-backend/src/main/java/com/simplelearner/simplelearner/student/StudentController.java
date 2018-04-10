package com.simplelearner.simplelearner.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "addsection", method = RequestMethod.POST)
    public ResponseEntity<Student> addSection(@RequestParam(name = "studentname") String studentName, @RequestParam(name = "sectionname") String sectionName) {
        Student student = studentService.addSection(studentName, sectionName);
        return new ResponseEntity<>(student, new HttpHeaders(), student == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }

    @RequestMapping(value = "addtask", method = RequestMethod.POST)
    public ResponseEntity<Student> addTask(@RequestParam(name = "studentname") String studentName,
                                           @RequestParam(name = "taskid") Long taskId) {
        Student student = studentService.addTask(studentName, taskId);
        return new ResponseEntity<>(student, new HttpHeaders(), student == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
}
