package com.sdconecta.saudedigital.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.models.enums.AuthorizationStatus;
import com.sdconecta.saudedigital.services.CrmService;
import com.sdconecta.saudedigital.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
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

    public static final Long ID = 1L;
    public static final String EMAIL = "joao@email.com";
    public static final String NAME = "João";
    private static final String PASSWORD = "12345";
    public static final String SURNAME = "Da Silva";
    public static final String MOBILE_PHONE = "11991234567";
    private static final AuthorizationStatus AUTHORIZATION_STATUS = AuthorizationStatus.WAITING_FOR_AUTHORIZATION;
    private static final String ROLE = "USER";

    User user;

    @MockBean
    private CrmService crmService;
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        start();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenCreateShouldReturnCreated() throws Exception {
        when(crmService.create(any(), any())).thenReturn(crm);
        when(userService.findById(any())).thenReturn(user);

        mockMvc.perform(post("/api/v1/users/{userId}/crms", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(crm)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void whenUpdateShouldReturnOk() throws Exception {
        when(crmService.create(any(), any())).thenReturn(crm);

        mockMvc.perform(put("/api/v1/crms/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(crm)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void whenFindAllShouldReturnListOfCrms() throws Exception {
        when(crmService.findAll()).thenReturn(List.of(crm, crm));
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
    @WithMockUser(roles = "USER")
    void whenFindByIdShouldReturnUser() throws Exception {
        when(crmService.findById(anyLong())).thenReturn(crm);

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
    @WithMockUser(roles = "ADMIN")
    void whenDeleteShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/crms/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNoContent());
    }

    private void start(){
        user = new User(ID, EMAIL, PASSWORD, NAME, SURNAME, new ArrayList<>(), MOBILE_PHONE, ROLE, AUTHORIZATION_STATUS);
        crm = new Crm(ID, CRM_NUMBER, UF, SPECIALTY, user);
    }
}
