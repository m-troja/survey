package com.mir.survey.entity;

import java.util.List;

public class Survey {
	
	List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions= questions;
	}

	public Survey(List<Question> questions) {
		super();
		this.questions = questions;
	}


}
