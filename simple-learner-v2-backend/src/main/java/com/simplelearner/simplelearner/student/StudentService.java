package com.simplelearner.simplelearner.student;

import com.simplelearner.simplelearner.answer.Answer;
import com.simplelearner.simplelearner.answer.AnswerRepository;
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
    private StudentSolvedTaskRepository studentSolvedTaskRepository;
    private TaskRepository taskRepository;
    private AnswerRepository answerRepository;

    @Autowired
    StudentService(StudentRepository studentRepository, SectionRepository sectionRepository,
                   StudentSolvedTaskRepository studentSolvedTaskRepository, TaskRepository taskRepository,
                   AnswerRepository answerRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.studentSolvedTaskRepository = studentSolvedTaskRepository;
        this.taskRepository = taskRepository;
        this.answerRepository = answerRepository;
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

    StudentSolvedTask checkAnswer(String studentName, Long taskId, Long answerId) {
        Optional<Student> studentOptional = studentRepository.findById(studentName);
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        Optional<Answer> answerOptional = answerRepository.findById(answerId);

        if (studentOptional.isPresent() && taskOptional.isPresent() && answerOptional.isPresent()) {
            Student student = studentOptional.get();
            Task task = taskOptional.get();
            Answer answer = answerOptional.get();
            StudentSolvedTask studentSolvedTask = new StudentSolvedTask(student, task, answer.getIsCorrect());
            studentSolvedTaskRepository.save(studentSolvedTask);
            return studentSolvedTask;
        }
        return null;
    }
}
