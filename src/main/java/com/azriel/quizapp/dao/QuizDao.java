package com.azriel.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.azriel.quizapp.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
