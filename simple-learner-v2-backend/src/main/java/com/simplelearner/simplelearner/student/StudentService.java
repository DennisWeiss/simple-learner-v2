package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.section.Section;
import com.simplelearner.simplelearner.section.SectionRepository;
import com.simplelearner.simplelearner.task.Task;
import com.simplelearner.simplelearner.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private TaskRepository taskRepository;

    @Autowired
    StudentService(StudentRepository studentRepository, SectionRepository sectionRepository, TaskRepository taskRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.taskRepository = taskRepository;
    }

    void save(Student student) {
        studentRepository.save(student);
    }

    Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    Student addSection(String studentName, String sectionName) {
        Optional<Student> studentOptional = studentRepository.findById(studentName);
        Optional<Section> sectionOptional = sectionRepository.findById(sectionName);
        if (studentOptional.isPresent() && sectionOptional.isPresent()) {
            Student student = studentOptional.get();
            Section section = sectionOptional.get();
            student.addSection(section);
            studentRepository.save(student);
            return student;
        }
        return null;
    }

    Student addTask(String studentName, Long taskId) {
        Optional<Student> studentOptional = studentRepository.findById(studentName);
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (studentOptional.isPresent() && taskOptional.isPresent()) {
            Student student = studentOptional.get();
            Task task = taskOptional.get();
            student.addTask(task);
            studentRepository.save(student);
            return student;
        }
        return null;
    }
}
