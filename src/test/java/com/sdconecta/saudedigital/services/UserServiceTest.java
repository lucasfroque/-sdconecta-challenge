package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.dto.UserDTO;
import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.models.enums.AuthorizationStatus;
import com.sdconecta.saudedigital.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    private static final Long ID = 1L;

    private static final String EMAIL = "joao@email.com";
    private static final String PASSWORD = "12345";
    private static final String NAME = "João";
    private static final String SURNAME = "Da Silva";
    private static final List<Crm> CRM = new ArrayList<>();
    private static final String MOBILE_PHONE = "11991234567";
    private static final AuthorizationStatus AUTHORIZATION_STATUS = AuthorizationStatus.WAITING_FOR_AUTHORIZATION;
    private static final String ROLE = "USER";


    public static final String CRM_SPECIALTY = "RADIOLOGIA E DIAGNÓSTICO POR IMAGEM";


    User user;
    UserDTO userDto;
    Optional<User> optionalUser;
    @Mock
    private UserRepository repository;
    @Mock
    private LoginService loginService;
    @InjectMocks
    UserService service;

    @BeforeEach
    void setUp() {
        start();
    }

    @Test
    void whenCreateShouldSaveUser(){
        when(repository.save(any())).thenReturn(user);
        User response = service.create(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(CRM, response.getCrms());
        assertEquals(MOBILE_PHONE, response.getMobilePhone());
        assertEquals(ROLE, response.getRoles());
    }

    @Test
    void whenUpdateShouldUpdateUser(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        UserDTO updatedUser = new UserDTO("joao2@email.com", PASSWORD, NAME, SURNAME, "21991234565", ROLE);

        User response = service.update(ID, updatedUser);


        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(CRM, response.getCrms());
        assertEquals(ROLE, response.getRoles());

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
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(SURNAME, response.get(0).getSurname());
        assertEquals(CRM, response.get(0).getCrms());
        assertEquals(MOBILE_PHONE, response.get(0).getMobilePhone());
        assertEquals(ROLE, response.get(0).getRoles());
    }

    @Test
    void whenFindAllByNameShouldReturnListOfUsersWithName(){
        when(repository.findByNameContainingIgnoreCase(any())).thenReturn(List.of(user));

        List<User> response = service.findAllByName(NAME);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(SURNAME, response.get(0).getSurname());
        assertEquals(CRM, response.get(0).getCrms());
        assertEquals(MOBILE_PHONE, response.get(0).getMobilePhone());
        assertEquals(ROLE, response.get(0).getRoles());
    }

    @Test
    void whenFindAllByNameShouldReturnListOfUsersWithSpecialty(){
        when(repository.findByCrmsSpecialtyContainingIgnoreCase(any())).thenReturn(List.of(user));

        List<User> response = service.findAllBySpecialty(CRM_SPECIALTY);

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(SURNAME, response.get(0).getSurname());
        assertEquals(CRM, response.get(0).getCrms());
        assertEquals(MOBILE_PHONE, response.get(0).getMobilePhone());
        assertEquals(ROLE, response.get(0).getRoles());
    }

    @Test
    void whenDeleteShouldDeleteUser() {
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void whenFindByIdShouldReturnUser() {
        when(repository.findById(anyLong())).thenReturn(optionalUser);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(NAME, response.getName());
        assertEquals(SURNAME, response.getSurname());
        assertEquals(CRM, response.getCrms());
        assertEquals(MOBILE_PHONE, response.getMobilePhone());
        assertEquals(ROLE, response.getRoles());
    }

    private void start(){
        user = new User(ID, EMAIL, PASSWORD, NAME, SURNAME, CRM, MOBILE_PHONE, ROLE, AUTHORIZATION_STATUS);
         userDto = new UserDTO(EMAIL, PASSWORD ,NAME, SURNAME, MOBILE_PHONE, ROLE);
        optionalUser = Optional.of(new User(ID, EMAIL, PASSWORD, NAME, SURNAME, CRM, MOBILE_PHONE, ROLE, AUTHORIZATION_STATUS));
    }

}
