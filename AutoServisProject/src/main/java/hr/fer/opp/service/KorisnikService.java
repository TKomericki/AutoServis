package hr.fer.opp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import hr.fer.opp.domain.Korisnik;

@Service
public interface KorisnikService {
	public List<Korisnik> listAll();
	public Korisnik createKorisnik(Korisnik korisnik);
}
