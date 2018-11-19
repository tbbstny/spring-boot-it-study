package com.ttt.example.api.controller;

import static com.ttt.example.utils.TestContactBuilder.NAME;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.ttt.example.utils.TestContactBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-it.properties")
public class ContactControllerIT
{
    private static Logger logger = LoggerFactory.getLogger(ContactControllerIT.class);

    private MockMvc mvc;

    @Autowired
    private ContactController contactController;

    @Autowired
    private TestContactBuilder contactBuilder;

    @Before
    public void setup() throws Exception {
        mvc = standaloneSetup(contactController).build();
    }

    @After
    public void resetDb() {
        contactBuilder.resetDB();
    }

    @Test
    @WithMockUser(username = "IT_USER", authorities = {"READ"})
    public void getContactsForClientTest() throws Exception {
        logger.debug("It Works!");

        contactBuilder.addAll();

        mvc.perform(get(String.format("/api/v1/clients/%d/contacts", 1))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(contactBuilder.dataRowCount()))))
                .andExpect(jsonPath("$[0].name", is(contactBuilder.getTestData(0)[NAME])))
                .andExpect(jsonPath("$[2].name", is(contactBuilder.getTestData(2)[NAME])));
    }
}
