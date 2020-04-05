package com.stepanov.documentserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stepanov.documentserver.model.Document;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-document-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-document-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DocumentControllerTest {
    @Autowired
    DocumentController documentController;
    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper om = new ObjectMapper();

    @Test
    void getDocumentsList() throws Exception {
        mockMvc.perform(get("/documents")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getDocumentById() throws Exception {
        mockMvc.perform(get("/documents/1")
                .param("{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{'id':1,'documentName':'Hello!','creationDate':'2019-05-05','content':'first document'}"));
    }

    @Test
    void saveDocument() throws Exception {
        Document newDocument = new Document();
        newDocument.setDocumentName("name");
        mockMvc.perform(post("/documents/save")
                .content(om.writeValueAsString(newDocument))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDocumentById() throws Exception {
        mockMvc.perform(delete("/documents/delete/1")
                .param("{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}