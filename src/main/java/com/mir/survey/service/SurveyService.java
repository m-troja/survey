package com.mir.survey.service;

import java.util.List;

import com.mir.survey.entity.Question;
import com.mir.survey.entity.Survey;

public interface SurveyService {
	
	 public List<Question> getQuestions();
	 public Survey getSurvey();
}
