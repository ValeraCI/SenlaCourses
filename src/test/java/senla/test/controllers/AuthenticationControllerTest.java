package senla.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import senla.configuration.Application;
import senla.dto.AuthRequest;
import senla.util.JwtUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Application.class })
@WebAppConfiguration()
public class AuthenticationControllerTest {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtUtil jwtUtil;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void loginTest() throws Exception {
        String json = objectMapper.writeValueAsString(
                new AuthRequest("cidikvalera@gmail.com", "411496"));

        MvcResult result = mockMvc.perform(get("/authenticate/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String token = result.getResponse()
                .getContentAsString()
                .split(":")[1]
                .replace('\"', ' ')
                .replace('}', ' ')
                .trim();

        System.out.println(token);

        Assert.assertTrue(jwtUtil.validate(token));
        Assert.assertEquals(jwtUtil.getEmail(token), "cidikvalera@gmail.com");
    }
}
