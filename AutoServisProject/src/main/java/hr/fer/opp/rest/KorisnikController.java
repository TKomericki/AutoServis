package hr.fer.opp.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.opp.domain.Korisnik;
import hr.fer.opp.service.KorisnikService;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {
	@Autowired
	private KorisnikService korisnikService;
	@GetMapping("")
	public List<Korisnik> listKorisnici()
	{
		return korisnikService.listAll();
	}
	
	@PostMapping("/register")
	public Korisnik createKorisnik(@RequestBody Korisnik korisnik)
	{
		return korisnikService.createKorisnik(korisnik);
	}
	
	
}
