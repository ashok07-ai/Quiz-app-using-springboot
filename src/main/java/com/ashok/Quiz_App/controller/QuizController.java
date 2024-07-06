package com.ashok.Quiz_App.controller;

import com.ashok.Quiz_App.model.Question;
import com.ashok.Quiz_App.model.QuestionWrapper;
import com.ashok.Quiz_App.model.ResultResponse;
import com.ashok.Quiz_App.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("questions/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionById(@PathVariable Integer id){
        return quizService.getQuestionById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<ResultResponse> response){
        return quizService.submitResponse(id, response);
    }
}
