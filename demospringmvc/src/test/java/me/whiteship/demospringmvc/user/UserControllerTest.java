package me.whiteship.demospringmvc.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    @Autowired
    MockMvc mockMvc; // webMvc 테스트를 할 떄 자주 사용

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
            .andExpect(status().isOk()) 
            .andExpect(content().string("hello"));
    }

    @Test
    public void createUser_XML() throws Exception {
        String userJson = "{\"username\":\"keesun\",\"password\":\"1234\"}";
        mockMvc.perform(post("/users/create")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_XML) // acceptHeader
            .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", Is.is("keesun")))
                .andExpect(jsonPath("$.password", Is.is("1234")));
                // .andExpect(xpath("/User/username").string("keesun"))
                // .andExpect(xpath("User/password").string("1234"));
    }
}
