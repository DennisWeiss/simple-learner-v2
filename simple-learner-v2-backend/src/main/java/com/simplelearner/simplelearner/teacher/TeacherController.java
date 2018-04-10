package com.simplelearner.simplelearner.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "{teacherName}/addsections", method = RequestMethod.POST)
    public ResponseEntity<Teacher> addSections(@PathVariable String teacherName, @RequestParam(value = "sectionnames") String sectionNames) {
        Teacher teacher = teacherService.addSections(teacherName, sectionNames.split("\\s*,\\s*"));
        return new ResponseEntity<>(teacher, new HttpHeaders(), teacher == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
}
