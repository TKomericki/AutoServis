package hr.fer.opp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import hr.fer.opp.dao.KorisnikRepository;
import hr.fer.opp.domain.Korisnik;
import hr.fer.opp.service.KorisnikService;
@Service
public class KorisnikServiceJpa implements KorisnikService{
	
	@Autowired
	KorisnikRepository korisnikRepo;
	@Override
	public List<Korisnik> listAll() {
		
		return korisnikRepo.findAll();
	}
	@Override
	public Korisnik createKorisnik(Korisnik korisnik) {
		
		return korisnikRepo.save(korisnik);
	}
	@Override
	public Optional<Korisnik> findByEmail(String email) {
		Assert.notNull(email, "Email must be given.");
		
		return korisnikRepo.findByEmail(email);
	}

}
