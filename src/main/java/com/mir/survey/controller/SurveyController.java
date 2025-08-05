package com.mir.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mir.survey.entity.Survey;
import com.mir.survey.service.AnswerService;
import com.mir.survey.service.SurveyService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SurveyController {

	@Autowired
	SurveyService surveyService;

	@Autowired
	AnswerService answerService;

	@GetMapping("/survey")
	public String showForm(Model model, HttpServletRequest request,
			@CookieValue(value = "JSESSIONID", defaultValue = "null") String jsessionid) {

		model.addAttribute("survey", surveyService.getSurvey(jsessionid));
		return "survey";
	}

	@PostMapping("/survey")
	public String doPost(Model model, @ModelAttribute("survey") Survey survey,
			@CookieValue(value = "JSESSIONID", defaultValue = "null") String jsessionid)
	{
		if ( surveyService.validateSurvey(survey))
		{
			surveyService.saveSurvey(survey,jsessionid);
		    model.addAttribute("message", "Odpowiedzi wysłane! Dziękujemy!");
		}
		else {
			model.addAttribute("message", "Błąd - długość odpowiedzi > 20");
		}

	    return "survey";
	}

}
