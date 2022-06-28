package com.sdconecta.saudedigital.repositories;

import com.sdconecta.saudedigital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
