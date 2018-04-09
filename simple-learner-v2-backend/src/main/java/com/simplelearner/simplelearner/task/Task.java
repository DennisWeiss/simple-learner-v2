package com.simplelearner.simplelearner.task;

import com.simplelearner.simplelearner.answer.Answer;
import com.simplelearner.simplelearner.section.Section;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Section section;

    @Column(length = 500, nullable = false)
    private String question;

    @OneToMany
    private List<Answer> answers;

    public Task() {
        this.answers = new ArrayList<>();
    }

    public Task(Section section, String question) {
        this();
        this.section = section;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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
}
