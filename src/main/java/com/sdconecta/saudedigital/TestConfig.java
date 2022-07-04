package com.sdconecta.saudedigital;

import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import com.sdconecta.saudedigital.models.enums.AuthorizationStatus;
import com.sdconecta.saudedigital.repositories.CrmRepository;
import com.sdconecta.saudedigital.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CrmRepository crmRepository;


    @Override
    public void run(String... args) throws Exception {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String password = passwordEncoder.encode("lcs");


        User u1 = new User(null, "usuario.teste-1@email.com", password, "Usuario", "Teste", new ArrayList<>(), "1212121", "ROLE_ADMIN, ROLE_USER", AuthorizationStatus.WAITING_FOR_AUTHORIZATION);
        User u2 = new User(null, "usuario.teste-2@email.com", password, "Usuario", "Teste2", new ArrayList<>(), "1212121", "ROLE_USER", AuthorizationStatus.WAITING_FOR_AUTHORIZATION);
        User u3 = new User(null, "joao@email.com", password, "João", "Da Silva", new ArrayList<>(), "1212121", "ROLE_USER", AuthorizationStatus.WAITING_FOR_AUTHORIZATION);

        Crm c1 = new Crm(null, "1233", "SP", "PEDIATRIA", u1);
        Crm c2 = new Crm(null, "1223", "SP", "GINECOLOGIA", u1);
        Crm c3 = new Crm(null, "1223", "RJ", "RADIOLOGIA", u2);
        Crm c4 = new Crm(null, "1223", "MG", "GERIATRIA", u3);

        userRepository.saveAll(Arrays.asList(u1, u2, u3));
        crmRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

    }
}