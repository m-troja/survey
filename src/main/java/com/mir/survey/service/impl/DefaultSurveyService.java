package com.mir.survey.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mir.survey.entity.Answer;
import com.mir.survey.entity.Question;
import com.mir.survey.entity.Survey;
import com.mir.survey.service.AnswerService;
import com.mir.survey.service.QuestionService;
import com.mir.survey.service.SurveyService;
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

  private final Integer allowedAnswersQty = 10;

	public Survey getSurvey(String jsessionid)
	{
		Survey survey = new Survey(surveyTitle,surveyDescription);
		
		  List<Question> questions = questionService.getQuestions();

		  if (jsessionid == null) {
			  return getSurveyWithNoAnswers();
		  } 
		  else // Get survey with answers already completed by jsessionid
		  { 
			 List<Answer> answers = answerService.getAnswersByJsessionID(jsessionid);

			 if ( !answers.isEmpty()) {

				 questions.forEach(q ->
				    q.setAnswers(
				        answers.stream()
				            .filter(a -> a.getQuestion().getId() == q.getId())
				            .peek(a -> a.setQuestion(q))
                            .peek(a -> System.out.println("Load: " + a + " " + q))
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
	
	public Survey getSurveyWithNoAnswers()  {
		
		  List<Question> questions = questionService.getQuestions();

		 questions.forEach(q -> {
		        List<Answer> answers = IntStream.range(0, 3)
		            .mapToObj(i -> new Answer()) 
		            .peek(a -> a.setQuestion(q)) 
		            .collect(Collectors.toList());

		        q.setAnswers(answers);
		    });
		 
		 return new Survey(surveyTitle, surveyDescription, questions);
	}
	public void saveSurvey(Survey survey, String jsessionid)
	{
		survey.getQuestions().stream()
		.flatMap( question -> question.getAnswers().stream()
				.filter( answer -> answer.getText() != null && !answer.getText().isBlank() && !answer.getText().isEmpty())
				.peek( answer -> answer.setQuestion(question))
				.peek(answer -> answer.setJsessionid(jsessionid))
				.peek(a -> System.out.println("save:" + a)))
		.forEach(answerService::saveAnswer);
	}

	 public boolean validateSurvey(Survey survey)
	 {
		 boolean checkLength = checkAnswersLength(survey);
         boolean checkQtyOfAnswers = checkQtyOfAnswers(survey);
        System.out.println("validateSurvey "  + (checkLength && checkQtyOfAnswers) );
         return checkLength && checkQtyOfAnswers;
	 }

     private boolean checkAnswersLength(Survey survey)
     {
         for ( Question q : survey.getQuestions()) {
             for (Answer a : q.getAnswers()) {
                 if (a.getText().length() > 20) {
                     System.out.println("checkAnswersLength false, length of answers: " + a.getText().length() );
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
                System.out.println("checkQtyOfAnswers false, size of answers: " + q.getAnswers().size() + ", for " + q);
                return false;
            }
        }
        return true;
    }

}
