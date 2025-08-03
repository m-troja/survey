package com.mir.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mir.survey.entity.Answer;
import com.mir.survey.entity.Question;
import com.mir.survey.entity.Survey;
import com.mir.survey.persistence.AnswerRepo;
import com.mir.survey.persistence.QuestionRepo;
import com.mir.survey.service.SurveyService;

@Controller
public class SurveyController {
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	AnswerRepo answerRepo;
	
	@GetMapping("/survey")
	public String showForm(Model model)
	{
		model.addAttribute("survey", surveyService.getSurvey());
		return "survey";
	}
	
	@PostMapping("/survey")
	public String doPost(Model model, @ModelAttribute("survey") Survey survey) {
		
		// Save answer only when contains text
		survey.getQuestions().stream()
			.flatMap( question -> question.getAnswers().stream()
					.filter( answer -> answer.getText() != null && !answer.getText().isBlank() && !answer.getText().isEmpty())
					.peek( answer -> answer.setQuestion(question)))
			.forEach(answerRepo::save);
		
	    model.addAttribute("feedback", "Odpowiedzi wysłane! Dziękujemy!");
	    return "survey";
	}

}
