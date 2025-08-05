package com.mir.survey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mir.survey.entity.Answer;
import com.mir.survey.persistence.AnswerRepo;
import com.mir.survey.service.AnswerService;
@Service
public class DefaultAnswerService implements AnswerService {

	@Autowired
	AnswerRepo answerRepo;


	@Override
	public void saveAnswer(Answer answer)
	{
		answerRepo.save(answer);
	}

	@Override
	public List<Answer> getAnswersByJsessionID(String jsessionid)
	{
		return answerRepo.findByjsessionid(jsessionid);
	}
}
