package com.gpch.login.service;

import com.gpch.login.model.*;
import com.gpch.login.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private PrijavaRepository prijavaRepository;
    private UslugaRepository uslugaRepository;
    private RadnoVrijemeRepository radnoVrijemeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PrijavaRepository prijavaRepository,
    				   UslugaRepository uslugaRepository, RadnoVrijemeRepository radnoVrijemeRepository, 
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.prijavaRepository = prijavaRepository;
        this.uslugaRepository = uslugaRepository;
        this.radnoVrijemeRepository = radnoVrijemeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRole("KORISNIK");
        return userRepository.save(user);
    }
    
    public Set<User> getAllUsers(){
    	Set<User> users = new HashSet<>();
    	users.addAll(userRepository.findAll());
    	users.removeIf(r -> r.getRole().equals("ADMIN"));
    	return users;
    }
    
    public Set<Prijava> getUserPrijave(int user_id){
    	Set<Prijava> prijave = new HashSet<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getPrijavaKey().getIdKorisnika() != user_id);
    	return prijave;
    }
    
    public Set<Prijava> getServiserPrijave(int serviser_id){
    	Set<Prijava> prijave = new HashSet<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getIdServisera() != serviser_id);
    	return prijave;
    }
}