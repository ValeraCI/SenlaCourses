package senla.test.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;;
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
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.UpdateAccountDto;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Application.class })
@WebAppConfiguration()
public class AccountControllerTest {
    @Autowired
    WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void findAllTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AccountMainDataDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AccountMainDataDto>>(){});

        Assert.assertEquals(list.get(0).getNickname(), "Valerix");
        Assert.assertEquals(list.get(0).getId().longValue(), 1);
    }

    @Test
    public void findByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts/{id}", 1))
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
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new AccountDataDto("nickname", "email", "password"))))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        result = mockMvc.perform(get("/accounts/{id}", result.getResponse().getContentAsString()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AccountMainDataDto account =
                objectMapper.readValue(result.getResponse().getContentAsString(), AccountMainDataDto.class);

        Assert.assertEquals(account.getNickname(), "nickname");
    }

    @Test
    public void removeByIdTest() throws Exception {
        mockMvc.perform(delete("/accounts/{id}", 10))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult result =  mockMvc.perform(get("/accounts/{id}", 10))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    public void updateDataTest() throws Exception {
        UpdateAccountDto accountDto = new UpdateAccountDto("TestNick", 3L, "testPass");

        mockMvc.perform(patch("/accounts/{id}", 9)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result =  mockMvc.perform(get("/accounts/{id}", 9))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AccountMainDataDto account =
                objectMapper.readValue(result.getResponse().getContentAsString(), AccountMainDataDto.class);

        Assert.assertEquals(account.getNickname(), "TestNick");
    }

    @Test
    public void addRemoveSavedAlbum() throws Exception {
        mockMvc.perform(post("/accounts/{accountId}/{albumId}", 1, 9))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(delete("/accounts/{accountId}/{albumId}", 1, 9))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addSavedAlbumExceptionTest() throws Exception {
        mockMvc.perform(post("/accounts//{accountId}/albums/{albumId}", 1, 9))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(post("/accounts/{accountId}/{albumId}", 1, 9))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    public void removeSavedAlbumExceptionTest() throws Exception {
        mockMvc.perform(delete("/accounts/{accountId}/albums/{albumId}", 1, 9))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(delete("/accounts/{accountId}/{albumId}", 1, 9))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(404, result.getResponse().getStatus());
    }
}