package com.mir.survey.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mir.survey.entity.Answer;

@Repository
public interface AnswerRepo extends CrudRepository<Answer, Integer> {
	
	List<Answer> findAll();
	
	List<Answer> findByjsessionid(String jsessionid);

}
