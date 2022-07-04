package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.dto.CrmDTO;
import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
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

    public Crm create(CrmDTO crmDTO, User user){
        Crm crm = new Crm();
        updateData(crm, crmDTO);
        crm.setUser(user);
        repository.save(crm);
        return crm;
    }
    public Crm update(Long id, CrmDTO newCrm){
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

    private void updateData(Crm crm, CrmDTO CrmDto) {
        crm.setCrm(CrmDto.getCrm());
        crm.setUf(CrmDto.getUf());
        crm.setSpecialty(CrmDto.getSpecialty());
    }

}
