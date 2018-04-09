package com.simplelearner.simplelearner.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    void save(Student student) {
        studentRepository.save(student);
    }

    Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
