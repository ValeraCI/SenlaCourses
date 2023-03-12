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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import senla.configuration.WebMvcConfig;
import senla.dto.AuthRequest;
import senla.dto.album.AlbumCreateUpdateDataDto;
import senla.dto.album.AlbumInfoDto;
import senla.security.filters.JwtFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
public class AlbumControllerTest {
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
        MvcResult result = mockMvc.perform(get("/albums")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AlbumInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AlbumInfoDto>>() {
                        });

        assertEquals(list.get(0).getTitle(), "?");
        assertEquals(list.get(0).getId(), 1);
    }

    @Test
    public void findByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/{id}", 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AlbumInfoDto album =
                objectMapper.readValue(result.getResponse().getContentAsString(), AlbumInfoDto.class);

        assertEquals(album.getTitle(), "?");
        assertEquals(album.getId(), 1);
    }

    @Test
    public void findByTitleTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/search/{title}", "LAST ONE")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AlbumInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AlbumInfoDto>>() {
                        });

        assertEquals(list.get(0).getTitle(), "LAST ONE");
        assertEquals(list.get(0).getId(), 2);
    }

    @Test
    public void saveTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/albums")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new AlbumCreateUpdateDataDto("TestAlbum", 1L))))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        result = mockMvc.perform(get("/albums/{id}", result.getResponse().getContentAsString())
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AlbumInfoDto album =
                objectMapper.readValue(result.getResponse().getContentAsString(), AlbumInfoDto.class);

        assertEquals(album.getTitle(), "TestAlbum");
    }

    @Test
    public void removeByIdTest() throws Exception {
        mockMvc.perform(delete("/albums/{id}", 4)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult result = mockMvc.perform(get("/albums/{id}", 4)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void findSavedAlbumsFromAccountIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/savedAlbums/{id}", 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AlbumInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AlbumInfoDto>>() {
                        });

        assertEquals(list.get(0).getTitle(), "?");
        assertEquals(list.get(0).getId(), 1);
    }

    @Test
    public void findCreatedAlbumsFromAccountIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/createdAlbums/{id}", 7)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AlbumInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AlbumInfoDto>>() {
                        });

        assertEquals(list.get(0).getTitle(), "?");
        assertEquals(list.get(0).getId(), 1);
    }

    @Test
    public void addRemoveSavedAlbum() throws Exception {
        mockMvc.perform(post("/albums/{albumId}/songs/{songId}", 1, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(delete("/albums/{albumId}/songs/{songId}", 1, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addSavedAlbumExceptionTest() throws Exception {
        mockMvc.perform(post("/albums/{albumId}/{songId}", 1, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(post("/albums/{albumId}/{songId}", 1, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertEquals(result.getResponse().getStatus(), 404);
    }

    @Test
    public void removeSavedAlbumExceptionTest() throws Exception {
        mockMvc.perform(delete("/albums/{albumId}/{songId}", 1, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print());

        MvcResult result = mockMvc.perform(delete("/albums/{albumId}/{songId}", 1, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertEquals(result.getResponse().getStatus(), 404);
    }

    @Test
    @GetMapping("/recommendations")
    public void findRecommendedFor() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/recommendations")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertEquals(result.getResponse().getStatus(), 200);
    }
}
