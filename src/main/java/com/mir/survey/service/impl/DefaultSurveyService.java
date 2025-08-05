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

	@Override
	public Survey getSurvey(String jsessionid)
	{
		Survey survey = new Survey();

		  List<Question> questions = questionService.getQuestions();

		  if (jsessionid == null) {
			  return getSurveyWithNoAnswers();
		  }
		  else // Get survey with answers already completed by jsessionid
		  {
			 List<Answer> answers = answerService.getAnswersByJsessionID(jsessionid);

			 if ( !answers.isEmpty() && answers.size() != 0 ) {

				 questions.forEach(q ->
				    q.setAnswers(
				        answers.stream()
				            .filter(a -> a.getQuestion().getId() == q.getId())
				            .peek(a -> a.setQuestion(q))
				            .collect(Collectors.toList())
				    )
				);
				 survey.setQuestions(questions);
				 return survey;
			 }
			 else { // List of answers by jsessionid is empty
				 return getSurveyWithNoAnswers();
			 }
		  }
	}

	@Override
	public Survey getSurveyWithNoAnswers()  {

		  List<Question> questions = questionService.getQuestions();

		 questions.forEach(q -> {
		        List<Answer> answers = IntStream.range(0, 3)
		            .mapToObj(i -> new Answer())
		            .peek(a -> a.setQuestion(q))
		            .collect(Collectors.toList());

		        q.setAnswers(answers);
		    });

		 return new Survey(questions);
	}
	@Override
	public void saveSurvey(Survey survey, String jsessionid)
	{
		survey.getQuestions().stream()
		.flatMap( question -> question.getAnswers().stream()
				.filter( answer -> answer.getText() != null && !answer.getText().isBlank() && !answer.getText().isEmpty())
				.peek( answer -> answer.setQuestion(question))
				.peek(answer -> answer.setJsessionid(jsessionid)))
		.forEach(answerService::saveAnswer);
	}

	 @Override
	public boolean validateSurvey(Survey survey, String jsessionid)
	 {
		 for ( Question q : survey.getQuestions()) {
			 for (Answer a : q.getAnswers()) {
				 if (a.getText().length() > 20) {
					 return false;
				 }
			 }
		 }
		 return true;
	 }

}
