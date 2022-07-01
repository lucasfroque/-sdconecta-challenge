package com.sdconecta.saudedigital.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.services.CrmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CrmController.class)
public class CrmControllerTest {

    public static final String CRM_NUMBER = "1234";
    public static final String UF = "SP";
    public static final String SPECIALTY = "RADIOLOGIA E DIAGNÓSTICO POR IMAGEM";
    Crm crm;

    public static final Integer ID = 1;
    public static final String EMAIL = "joao@email.com";
    public static final String NAME = "João";
    public static final String SURNAME = "Da Silva";
    public static final String MOBILE_PHONE = "11991234567";
    User user;

    @MockBean
    private CrmService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        start();
    }

    @Test
    void whenCreateShouldReturnCreated() throws Exception {
        when(service.create(any())).thenReturn(crm);

        mockMvc.perform(post("/api/v1/crms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(crm)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenUpdateShouldReturnOk() throws Exception {
        when(service.create(any())).thenReturn(crm);

        mockMvc.perform(put("/api/v1/crms/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(crm)))
                .andExpect(status().isOk());
    }

    @Test
    void whenFindAllShouldReturnListOfCrms() throws Exception {
        when(service.findAll()).thenReturn(List.of(crm, crm));
        mockMvc.perform(get("/api/v1/crms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"))
                .andExpect(jsonPath("[0].id").value(ID))
                .andExpect(jsonPath("[0].crm").value(CRM_NUMBER))
                .andExpect(jsonPath("[0].uf").value(UF))
                .andExpect(jsonPath("[0].specialty").value(SPECIALTY));
    }

    @Test
    void whenFindByIdShouldReturnUser() throws Exception {
        when(service.findById(anyInt())).thenReturn(crm);

        mockMvc.perform(get("/api/v1/crms/{id}", ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(crm)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(ID))
                .andExpect(jsonPath("crm").value(CRM_NUMBER))
                .andExpect(jsonPath("uf").value(UF))
                .andExpect(jsonPath("specialty").value(SPECIALTY));
    }

    @Test
    void whenDeleteShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/crms/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }

    private void start(){
        user = new User(ID, EMAIL, NAME, SURNAME, new ArrayList<>(), MOBILE_PHONE);
        crm = new Crm(ID, CRM_NUMBER, UF, SPECIALTY, user);
    }
}
