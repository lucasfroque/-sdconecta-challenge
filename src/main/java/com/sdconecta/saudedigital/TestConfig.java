package com.sdconecta.saudedigital;

import com.sdconecta.saudedigital.models.enums.AuthorizationStatus;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String password = passwordEncoder.encode("lcs");

        User u1 = new User(null, "usuario.teste-1@email.com", password, "Lucas", "Fernando", new ArrayList<>(), "1212121", "ADMIN, USER", AuthorizationStatus.WAITING_FOR_AUTHORIZATION);
        User u2 = new User(null, "crl@mail.com", password, "Carlos", "Fernando", new ArrayList<>(), "1212121", "USER", AuthorizationStatus.WAITING_FOR_AUTHORIZATION);


        userRepository.save(u1);
        userRepository.save(u2);

    }
}