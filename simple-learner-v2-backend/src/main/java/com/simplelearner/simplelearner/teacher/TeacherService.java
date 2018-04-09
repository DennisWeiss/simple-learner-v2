package com.simplelearner.simplelearner.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;

    @Autowired
    TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
}
