package com.azriel.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.azriel.quizapp.dao.QuestionDao;
import com.azriel.quizapp.dao.QuizDao;
import com.azriel.quizapp.entity.Question;
import com.azriel.quizapp.entity.QuestionWrapper;
import com.azriel.quizapp.entity.Quiz;
import com.azriel.quizapp.entity.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizDao quizDao;
	private final QuestionDao questionDao;

	public String createQuiz(String title, String category, int numQuestions) {
		List<Question> questions = questionDao.findRandomQuestionsForCategory(category, numQuestions);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);

		return "success";
	}

	public List<QuestionWrapper> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);

		if (quiz.isPresent()) {
			List<Question> questionsFromDB = quiz.get().getQuestions();
			List<QuestionWrapper> questionsForUser = new ArrayList<>();
			for (Question q : questionsFromDB) {
				QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
						q.getOption2(), q.getOption3(), q.getOption4());
				questionsForUser.add(qw);
			}
			return questionsForUser;
		} else {
			return null;
		}

	}

	public Integer calculateResult(Integer id, List<Response> responses) {
		Optional<Quiz> quiz = quizDao.findById(id);
		if (quiz.isPresent()) {
			List<Question> questions = quiz.get().getQuestions();
			int right = 0;
			int i = 0;
			for (Response response : responses) {
				if (response.getResponse().equals(questions.get(i).getRightAnswer()))
					right++;

				i++;
			}
			return right;
		} else {
			return null;
		}

	}

}
