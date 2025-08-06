package com.mir.survey.service;

import java.util.List;

import com.mir.survey.entity.Question;

public interface QuestionService {
	void saveQuestion(Question question);
	List<Question> getQuestions();
}
