package senla.test.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import senla.dto.song.SongCreateDto;
import senla.dto.song.SongInfoDto;
import senla.security.filters.JwtFilter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class SongControllerTest {
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
    public void findByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/{id}", 2)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        SongInfoDto song =
                objectMapper.readValue(result.getResponse().getContentAsString(), SongInfoDto.class);

        assertEquals(song.getId(), 2);
        assertEquals(song.getTitle(), "TEASER");
    }

    @Test
    public void saveTest() throws Exception {
        SongCreateDto songCreateDto = new SongCreateDto("TestSong", 1L,
                new ArrayList<>(List.of(6L, 7L, 8L)), "XXXTENTCION", 1L);

        MvcResult result = mockMvc.perform(post("/songs")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(songCreateDto)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        result = mockMvc.perform(get("/songs/{id}", result.getResponse().getContentAsString())
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        SongInfoDto song =
                objectMapper.readValue(result.getResponse().getContentAsString(), SongInfoDto.class);

        assertEquals(song.getTitle(), "TestSong");
    }

    @Test
    public void removeByIdTest() throws Exception {
        mockMvc.perform(delete("/songs/{id}", 5)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult result = mockMvc.perform(get("/songs/{id}", 5)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    public void findByTitleParameterTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/search/{parameter}?findBy=BY_TITLE", "NUMB")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<SongInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<SongInfoDto>>() {
                        });

        assertEquals("NUMB", list.get(0).getTitle());
        assertEquals(8, list.get(0).getId());
    }

    @Test
    public void findByAlbumIdParameterTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/search/{parameter}?findBy=BY_ALBUM_ID", 2)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<SongInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<SongInfoDto>>() {
                        });

        assertEquals("TEASER", list.get(0).getTitle());
        assertEquals(2, list.get(0).getId());
    }

    @Test
    public void findByGenreParameterTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/songs/search/{parameter}?findBy=BY_GENRE", "RAP")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<SongInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<SongInfoDto>>() {
                        });

        assertEquals("TEASER", list.get(0).getTitle());
        assertEquals(2, list.get(0).getId());
    }
}