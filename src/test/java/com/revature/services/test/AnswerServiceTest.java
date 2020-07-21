package com.revature.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.entities.Answer;
import com.revature.repositories.AnswerRepository;
import com.revature.services.AnswerService;
import com.revature.services.RSSService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnswerServiceTest {
	
	@MockBean
	AnswerRepository answerRepository;
	
	@MockBean
	RSSService rssService;
	
	@Autowired
	AnswerService answerService;

	@Autowired
	RSSService rssservice;
	
	/** @author ken */
	@Test
	public void getAllAnswersTest() throws Exception {
		
		Answer answer = new Answer(1, 1, 1, "test content", LocalDate.MIN, LocalDate.MIN);	
		List<Answer> answers = new ArrayList<>();	
		answers.add(answer);	

		Page<Answer> pageResult = new PageImpl<Answer>(answers);	

		when(answerRepository.findAll(Mockito.any(Pageable.class))).thenReturn((pageResult));	

		Page<Answer> result = answerService.getAnswers(PageRequest.of(1, 5));

		assertThat(pageResult).contains(answer);	
		assertEquals( pageResult, result);	
	}
	
	/** @author ken */
	@Test
	public void getAllAnswersByUserIDTest() throws Exception {
		
		Answer answer = new Answer(1, 1, 1, "test content", LocalDate.MIN, LocalDate.MIN);	
		List<Answer> answers = new ArrayList<>();	
		answers.add(answer);	

		Page<Answer> pageResult = new PageImpl<Answer>(answers);	

		when(answerRepository.getAllAnswersByUserId(Mockito.any(Pageable.class), Mockito.anyInt())).thenReturn((pageResult));	

		Page<Answer> result = answerService.getAllAnswersByUserID(PageRequest.of(1, 5), 1);
		assertThat(result).contains(answer);	
	}
	
	/** @author Natasha Poser  */
	@Test
	public void getAnswerByQuestionIdTest() throws Exception {
		
		Answer answer = new Answer(1, 1, 1, "test content", LocalDate.MIN, LocalDate.MIN);	
		List<Answer> answers = new ArrayList<>();	
		answers.add(answer);	

		Page<Answer> pageResult = new PageImpl<Answer>(answers);	

		when(answerRepository.getAnswerByQuestionId(Mockito.any(Pageable.class), Mockito.anyInt())).thenReturn((pageResult));	

		Page<Answer> result = answerService.getAnswerByQuestionId(PageRequest.of(1, 5), 1);
		assertThat(result).contains(answer);	
	}
	
}
