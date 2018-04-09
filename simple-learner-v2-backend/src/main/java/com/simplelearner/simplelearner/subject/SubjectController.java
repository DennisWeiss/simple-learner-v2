package com.simplelearner.simplelearner.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
