package com.ashok.Quiz_App.dao;

import com.ashok.Quiz_App.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
