package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.RegistrationStatus;
import com.simplelearner.simplelearner.answer.Answer;
import com.simplelearner.simplelearner.answer.AnswerRepository;
import com.simplelearner.simplelearner.section.Section;
import com.simplelearner.simplelearner.section.SectionRepository;
import com.simplelearner.simplelearner.task.Task;
import com.simplelearner.simplelearner.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private StudentSolvedTaskRepository studentSolvedTaskRepository;
    private TaskRepository taskRepository;
    private AnswerRepository answerRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    StudentService(StudentRepository studentRepository, SectionRepository sectionRepository,
                   StudentSolvedTaskRepository studentSolvedTaskRepository, TaskRepository taskRepository,
                   AnswerRepository answerRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.studentSolvedTaskRepository = studentSolvedTaskRepository;
        this.taskRepository = taskRepository;
        this.answerRepository = answerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    RegistrationStatus register(String name, String password, String firstName, String lastName) {
        Optional<Student> existingStudent = studentRepository.findById(name);
        if (existingStudent.isPresent()) {
            return RegistrationStatus.USER_EXISTS_ALREADY;
        }
        Student student = new Student(name, passwordEncoder.encode(password), firstName, lastName);
        studentRepository.save(student);
        return RegistrationStatus.SUCCESS;
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
            return studentRepository.save(student);
        }
        return null;
    }

    StudentSolvedTask checkAnswer(String studentName, Long taskId, Long answerId) {
        Optional<Student> studentOptional = studentRepository.findById(studentName);
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Optional<Answer> answerOptional = answerRepository.findById(answerId);

        if (studentOptional.isPresent() && taskOptional.isPresent() && answerOptional.isPresent()) {
            Student student = studentOptional.get();
            Task task = taskOptional.get();
            Answer answer = answerOptional.get();
            StudentSolvedTask studentSolvedTask = new StudentSolvedTask(student, task, answer.getIsCorrect());
            return studentSolvedTaskRepository.save(studentSolvedTask);
        }
        return null;
    }
}
