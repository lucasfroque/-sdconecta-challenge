package com.sdconecta.saudedigital.services;

import com.sdconecta.saudedigital.dto.UserDTO;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.UserRepository;
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
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private LoginService loginService;

    public User create(UserDTO userDTO){
        try {
            User user = new User();
            updateData(user, userDTO);
            repository.save(user);
            return user;
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Integer id, UserDTO newUser){
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

    public UserDTO userToDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setCrms(user.getCrms());
        userDto.setMobilePhone(user.getMobilePhone());
        userDto.setRoles(user.getRoles());
        userDto.setAuthorizationStatus(user.getAuthorizationStatus());
        return userDto;
    }
    private void updateData(User user, UserDTO userDto) {
        user.setEmail(userDto.getEmail());
        user.setPassword(loginService.encodePassword(userDto.getPassword()));
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setCrms(userDto.getCrms());
        user.setMobilePhone(userDto.getMobilePhone());
        user.setRoles(userDto.getRoles());
        user.setAuthorizationStatus(userDto.getAuthorizationStatus());
    }
}
