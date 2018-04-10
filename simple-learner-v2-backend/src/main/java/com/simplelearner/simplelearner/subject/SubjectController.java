package com.simplelearner.simplelearner.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subject")
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void addSubject(@RequestParam String name, @RequestParam String abbreviation) {
        subjectService.save(new Subject(name, abbreviation));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @RequestMapping(value = "{subjectName}/addcategories")
    public ResponseEntity<Subject> addCategory(@PathVariable String subjectName,
                                               @RequestParam(value = "categorynames") String categoryNames) {
        Subject subject = subjectService.addCategories(subjectName, categoryNames.split("\\s*,\\s*"));
        return new ResponseEntity<>(subject, new HttpHeaders(), subject == null ? HttpStatus.NOT_FOUND : HttpStatus.CREATED);
    }
}
