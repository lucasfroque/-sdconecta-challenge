package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.UserRepository;
import com.sdconecta.saudedigital.response.AcessResponse;
import com.sdconecta.saudedigital.response.TokenResponse;
import com.sdconecta.saudedigital.services.exceptions.DatabaseException;
import com.sdconecta.saudedigital.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private Environment environment;

    RestTemplate restTemplate = new RestTemplate();

    public User create(User user){
        try {
            repository.save(user);
            return user;
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Email already exist");
        }
    }

    public User update(Integer id, User newUser){
        try {
            User user = repository.findById(id).get();
            updateData(user, newUser);

            repository.save(user);
            return user;
        }
        catch(NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private void updateData(User user, User newUser) {
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setCrms(newUser.getCrms());
        user.setMobilePhone(newUser.getMobilePhone());
    }

    public TokenResponse getToken(){
        String url = environment.getProperty("env.tokenUrl");
        String client_id = environment.getProperty("env.clientId");
        String client_secret = environment.getProperty("env.clientSecret");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("scope", "partner" );
        requestBody.add("client_id", client_id);
        requestBody.add("client_secret", client_secret);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TokenResponse> response = restTemplate.postForEntity(url, requestEntity, TokenResponse.class);
        return response.getBody();
    }
    public AcessResponse getAuthStatus(User obj){
        String url = environment.getProperty("env.generateTokenUrl");
        String acess_token = getToken().getAccess_token();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + acess_token);

        HttpEntity<?> requestEntity = new HttpEntity<>(obj, headers);

        ResponseEntity<AcessResponse> response = restTemplate.postForEntity(url, requestEntity, AcessResponse.class);
        return response.getBody();
    }

}
