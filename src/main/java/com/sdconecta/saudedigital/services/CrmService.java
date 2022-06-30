package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.CrmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrmService {

    @Autowired
    CrmRepository repository;

    public Crm create(Crm crm){
        repository.save(crm);
        return crm;
    }
    public Crm update(Integer id, Crm newCrm){
        Crm oldCrm = repository.findById(id).get();
        updateData(oldCrm, newCrm);

        repository.save(oldCrm);
        return oldCrm;
    }

    public List<Crm> findAll(){
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Crm findById(Integer id) {
        Optional<Crm> obj = repository.findById(id);
        return obj.get();
    }

    private void updateData(Crm oldCrm, Crm newCrm) {
        oldCrm.setCrm(newCrm.getCrm());
        oldCrm.setUf(newCrm.getUf());
        oldCrm.setUser(newCrm.getUser());
    }

}
