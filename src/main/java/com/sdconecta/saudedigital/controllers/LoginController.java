package com.sdconecta.saudedigital.controllers;

import com.sdconecta.saudedigital.dto.UserDTO;
import com.sdconecta.saudedigital.models.enums.AuthorizationStatus;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.UserRepository;
import com.sdconecta.saudedigital.response.AcessResponse;
import com.sdconecta.saudedigital.services.LoginService;
import com.sdconecta.saudedigital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<AcessResponse> login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByEmail(authentication.getName());
        UserDTO userDto = userService.userToDto(user);
        AcessResponse response = loginService.getAuthStatus(user);
        String auth_status = response.getAuthorization_status();

        if(Objects.equals(auth_status, String.valueOf(AuthorizationStatus.AUTHORIZED))){
            user.setAuthorizationStatus(AuthorizationStatus.AUTHORIZED);
            userService.update(user.getId(), userDto);
            return ResponseEntity.ok().body(response);
        }else{
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

}
