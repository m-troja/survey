package com.mir.survey.service;

import com.mir.survey.entity.Survey;

public interface SurveyService {

	 Survey getSurvey(String jsessionid);
	 Survey getSurveyWithNoAnswers();
	 boolean validateSurvey(Survey survey);
	 void saveSurvey(Survey survey, String jsessionid);
	 }
