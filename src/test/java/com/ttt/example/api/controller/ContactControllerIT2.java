package com.ttt.example.api.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import com.ttt.example.api.model.Client;
import com.ttt.example.api.model.Contact;
import com.ttt.example.api.repository.ClientRepository;
import com.ttt.example.api.repository.ContactRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:application-it.properties")
public class ContactControllerIT2
{
    private MockMvc mvc;

    @Autowired
    private ContactController contactController;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Before
    public void setup() throws Exception {
        mvc = standaloneSetup(contactController).build();
    }

    @Test
    @WithMockUser(username = "IT_USER", authorities = {"READ"})
    public void getContactsForClientTest() throws Exception {
        Client testClient = Client.builder()
                .name("Client 1")
                .notes("some notes")
                .build();
        testClient = clientRepository.saveAndFlush(testClient);

        Contact testContact = Contact.builder()
                .client(testClient)
                .name("Test Client 1")
                .build();
        contactRepository.saveAndFlush(testContact);

        testContact = Contact.builder()
                .client(testClient)
                .name("Test Client 2")
                .build();
        contactRepository.saveAndFlush(testContact);

        testContact = Contact.builder()
                .client(testClient)
                .name("Test Client 3")
                .build();
        contactRepository.saveAndFlush(testContact);

        mvc.perform(get(String.format("/api/v1/clients/%d/contacts", testClient.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(3))))
                .andExpect(jsonPath("$[0].name", is("Test Client 1")))
                .andExpect(jsonPath("$[2].name", is("Test Client 3")));
    }
}
