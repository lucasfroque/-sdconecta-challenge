package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User create(User user){
        repository.save(user);
        return user;
    }

    public User update(Integer id, User newUser){
        User oldUser = repository.findById(id).get();
        updateData(oldUser, newUser);

        repository.save(oldUser);
        return oldUser;
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

    private void updateData(User oldUser, User newUser) {
        oldUser.setEmail(newUser.getEmail());
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setCrm(newUser.getCrm());
        oldUser.setMobilePhone(newUser.getMobilePhone());
    }

}
