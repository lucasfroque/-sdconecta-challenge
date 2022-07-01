package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.UserRepository;
import com.sdconecta.saudedigital.services.exceptions.DatabaseException;
import com.sdconecta.saudedigital.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

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

}
