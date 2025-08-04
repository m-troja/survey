package com.mir.survey.service;

import java.util.List;

import com.mir.survey.entity.Answer;

public interface AnswerService {

	 void saveAnswer(Answer answer);
	 List<Answer> getAnswersByJsessionID(String jsessionid);
}
