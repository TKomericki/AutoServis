package hr.fer.opp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
