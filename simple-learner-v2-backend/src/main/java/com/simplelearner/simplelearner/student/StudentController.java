package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.RegistrationStatus;
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegistrationStatus register(@RequestParam String name, @RequestParam String password,
                                       @RequestParam(name = "firstname") String firstName,
                                       @RequestParam(name = "lastname") String lastName) {
        return studentService.register(name, password, firstName, lastName);
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

    @RequestMapping(value = "{studentName}/{taskId}/answer", method = RequestMethod.GET)
    public ResponseEntity<StudentSolvedTask> checkAnswer(@PathVariable String studentName, @PathVariable Long taskId,
                                                         @RequestParam(name = "answerid") Long answerId) {
        StudentSolvedTask studentSolvedTask = studentService.checkAnswer(studentName, taskId, answerId);
        return new ResponseEntity<>(studentSolvedTask, new HttpHeaders(), studentSolvedTask == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
}
