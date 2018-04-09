package com.simplelearner.simplelearner.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private AnswerRepository answerRepository;

    @Autowired
    AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    void save(Answer answer) {
        answerRepository.save(answer);
    }

    Iterable<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }
}
