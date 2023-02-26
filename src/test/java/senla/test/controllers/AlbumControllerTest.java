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
import senla.dto.album.AlbumInfoDto;
import senla.dto.album.CreateAlbumDto;
import senla.security.filters.JwtFilter;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WebMvcConfig.class })
@WebAppConfiguration()
public class AlbumControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private String token;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
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
                        new TypeReference<List<AlbumInfoDto>>(){});

        Assert.assertEquals(list.get(0).getTitle(), "?");
        Assert.assertEquals(list.get(0).getId(), 1);
    }

    @Test
    public void findByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/{id}", 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AlbumInfoDto album =
                objectMapper.readValue(result.getResponse().getContentAsString(), AlbumInfoDto.class);

        Assert.assertEquals(album.getTitle(), "?");
        Assert.assertEquals(album.getId(), 1);
    }

    @Test
    public void findByTitleTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/search/{title}", "LAST ONE")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AlbumInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AlbumInfoDto>>(){});

        Assert.assertEquals(list.get(0).getTitle(), "LAST ONE");
        Assert.assertEquals(list.get(0).getId(), 2);
    }

    @Test
    public void saveTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/albums")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CreateAlbumDto("TestAlbum", 1L))))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        result = mockMvc.perform(get("/albums/{id}", result.getResponse().getContentAsString())
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AlbumInfoDto album =
                objectMapper.readValue(result.getResponse().getContentAsString(), AlbumInfoDto.class);

        Assert.assertEquals(album.getTitle(), "TestAlbum");
    }

    @Test
    public void removeByIdTest() throws Exception {
        mockMvc.perform(delete("/albums/{id}", 4)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


        MvcResult result =  mockMvc.perform(get("/albums/{id}", 4)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    public void findSavedAlbumsFromAccountIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/savedAlbums/{id}", 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AlbumInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AlbumInfoDto>>(){});

        Assert.assertEquals(list.get(0).getTitle(), "?");
        Assert.assertEquals(list.get(0).getId(), 1);
    }

    @Test
    public void findCreatedAlbumsFromAccountIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/albums/createdAlbums/{id}", 7)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        List<AlbumInfoDto> list =
                objectMapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<List<AlbumInfoDto>>(){});

        Assert.assertEquals(list.get(0).getTitle(), "?");
        Assert.assertEquals(list.get(0).getId(), 1);
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

        MvcResult result =  mockMvc.perform(post("/albums/{albumId}/{songId}", 1, 1)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 404);
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

        Assert.assertEquals(result.getResponse().getStatus(), 404);
    }
}
