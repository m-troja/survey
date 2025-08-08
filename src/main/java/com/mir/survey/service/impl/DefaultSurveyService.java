package com.mir.survey.service.impl;

import com.mir.survey.entity.Answer;
import com.mir.survey.entity.Question;
import com.mir.survey.entity.Survey;
import com.mir.survey.service.AnswerService;
import com.mir.survey.service.QuestionService;
import com.mir.survey.service.SurveyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Slf4j
@Service
public class DefaultSurveyService implements SurveyService {

    @Value("${survey.title}")
    private String surveyTitle;

    @Value("${survey.description}")
    private String surveyDescription;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Value("${answer.max.length}")
    private int answerMaxLength;

    private final Integer allowedAnswersQty = 10;


    public Survey getSurvey(String jsessionid)
	{

		  List<Question> questions = questionService.getQuestions();

          Survey survey = new Survey(surveyTitle,surveyDescription, questions);

		  if (jsessionid == null) {
              log.debug("getSurvey: jsessionid == null, return getSurveyWithNoAnswers()");

              return getSurveyWithNoAnswers();
		  } 
		  else // Get survey with answers already completed by jsessionid
		  { 
			 List<Answer> answers = answerService.getAnswersByJsessionID(jsessionid);
              log.debug("getSurvey: jsessionid == {}" , jsessionid);
			 if ( !answers.isEmpty()) {
				 
				 questions.forEach(q ->
				    q.setAnswers(
				        answers.stream()
				            .filter(a -> a.getQuestion().getId() == q.getId())
				            .peek(a -> a.setQuestion(q))
                            .peek(a -> log.debug("Load {} into {}" , a , q))
                                .collect(Collectors.toList())
				    )
				);
				 survey.setQuestions(questions);
                 log.debug("setQuestions: {}" , questions);
				 return survey;
			 }
			 else { // List of answers by jsessionid is empty
                 log.debug("List of answers by jsessionid is empty, return getSurveyWithNoAnswers()");
				 return getSurveyWithNoAnswers();
			 }
		  }
	}
	
	public Survey getSurveyWithNoAnswers()  {
		
		 List<Question> questions = questionService.getQuestions();
         log.debug("getSurveyWithNoAnswers: getQuestions(): {}" , questions);
         log.debug("Setting answers into questions");
		 questions.forEach(q -> {
		        List<Answer> answers = IntStream.range(0, 3)
		            .mapToObj(i -> new Answer()) 
		            .peek(a -> a.setQuestion(q)) 
		            .peek(a ->  log.debug("Set {} into {}" , q , a ))
		            .collect(Collectors.toList());

		        q.setAnswers(answers);
		    });
		 
		 return new Survey(surveyTitle, surveyDescription, questions);
	}
	public void saveSurvey(Survey survey, String jsessionid)
	{
        log.debug("inside saveSurvey, building answer objects");
		survey.getQuestions().stream()
		.flatMap( question -> question.getAnswers().stream()
				.filter( answer -> answer.getText() != null && !answer.getText().isBlank() && !answer.getText().isEmpty())
				.peek( a -> a.setQuestion(question))
				.peek( a -> log.debug("Set jsessionid = {} for {}", jsessionid, a))
				.peek(a -> a.setJsessionid(jsessionid))
				.peek(a -> log.debug("Save {} into {}" , a , question)))
		.forEach(answerService::saveAnswer);
	}

	 public boolean validateSurvey(Survey survey)
	 {
		 boolean checkLength = checkAnswersLength(survey);
         boolean checkQtyOfAnswers = checkQtyOfAnswers(survey);
         log.debug("validateSurvey {}" ,(checkLength && checkQtyOfAnswers) );
         return checkLength && checkQtyOfAnswers;
	 }

     private boolean checkAnswersLength(Survey survey)
     {
         for ( Question q : survey.getQuestions()) {
             for (Answer a : q.getAnswers()) {
                 if (a.getText().length() > answerMaxLength) {
                     log.debug("checkAnswersLength false, length of answers: {}" , a.getText().length() );
                     return false;
                 }
             }
         }
         return true;
     }
    private boolean checkQtyOfAnswers(Survey survey)
    {
        for ( Question q : survey.getQuestions()) {
            if (q.getAnswers().size() > allowedAnswersQty) {
                log.debug("checkQtyOfAnswers false, size of answers: {} for {}" , q.getAnswers().size() , q);
                return false;
            }
        }
        return true;
    }

}
