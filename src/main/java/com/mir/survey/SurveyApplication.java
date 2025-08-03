package com.mir.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mir.survey.entity.Question;
import com.mir.survey.service.SurveyService;

@SpringBootApplication
@PropertySources({
	@PropertySource("classpath:application.properties") 
	})
@EntityScan("com.mir.survey.entity") 

public class SurveyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SurveyApplication.class, args);
		
	}
}
