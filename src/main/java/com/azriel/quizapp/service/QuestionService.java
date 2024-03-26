package com.azriel.quizapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.azriel.quizapp.entity.Question;
import com.azriel.quizapp.dao.QuestionDao;

@Service
public class QuestionService {
	
	QuestionDao questionDao;
	
	public QuestionService(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	public List<Question> getQuestionsByCategory(String category) {
		return questionDao.findByCategory(category);
	}

	public String deleteQuestion(Integer id) {
		questionDao.deleteById(id);
		return "Question with the id: "+id+" was deleted";
	}

	public String addQuestion(Question question) {
		questionDao.save(question);
		return "The adding was succesful!";
	}

	public String updateQuestion(Question question) {
		questionDao.save(question);
		return "Update success!";
	}
	
	

}
