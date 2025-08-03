package com.mir.survey.service.impl;

import org.springframework.stereotype.Service;

import com.mir.survey.entity.Question;
import com.mir.survey.service.SurveyService;

@Service
public class DefaultSurveyInterface implements SurveyService {

	public Question createQuestion()
	{
		Question q = new Question();
		q.setText("test");
		return q;
	}
}
