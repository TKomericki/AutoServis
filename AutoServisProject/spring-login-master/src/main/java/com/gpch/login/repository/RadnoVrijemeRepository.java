package com.gpch.login.repository;

import com.gpch.login.model.RadnoVrijeme;
import com.gpch.login.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("radnoVrijemeRepository")
public interface RadnoVrijemeRepository extends JpaRepository<RadnoVrijeme, Long> {

}
