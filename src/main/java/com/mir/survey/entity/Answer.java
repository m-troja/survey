package com.mir.survey.entity;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id ;
	
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	

}
