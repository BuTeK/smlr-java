package by.home.butek.smlr.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.home.butek.smlr.AbstractIntegrationTest;
import by.home.butek.smlr.controllers.RedirectController;
import by.home.butek.smlr.service.KeyMapperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

class RedirectControllerTest extends AbstractIntegrationTest {

    private static final String PATH = "aAbBcCdD";
    private static final String BAD_PATH = "ololo";
    private static final int NOT_FOUND = 404;
    private static final Integer REDIRECT_STATUS = 302;
    private static final String HEADER_NAME ="Location";
    private static final String HEADER_VALUE ="http://www.eveonline.com";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private KeyMapperService service;

    @Autowired
    @InjectMocks
    private RedirectController controller;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Mockito.when(service.getLink(PATH)).thenReturn(HEADER_VALUE);
        Mockito.when(service.getLink(BAD_PATH)).thenReturn(null);
    }

    @Test
    void controllerMustRedirectUsWhenRequestIsSuccessful() throws Exception {
        mockMvc.perform(get("/" + PATH))
                .andExpect(status().is(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE));
    }

    @Test
    void controllerMustReturn404ifBadKey() throws Exception {
        mockMvc.perform(get("/" + BAD_PATH))
                .andExpect(status().is(NOT_FOUND));
    }

    @Test
    void homeWorkFile() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

}
