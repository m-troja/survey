package com.mir.survey.entity;


import java.util.List;

public class Survey {

    String title;

    String description;

	List<Question> questions;

    public Survey(String title, String description, List<Question> questions) {
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public Survey(String title, String description) {
        this.title = title;
        this.description = description;
    }

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

	public Survey() {
		super();
	}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
