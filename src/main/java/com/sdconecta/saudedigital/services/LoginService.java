package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.response.AcessResponse;
import com.sdconecta.saudedigital.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private Environment environment;

    RestTemplate restTemplate = new RestTemplate();

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
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
