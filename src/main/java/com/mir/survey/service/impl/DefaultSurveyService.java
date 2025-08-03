package com.mir.survey.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mir.survey.entity.Answer;
import com.mir.survey.entity.Question;
import com.mir.survey.entity.Survey;
import com.mir.survey.service.AnswerService;
import com.mir.survey.service.QuestionService;
import com.mir.survey.service.SurveyService;

@Service
public class DefaultSurveyService implements SurveyService {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	public Survey getSurvey()
	{
		  List<Question> questions = questionService.getQuestions();

		    questions.forEach(q -> {
		        List<Answer> answers = IntStream.range(0, 3)
		            .mapToObj(i -> new Answer()) // brak ID = nowy obiekt
		            .peek(a -> a.setQuestion(q)) // powiązanie pytania
		            .collect(Collectors.toList());

		        q.setAnswers(answers);
		    });

		    return new Survey(questions);
	}
	
	public void saveSurvey(Survey survey, String jsessionid)
	{
		survey.getQuestions().stream()
		.flatMap( question -> question.getAnswers().stream()
				.filter( answer -> answer.getText() != null && !answer.getText().isBlank() && !answer.getText().isEmpty())
				.peek( answer -> answer.setQuestion(question))
				.peek(answer -> answer.setJsessionid(jsessionid)))
		.forEach(answerService::saveAnswer);
	}
	
	 public boolean validateSurvey(Survey survey, String jsessionid) {
		 if ( answerService.getAnswersByJsessionID(jsessionid).isEmpty()) {
			 return true;
		 }
		 
		 return false;
	 }

}
