package com.sdconecta.saudedigital.repositories;

import com.sdconecta.saudedigital.models.Crm;
import com.sdconecta.saudedigital.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrmRepository extends JpaRepository<Crm, Long> {

}
