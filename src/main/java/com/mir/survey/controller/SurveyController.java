package com.mir.survey.controller;

import com.mir.survey.entity.Survey;
import com.mir.survey.service.SurveyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SurveyController {


    @Value("${answer.max.length}")
    public int answerMaxLength;

    @Value("${survey.message.sent.success}")
    private String successMessage;

    @Value("${survey.message.sent.error}")
    private String errorMessage;


	SurveyService surveyService;

	@GetMapping("/survey")
	public String showForm(Model model, HttpServletRequest request, 
			@CookieValue(value = "JSESSIONID", defaultValue = "null") String jsessionid) {

		model.addAttribute("message", null);
		model.addAttribute("message", null);
		model.addAttribute("answerMaxLength", answerMaxLength);
		model.addAttribute("survey", surveyService.getSurvey(jsessionid));
		return "survey";
	}
	
	@PostMapping("/survey")
	public String doPost(Model model, @ModelAttribute("survey") Survey survey,
			@CookieValue(value = "JSESSIONID", defaultValue = "null") String jsessionid)
	{
		if ( surveyService.validateSurvey(survey))
		{
			surveyService.saveSurvey(survey, jsessionid);
		    model.addAttribute("successMessage", successMessage);
		}
		else {
			model.addAttribute("errorMessage", errorMessage);
		}
        model.addAttribute("answerMaxLength", answerMaxLength);
        model.addAttribute("survey", surveyService.getSurvey(jsessionid));
	    return "survey";
	}

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
}
