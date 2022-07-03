package com.sdconecta.saudedigital.controllers;

import com.sdconecta.saudedigital.dto.UserDTO;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO obj){
        User response = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO obj){
        User response = service.update(id, obj);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String specialty){
        List<User> list;
        if(name != null){
           list = service.findAllByName(name);
        }else if (specialty != null){
            list = service.findAllBySpecialty(specialty);
        }else {
            list = service.findAll();
        }
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }
}
