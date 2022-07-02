package com.sdconecta.saudedigital.controllers;

import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.response.TokenResponse;
import com.sdconecta.saudedigital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    RestTemplate restTemplate = new RestTemplate();


    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        User response = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/token")
    public TokenResponse token(){
        return service.getToken();
    }
    @PostMapping("/login")
    public String login(@RequestBody User obj){
      return service.getAuthStatus(obj).getAuthorization_status();
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User obj){
        User response = service.update(id, obj);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }
}
