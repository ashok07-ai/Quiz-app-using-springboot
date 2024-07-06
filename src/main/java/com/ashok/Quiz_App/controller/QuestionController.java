package com.ashok.Quiz_App.controller;

import com.ashok.Quiz_App.model.Question;
import com.ashok.Quiz_App.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionsService;

    @GetMapping()
    public ResponseEntity<List<Question> > getAllQuestions(){
        return questionsService.getAllQuestions();
    }

    @GetMapping("category/{categoryType}")
    public ResponseEntity<List<Question> > getQuestionByCategory(@PathVariable String category){
        return questionsService.getQuestionByCategory(category.toUpperCase());
    }

    @PostMapping()
    public ResponseEntity<String> addQuestions(@RequestBody Question question){
        return questionsService.addQuestions(question);
    }

}
