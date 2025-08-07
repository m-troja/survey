
package com.mir.survey.controller;

import com.mir.survey.entity.Survey;
import com.mir.survey.service.SurveyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
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
        log.debug("GET /survey, jsessionid: {}" , jsessionid);

        model.addAttribute("message", null);
        model.addAttribute("message", null);
        model.addAttribute("answerMaxLength", answerMaxLength);
        model.addAttribute("survey", surveyService.getSurvey(jsessionid));
        log.debug("Added attributes: answerMaxLength {}" , answerMaxLength);
        return "survey";
    }

    @PostMapping("/survey")
    public String doPost(Model model, @ModelAttribute("survey") Survey survey,
                         @CookieValue(value = "JSESSIONID", defaultValue = "null") String jsessionid)
    {
        log.debug("POST /survey, jsessionid:  {} , {}: " , jsessionid , survey);

        if ( surveyService.validateSurvey(survey))
        {
            surveyService.saveSurvey(survey, jsessionid);
            log.debug("Send successMessage {}" , successMessage);
            model.addAttribute("successMessage", successMessage);
        }
        else {
            log.debug("Send errorMessage {}" , errorMessage);
            model.addAttribute("errorMessage", errorMessage);
        }
        log.debug("Added atributes: answerMaxLength {}" ,   answerMaxLength);
        model.addAttribute("answerMaxLength" , answerMaxLength);
        model.addAttribute("survey" , surveyService.getSurvey(jsessionid));
        return "survey";
    }

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
}