package by.home.butek.smlr.controller;

import by.home.butek.smlr.SmlrApplication;
import by.home.butek.smlr.controllers.AddController;
import by.home.butek.smlr.service.KeyMapperService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:repositories-test.properties")
@SpringBootTest(classes = SmlrApplication.class)
@WebAppConfiguration
class AddControllerTest {

    private static final String LINK = "link";
    private static final String KEY = "key";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private KeyMapperService service;

    @Autowired
    @InjectMocks
    private AddController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        Mockito.when(service.add(LINK)).thenReturn(KEY);
    }

    @Test
    void whenUserAddLinkHeTakesAKey() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new AddController.AddRequest(LINK))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.key", equalTo(KEY)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.link", equalTo(LINK)));
    }

    @Test
    void whenUserLinkAddLinkByFormHeTakesAWebPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/addhtml")
                .param("link", LINK)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(KEY)))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(LINK)));
    }

}
