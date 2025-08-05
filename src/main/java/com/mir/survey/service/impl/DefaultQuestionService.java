package com.mir.survey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mir.survey.entity.Question;
import com.mir.survey.persistence.QuestionRepo;
import com.mir.survey.service.QuestionService;

@Service
public class DefaultQuestionService implements QuestionService {

	@Autowired
	QuestionRepo questionRepo;

	@Override
	public void saveQuestion(Question question) {
		questionRepo.save(question);
	}

	@Override
	public List<Question> getQuestions() {
		return questionRepo.findAll();
	}


}
