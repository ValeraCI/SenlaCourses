package senla.test.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
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
import senla.dto.album.AlbumInfoDto;
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Application.class })
@WebAppConfiguration()
public class SongControllerTest {
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
    public void findByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/{id}", 2))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        SongInfoDto song =
                objectMapper.readValue(result.getResponse().getContentAsString(), SongInfoDto.class);

        Assert.assertEquals(song.getId(), 2);
        Assert.assertEquals(song.getTitle(), "TEASER");
    }

    @Test
    public void saveTest() throws Exception {
        SongCreateDto songCreateDto = new SongCreateDto("TestSong", 1L,
                new ArrayList<>(List.of(6L, 7L, 8L)), "XXXTENTCION", 1L);

        MvcResult result = mockMvc.perform(post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songCreateDto)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        result = mockMvc.perform(get("/songs/{id}", result.getResponse().getContentAsString()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        SongInfoDto song =
                objectMapper.readValue(result.getResponse().getContentAsString(), SongInfoDto.class);

        Assert.assertEquals(song.getTitle(), "TestSong");
    }

    @Test
    public void removeByIdTest() throws Exception {
        mockMvc.perform(delete("/songs/{id}", 5))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult result =  mockMvc.perform(get("/songs/{id}", 5))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 404);
    }

    @Test
    public void findByTitleParameterTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/search/{parameter}?findBy=BY_TITLE", "NUMB"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<SongInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<SongInfoDto>>(){});

        Assert.assertEquals(list.get(0).getTitle(), "NUMB");
        Assert.assertEquals(list.get(0).getId(), 8);
    }

    @Test
    public void findByAlbumIdParameterTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/search/{parameter}?findBy=BY_ALBUM_ID", 2))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<SongInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<SongInfoDto>>(){});

        Assert.assertEquals(list.get(0).getTitle(), "TEASER");
        Assert.assertEquals(list.get(0).getId(), 2);
    }

    @Test
    public void findByGenreParameterTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/search/{parameter}?findBy=BY_GENRE", "RAP"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<SongInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<SongInfoDto>>(){});

        Assert.assertEquals(list.get(0).getTitle(), "TEASER");
        Assert.assertEquals(list.get(0).getId(), 2);
    }
}