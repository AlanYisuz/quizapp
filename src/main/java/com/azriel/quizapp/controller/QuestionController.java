package com.azriel.quizapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azriel.quizapp.Question;
import com.azriel.quizapp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	QuestionService questionService;
	
	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@GetMapping("/allQuestions")
	public ResponseEntity<Question> getAllQuestions() {
		try {
			return new ResponseEntity(questionService.getAllQuestions(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<Question> getQuestionsByCategory(@PathVariable("category")String category){
		try {
			return new ResponseEntity(questionService.getQuestionsByCategory(category), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		try {
			return new ResponseEntity(questionService.addQuestion(question), HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("id")Integer id) {
		try {
			return new ResponseEntity(questionService.deleteQuestion(id);, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/update/{id}")
	public String updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
		return questionService.updateQuestion(id, question);
	}
}
