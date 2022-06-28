package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    public static final Integer ID = 1;
    public static final String EMAIL = "joao@email.com";
    public static final String NAME = "João";
    public static final String SURNAME = "Da Silva";
    private static final Set<Crm> CRM = new HashSet<>();
    public static final String MOBILE_PHONE = "11991234567";
    User user;
    Optional<User> optionalUser;

    @Mock
    private UserRepository repository;
    @InjectMocks
    UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        start();
    }

    @Test
    void whenCreateShouldSaveUser(){
        when(repository.save(user)).thenReturn(user);
        User response = service.create(user);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(CRM, response.getCrm());
        assertEquals(MOBILE_PHONE, response.getMobilePhone());
    }

    @Test
    void whenUpdateShouldUpdateUser(){
        when(repository.findById(anyInt())).thenReturn(Optional.of(user));

        User updatedUser = new User(ID, "joao2@email.com", NAME, SURNAME, CRM, "21991234565");

        User response = service.update(ID, updatedUser);

        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(CRM, response.getCrm());

        assertNotEquals(EMAIL, response.getEmail());
        assertNotEquals(MOBILE_PHONE, response.getMobilePhone());
    }

    @Test
    void whenFindAllShouldReturnListOfUsers(){
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(SURNAME, response.get(0).getSurname());
        assertEquals(CRM, response.get(0).getCrm());
        assertEquals(MOBILE_PHONE, response.get(0).getMobilePhone());
    }

    @Test
    void whenDeleteShouldDeleteUser() {
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenFindByIdShouldReturnUser() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(CRM, response.getCrm());
        assertEquals(MOBILE_PHONE, response.getMobilePhone());
    }

    private void start(){
        user = new User(ID, EMAIL, NAME, SURNAME, CRM, MOBILE_PHONE);
        optionalUser = Optional.of(new User(ID, EMAIL, NAME, SURNAME, CRM, MOBILE_PHONE));

    }

}
