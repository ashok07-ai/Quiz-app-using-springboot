package com.ashok.Quiz_App.service;

import com.ashok.Quiz_App.dao.QuestionDao;
import com.ashok.Quiz_App.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            List<Question> questions = questionDao.findAll();
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategoryType(String categoryType) {
        try {
            List<Question> questions = questionDao.findByCategory(categoryType);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    public ResponseEntity<String> addQuestions(Question question) {
        try {
            Question savedQuestion = questionDao.save(question);
            return ResponseEntity.status(HttpStatus.CREATED).body("Question added successfully: ");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add question: " + e.getMessage());
        }
    }
}
