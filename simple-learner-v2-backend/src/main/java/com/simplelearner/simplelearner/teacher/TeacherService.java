package com.simplelearner.simplelearner.teacher;

import com.simplelearner.simplelearner.section.Section;
import com.simplelearner.simplelearner.section.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    private TeacherRepository teacherRepository;
    private SectionRepository sectionRepository;

    @Autowired
    TeacherService(TeacherRepository teacherRepository, SectionRepository sectionRepository) {
        this.teacherRepository = teacherRepository;
        this.sectionRepository = sectionRepository;
    }

    void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    Teacher addSections(String teacherName, String[] sectionNames) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherName);
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            for (String sectionName : sectionNames) {
                Optional<Section> sectionOptional = sectionRepository.findById(sectionName);
                if (sectionOptional.isPresent()) {
                    Section section = sectionOptional.get();
                    teacher.addSection(section);
                }
            }
            teacherRepository.save(teacher);
            return teacher;
        }
        return null;
    }
}
