package com.sdconecta.saudedigital.controllers;

import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.services.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/crms")
public class CrmController {

    @Autowired
    private CrmService service;

    @PostMapping
    public ResponseEntity<Crm> insert(@RequestBody Crm obj){
        Crm response = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<Crm> update(@PathVariable Integer id, @RequestBody Crm obj){
        service.update(id, obj);
        return ResponseEntity.ok().body(obj);

    }

    @GetMapping
    public ResponseEntity<List<Crm>> findAll(){
        List<Crm> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Crm> findById(@PathVariable Integer id){
        Crm obj = service.findById(id);
        return ResponseEntity.ok().body(obj);

    }
}
