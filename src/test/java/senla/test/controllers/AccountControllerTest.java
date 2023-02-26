package senla.test.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import senla.configuration.WebMvcConfig;
import senla.dto.AuthRequest;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.UpdateAccountDto;
import senla.security.filters.JwtFilter;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class AccountControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private String token;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .addFilter(jwtFilter)
                .dispatchOptions(true).build();

        String json = objectMapper.writeValueAsString(
                new AuthRequest("cidikvalera@gmail.com", "1111"));

        MvcResult result = mockMvc.perform(get("/authenticate/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        token = "Bearer " + result.getResponse()
                .getContentAsString()
                .split(":")[2]
                .replace('\"', ' ')
                .replace('}', ' ')
                .trim();
    }

    @Test
    public void findAllTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AccountMainDataDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AccountMainDataDto>>() {
                        });

        Assert.assertEquals(list.get(0).getNickname(), "Valerix");
        Assert.assertEquals(list.get(0).getId().longValue(), 1);
    }

    @Test
    public void findByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/{id}", 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AccountMainDataDto account =
                objectMapper.readValue(result.getResponse().getContentAsString(), AccountMainDataDto.class);

        Assert.assertEquals(account.getNickname(), "Valerix");
        Assert.assertEquals(account.getId().longValue(), 1);
    }

    @Test
    public void saveTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/accounts")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new AccountDataDto("nickname", "email", "password"))))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        result = mockMvc.perform(get("/accounts/{id}", result.getResponse().getContentAsString())
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AccountMainDataDto account =
                objectMapper.readValue(result.getResponse().getContentAsString(), AccountMainDataDto.class);

        Assert.assertEquals(account.getNickname(), "nickname");
    }

    @Test
    public void removeByIdTest() throws Exception {
        mockMvc.perform(delete("/accounts/{id}", 10)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult result = mockMvc.perform(get("/accounts/{id}", 10)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    public void updateDataTest() throws Exception {
        UpdateAccountDto accountDto = new UpdateAccountDto("TestNick", 3L, "testPass");

        mockMvc.perform(patch("/accounts/{id}", 9)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(get("/accounts/{id}", 9)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AccountMainDataDto account =
                objectMapper.readValue(result.getResponse().getContentAsString(), AccountMainDataDto.class);

        Assert.assertEquals(account.getNickname(), "TestNick");
    }

    @Test
    public void addRemoveSavedAlbumTest() throws Exception {
        mockMvc.perform(post("/accounts/{accountId}/albums/{albumId}", 10, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(delete("/accounts/{accountId}/albums/{albumId}", 10, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void addSavedAlbumExceptionTest() throws Exception {
        mockMvc.perform(post("/accounts/{accountId}/albums/{albumId}", 10, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(post("/accounts/{accountId}/albums/{albumId}", 10, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    public void removeSavedAlbumExceptionTest() throws Exception {
        mockMvc.perform(delete("/accounts/{accountId}/albums/{albumId}", 10, 2)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(delete("/accounts/{accountId}/albums/{albumId}", 10, 2)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }
}