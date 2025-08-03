package com.mir.survey.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mir.survey.entity.Question;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer> {
	
	List<Question> findAll();

}
