package com.simplelearner.simplelearner.task;

import com.simplelearner.simplelearner.answer.Answer;
import com.simplelearner.simplelearner.section.Section;
import com.simplelearner.simplelearner.student.StudentSolvedTask;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;

    @OneToMany(mappedBy = "task")
    private List<StudentSolvedTask> solvedByStudents;


    public Task() {
        this.answers = new ArrayList<>();
        this.solvedByStudents = new ArrayList<>();
    }

    public Task(Section section, String question) {
        this();
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<StudentSolvedTask> getSolvedByStudents() {
        return solvedByStudents;
    }

    public void setSolvedByStudents(List<StudentSolvedTask> solvedByStudents) {
        this.solvedByStudents = solvedByStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
