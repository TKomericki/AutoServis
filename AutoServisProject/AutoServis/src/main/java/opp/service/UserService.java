package opp.service;

import opp.domain.*;
import opp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private PrijavaRepository prijavaRepository;
    private UslugaRepository uslugaRepository;
    private ZamjenskoVoziloRepository zamjenskoVoziloRepository;
    private RadnoVrijemeRepository radnoVrijemeRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PrijavaRepository prijavaRepository,
    				   UslugaRepository uslugaRepository, RadnoVrijemeRepository radnoVrijemeRepository,
    				   ZamjenskoVoziloRepository zamjenskoVoziloRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.prijavaRepository = prijavaRepository;
        this.uslugaRepository = uslugaRepository;
        this.zamjenskoVoziloRepository = zamjenskoVoziloRepository;
        this.radnoVrijemeRepository = radnoVrijemeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<Prijava> findPrijavaByPrijavaKey(int id, Timestamp vrijeme) {
    	Set<Prijava> prijave = new HashSet<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getPrijavaKey().getIdKorisnika() == id && s.getPrijavaKey().getVrijemePrijave().equals(vrijeme));
    	return prijave.stream().findFirst();
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRole("KORISNIK");
        return userRepository.save(user);
    }
    
    public User saveServiser(User serviser) {
        serviser.setPassword(bCryptPasswordEncoder.encode(serviser.getPassword()));
        serviser.setActive(1);
        serviser.setRole("SERVISER");
        return userRepository.save(serviser);
    }
    
    public Set<User> getAllUsers(){
    	Set<User> users = new HashSet<>();
    	users.addAll(userRepository.findAll());
    	users.removeIf(r -> r.getRole().equals("ADMIN"));
    	return users;
    }
    
    public Set<User> getAllServiseri(){
    	Set<User> serviseri = new HashSet<>();
    	serviseri.addAll(userRepository.findAll());
    	serviseri.removeIf(r -> !r.getRole().equals("SERVISER"));
    	return serviseri;
    }
    
    public List<RadnoVrijeme> getAllRadnaVremena(){
    	return radnoVrijemeRepository.findAll();
    }
    
    public List<Usluga> getAllUsluge(){
    	return uslugaRepository.findAll();
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
    
    public Set<ZamjenskoVozilo> getSlobodnaVozila() {
    	Set<ZamjenskoVozilo> zamjenskaVozila = new HashSet<>();
    	zamjenskaVozila.addAll(zamjenskoVoziloRepository.findAll());
    	zamjenskaVozila.removeIf(s -> s.getIdKorisnik() != null);
    	return zamjenskaVozila;
    }
    
    public Optional<RadnoVrijeme> getRadnoVrijeme(String idRadnogVremena){
    	Optional<RadnoVrijeme> radnoVrijeme = radnoVrijemeRepository.findById(Integer.parseInt(idRadnogVremena));
    	return radnoVrijeme;
    }
}