package com.gpch.login.repository;

import com.gpch.login.model.User;
import com.gpch.login.model.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("uslugaRepository")
public interface UslugaRepository extends JpaRepository<Usluga, Long>{

}
