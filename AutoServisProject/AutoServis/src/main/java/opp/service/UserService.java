package opp.service;

import opp.domain.*;
import opp.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
    private VezaUslugaPrijavaRepository vezaUslugaPrijavaRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, PrijavaRepository prijavaRepository,
    				   UslugaRepository uslugaRepository, RadnoVrijemeRepository radnoVrijemeRepository,
    				   ZamjenskoVoziloRepository zamjenskoVoziloRepository,
    				   VezaUslugaPrijavaRepository vezaUslugaPrijavaRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.prijavaRepository = prijavaRepository;
        this.uslugaRepository = uslugaRepository;
        this.zamjenskoVoziloRepository = zamjenskoVoziloRepository;
        this.radnoVrijemeRepository = radnoVrijemeRepository;
        this.vezaUslugaPrijavaRepository = vezaUslugaPrijavaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User findUserById(int id) {
    	return userRepository.findById(id).get();
    }
    
    public Optional<Prijava> findPrijavaByPrijavaKey(int id, Timestamp vrijeme) {
    	PrijavaKey prijavaKey = new PrijavaKey();
    	prijavaKey.setIdKorisnika(id);
    	prijavaKey.setVrijemePrijave(vrijeme);
    	return prijavaRepository.findById(prijavaKey);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRole("KORISNIK");
        return userRepository.save(user);
    }
    
    public Prijava savePrijava(Prijava prijava) {
    	return prijavaRepository.save(prijava);
    }
    
    
    public User replaceUser(User newUser) {
    	System.out.println(newUser.getId());
    	User oldUser = userRepository.findById(newUser.getId()).get();
    	if(newUser.getPassword().isEmpty()) newUser.setPassword(oldUser.getPassword());
    	else newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
    	//userRepository.delete(oldUser);
    	userRepository.save(newUser);

    	return newUser;
    }
    
    public User saveServiser(User serviser) {
        serviser.setPassword(bCryptPasswordEncoder.encode(serviser.getPassword()));
        serviser.setId(this.getAllUsers().size());
        serviser.setActive(1);
        serviser.setRole("SERVISER");
        return userRepository.save(serviser);
    }
    
    public List<User> getAllUsers(){
    	List<User> users = new ArrayList<>();
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
    
    public List<Prijava> getUserPrijave(int user_id){
    	List<Prijava> prijave = new ArrayList<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getPrijavaKey().getIdKorisnika() != user_id);
    	prijave.removeIf(s -> s.isPreuzeto() == true);
    	return prijave;
    }
    
    public List<Prijava> getUserPreuzetePrijave(int user_id){
    	List<Prijava> prijave = new ArrayList<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getPrijavaKey().getIdKorisnika() != user_id);
    	prijave.removeIf(s -> s.isPreuzeto() == false || s.isZavrseno() == true);
    	return prijave;
    }
    
    public List<Prijava> getUserZavrsenePrijave(int user_id){
    	List<Prijava> prijave = new ArrayList<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getPrijavaKey().getIdKorisnika() != user_id);
    	prijave.removeIf(s -> s.isZavrseno() == false);
    	return prijave;
    }
    
    public List<Prijava> getServiserPrijave(int serviser_id){
    	List<Prijava> prijave = new ArrayList<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getIdServisera() != serviser_id);
    	prijave.removeIf(s -> s.isPreuzeto() == true);
    	return prijave;
    }
    
    public List<Prijava> getServiserPreuzetePrijave(int serviser_id){
    	List<Prijava> prijave = new ArrayList<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getIdServisera() != serviser_id);
    	prijave.removeIf(s -> s.isPreuzeto() == false || s.isZavrseno() == true);
    	return prijave;
    }
    
    public List<Prijava> getServiserZavrsenePrijave(int serviser_id){
    	List<Prijava> prijave = new ArrayList<>();
    	prijave.addAll(prijavaRepository.findAll());
    	prijave.removeIf(s -> s.getIdServisera() != serviser_id);
    	prijave.removeIf(s -> s.isZavrseno() == false);
    	return prijave;
    }
    
    public List<ZamjenskoVozilo> getSlobodnaVozila() {
    	List<ZamjenskoVozilo> zamjenskaVozila = new ArrayList<>();
    	for(ZamjenskoVozilo vozilo : zamjenskoVoziloRepository.findAll()) {
    		if(vozilo.getIdKorisnik() == null) zamjenskaVozila.add(vozilo);
    	}
    	//System.out.println(zamjenskaVozila.size());
    	return zamjenskaVozila;
    }
    
    public void zauzmiVozilo(int id, String reg){
    	ZamjenskoVozilo vozilo = zamjenskoVoziloRepository.findById(reg).get();
    	vozilo.setIdKorisnik(Integer.valueOf(id).toString());
    	zamjenskoVoziloRepository.save(vozilo);
    }
    
    public void oslobodiVozilo(String reg){
    	ZamjenskoVozilo vozilo = zamjenskoVoziloRepository.findById(reg).get();
    	vozilo.setIdKorisnik(null);
    	zamjenskoVoziloRepository.save(vozilo);
    }
    
    public ZamjenskoVozilo getVoziloById(String reg) {
    	return zamjenskoVoziloRepository.findById(reg).get();
    }
    
    public Optional<RadnoVrijeme> getRadnoVrijeme(String idRadnogVremena){
    	Optional<RadnoVrijeme> radnoVrijeme = radnoVrijemeRepository.findById(Integer.parseInt(idRadnogVremena));
    	return radnoVrijeme;
    }
    
    public List<Integer> getJutarnjeSmjene(int dan){
    	List<RadnoVrijeme> svaRadnaVremena = this.getAllRadnaVremena();
    	List<Integer> indeksiVremena = new ArrayList<>();
    	if(dan == 1) svaRadnaVremena.removeIf(s -> s.getPonedjeljakPocetak().getHour() != 7);
    	if(dan == 2) svaRadnaVremena.removeIf(s -> s.getUtorakPocetak().getHour() != 7);
    	if(dan == 3) svaRadnaVremena.removeIf(s -> s.getSrijedaPocetak().getHour() != 7);
    	if(dan == 4) svaRadnaVremena.removeIf(s -> s.getCetvrtakPocetak().getHour() != 7);
    	if(dan == 5) svaRadnaVremena.removeIf(s -> s.getPetakPocetak().getHour() != 7);
    	for(RadnoVrijeme radno : svaRadnaVremena) indeksiVremena.add(radno.getIdRadnogVremena());
    	return indeksiVremena;
    }
    
    public Optional<Usluga> getUslugaById(int id){
    	return uslugaRepository.findById(id);
    }
    
    public void addUslugaPrijavaVeze(Set<Integer> uslugeId, int id, Timestamp vrijeme) {
    	for(int uslugaId : uslugeId) {
    		VezaUslugaPrijava veza = new VezaUslugaPrijava();
    		veza.setVezaUslugaPrijava(new UslugaPrijavaKey());
    		veza.getVezaUslugaPrijava().setIdKorisnika(id);
    		veza.getVezaUslugaPrijava().setVrijemePrijave(vrijeme);
    		veza.getVezaUslugaPrijava().setIdUsluge(uslugaId);  		
    		vezaUslugaPrijavaRepository.save(veza);
    	}
    }
    
    public List<Usluga> getPrijavaUsluge(int id, Timestamp vrijeme){
    	List<VezaUslugaPrijava> veze = vezaUslugaPrijavaRepository.findAll();
    	veze.removeIf(s -> s.getVezaUslugaPrijava().getIdKorisnika() != id || !s.getVezaUslugaPrijava().getVrijemePrijave().equals(vrijeme));
    	
    	List<Usluga> usluge = new ArrayList<>();
    	for(VezaUslugaPrijava veza : veze) usluge.add(uslugaRepository.findById(veza.getVezaUslugaPrijava().getIdUsluge()).get());
    	return usluge;
    }
    
    public Set<Integer> getPrijavaUslugeId(int id, Timestamp vrijeme){
    	List<VezaUslugaPrijava> veze = vezaUslugaPrijavaRepository.findAll();
    	veze.removeIf(s -> s.getVezaUslugaPrijava().getIdKorisnika() != id || !s.getVezaUslugaPrijava().getVrijemePrijave().equals(vrijeme));
    	
    	Set<Integer> usluge = new HashSet<>();
    	for(VezaUslugaPrijava veza : veze) usluge.add(veza.getVezaUslugaPrijava().getIdUsluge());
    	return usluge;
    }
    
    public void replaceUslugaPrijavaVeze(Set<Integer> uslugeId, int id, Timestamp vrijeme) {
    	List<VezaUslugaPrijava> veze = vezaUslugaPrijavaRepository.findAll();
    	veze.removeIf(s -> s.getVezaUslugaPrijava().getIdKorisnika() != id || !s.getVezaUslugaPrijava().getVrijemePrijave().equals(vrijeme));
    	List<Integer> postojeceVeze = new ArrayList<>();
    	
    	for(VezaUslugaPrijava veza : veze) {
    		if(!uslugeId.contains(veza.getVezaUslugaPrijava().getIdUsluge())) vezaUslugaPrijavaRepository.delete(veza);
    		else postojeceVeze.add(veza.getVezaUslugaPrijava().getIdUsluge());
    	}
    	
    	uslugeId.removeIf(s -> postojeceVeze.contains(s));
    	this.addUslugaPrijavaVeze(uslugeId, id, vrijeme);
    }
}