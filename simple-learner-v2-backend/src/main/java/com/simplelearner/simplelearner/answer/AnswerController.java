package com.simplelearner.simplelearner.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("answer")
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void saveAnswer(@RequestParam String text, @RequestParam boolean correct) {
        answerService.save(new Answer(text, correct));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }
}
