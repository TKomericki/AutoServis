package com.gpch.login.repository;

import com.gpch.login.model.Prijava;
import com.gpch.login.model.PrijavaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("prijavaRepository")
public interface PrijavaRepository extends JpaRepository<Prijava, PrijavaKey>{

}
