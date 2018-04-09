package com.simplelearner.simplelearner.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    void save(Subject subject) {
        subjectRepository.save(subject);
    }

    Iterable<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
