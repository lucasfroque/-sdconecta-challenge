package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.CrmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrmServiceTest {

    public static final Integer ID = 1;
    public static final String CRM_NUMBER = "1234";
    public static final String UF = "SP";
    public static final String SPECIALTY = "RADIOLOGIA E DIAGNÓSTICO POR IMAGEM";

    Crm crm;
    Optional<Crm> optionalCrm;

    @Mock
    private CrmRepository repository;
    @InjectMocks
    CrmService service;

    @BeforeEach
    void setUp() {
        start();
    }

    @Test
    void whenCreateShouldSaveCrm(){
        when(repository.save(crm)).thenReturn(crm);
        Crm response = service.create(crm);

        assertNotNull(response);
        assertEquals(Crm.class, crm.getClass());
        assertEquals(ID, response.getId());
        assertEquals(CRM_NUMBER, response.getCrm());
        assertEquals(UF, response.getUf());
        assertEquals(SPECIALTY, response.getSpecialty());
    }

    @Test
    void whenUpdateShouldUpdateCrm(){
        when(repository.findById(anyInt())).thenReturn(Optional.of(crm));

        Crm updatedCrm = new Crm(ID, CRM_NUMBER, "RJ", SPECIALTY, new User());

        Crm response = service.update(ID, updatedCrm);

        assertNotNull(response);
        assertEquals(Crm.class, crm.getClass());
        assertEquals(ID, response.getId());
        assertEquals(CRM_NUMBER, response.getCrm());
        assertEquals(SPECIALTY, response.getSpecialty());

        assertNotEquals(UF, response.getUf());
    }

    @Test
    void whenFindAllShouldReturnListOfCrms(){
        when(repository.findAll()).thenReturn(List.of(crm));

        List<Crm> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Crm.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(UF, response.get(0).getUf());
        assertEquals(CRM_NUMBER, response.get(0).getCrm());
    }


    @Test
    void whenDeleteShouldDeleteCrm() {
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenFindByIdShouldReturnCrm() {
        when(repository.findById(anyInt())).thenReturn(optionalCrm);
        Crm response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Crm.class, crm.getClass());
        assertEquals(ID, response.getId());
        assertEquals(CRM_NUMBER, response.getCrm());
        assertEquals(UF, response.getUf());
        assertEquals(SPECIALTY, response.getSpecialty());
    }


    private void start(){
        crm = new Crm(ID, CRM_NUMBER, UF, SPECIALTY, new User());
        optionalCrm = Optional.of(new Crm(ID, CRM_NUMBER, UF, SPECIALTY, new User()));
    }

}
