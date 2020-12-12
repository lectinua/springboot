package com.example.springbootmvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

	// 요청 "/hello"
	// 응답
	// - 모델 name: keesun
	// - 뷰 이름: hello
    
    @Autowired
	MockMvc mockMvc;

	@Test
	public void hello() throws Exception {

		// 요청 "/hello"
		// 응답
		// - 뷰 이름: hello
		// - 모델 name: keesun

		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				// .andDo(print())
				.andExpect(view().name("hello"))
				.andExpect(model().attribute("name", Is.is("keesun")))
				.andExpect(content().string(containsString("hello page")))
				.andExpect(content().string(containsString("keesun")));
	}

	// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/result/MockMvcResultMatchers.html
	// print()는 document에 없음
	// 걍 실패하면 thymeleaf가 처리하고 난 뒤의 html 전문 뜸
}
