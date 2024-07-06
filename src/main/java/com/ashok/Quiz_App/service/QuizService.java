package com.ashok.Quiz_App.service;

import com.ashok.Quiz_App.dao.QuestionDao;
import com.ashok.Quiz_App.dao.QuizDao;
import com.ashok.Quiz_App.model.Question;
import com.ashok.Quiz_App.model.QuestionWrapper;
import com.ashok.Quiz_App.model.Quiz;
import com.ashok.Quiz_App.model.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category.toUpperCase(), numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);

        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully!!", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionById(Integer id) {
        Optional<Quiz> quizResponse = quizDao.findById(id);
        List<Question> questionsFromDB = quizResponse.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }


    public ResponseEntity<Integer> submitResponse(Integer id, List<ResultResponse> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int rightAnswerCount = 0;
        int index = 0;

        for(ResultResponse response: responses){
            if(response.getResponse().equals(questions.get(index).getRightAnswer()))
                rightAnswerCount ++;
            index++;
        }

        return new ResponseEntity<>(rightAnswerCount, HttpStatus.OK);

    }
}
