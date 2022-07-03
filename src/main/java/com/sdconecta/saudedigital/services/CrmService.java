package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.repositories.CrmRepository;
import com.sdconecta.saudedigital.services.exceptions.DatabaseException;
import com.sdconecta.saudedigital.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CrmService {

    @Autowired
    CrmRepository repository;

    public Crm create(Crm crm){
        repository.save(crm);
        return crm;
    }
    public Crm update(Long id, Crm newCrm){
        try {
            Crm crm = repository.findById(id).get();
            updateData(crm, newCrm);

            repository.save(crm);
            return crm;
        }
        catch(NoSuchElementException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<Crm> findAll(){
        return repository.findAll();
    }
    public List<Crm> findBySpecialty(String specialty){
        return repository.findBySpecialtyContainingIgnoreCase(specialty);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Crm findById(Long id) {
        Optional<Crm> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    private void updateData(Crm crm, Crm newCrm) {
        crm.setCrm(newCrm.getCrm());
        crm.setUf(newCrm.getUf());
        crm.setSpecialty(newCrm.getSpecialty());
        crm.setUser(newCrm.getUser());
    }

}
