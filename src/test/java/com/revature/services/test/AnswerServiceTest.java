package com.revature.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.entities.Answer;
import com.revature.repositories.AnswerRepository;
import com.revature.services.AnswerService;

@RunWith(SpringRunner.class)
@WebMvcTest(AnswerService.class)
public class AnswerServiceTest {
	
	@MockBean
	AnswerRepository answerRepository;
	
	@Autowired
	AnswerService answerService;

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
	

}
