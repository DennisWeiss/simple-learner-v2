package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.section.Section;
import com.simplelearner.simplelearner.section.SectionRepository;
import com.simplelearner.simplelearner.task.Task;
import com.simplelearner.simplelearner.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    Student addSections(String studentName, String[] sectionNames) {
        Optional<Student> studentOptional = studentRepository.findById(studentName);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            for (String sectionName : sectionNames) {
                Optional<Section> sectionOptional = sectionRepository.findById(sectionName);
                if (sectionOptional.isPresent()) {
                    Section section = sectionOptional.get();
                    student.addSection(section);
                }
            }
            studentRepository.save(student);
            return student;
        }
        return null;
    }

    Student addTasks(String studentName, List<Long> taskIds) {
        Optional<Student> studentOptional = studentRepository.findById(studentName);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            for (Long taskId : taskIds) {
                Optional<Task> taskOptional = taskRepository.findById(taskId);
                if (taskOptional.isPresent()) {
                    Task task = taskOptional.get();
                    student.addTask(task);
                }
            }
            studentRepository.save(student);
            return student;
        }
        return null;
    }
}
