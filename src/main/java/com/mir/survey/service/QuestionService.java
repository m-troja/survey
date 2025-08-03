package com.mir.survey.service;

import java.util.List;

import com.mir.survey.entity.Question;

public interface QuestionService {
	public void saveQuestion(Question question);
	public List<Question> getQuestions();
}
