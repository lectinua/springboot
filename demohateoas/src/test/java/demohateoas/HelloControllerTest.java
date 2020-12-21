package demohateoas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
	MockMvc mockMvc;

	@Test
	public void hello() throws Exception {
		mockMvc.perform(get("/hello"))
			.andExpect(jsonPath("$.content").value("Hello, World"))
			.andExpect(jsonPath("$._links.self.href").value("http://localhost/hello?name=World"));
	}

	@Test
	public void hello2() throws Exception {
		mockMvc.perform(get("/hello2"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$._links.self").exists());
	}
}

/**
 *   {
 *     "content": "Hello, World",
 *     "_links": {
 *       "self": {
 *         "href": "http://localhost/hello?name=World"
 *       }
 *     }
 *   }
 */
