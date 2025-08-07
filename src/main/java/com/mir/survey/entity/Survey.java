package com.mir.survey.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Survey {

    String title;

    String description;

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
