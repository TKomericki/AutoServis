package hr.fer.opp.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.fer.opp.service.KorisnikService;
import hr.fer.opp.domain.Korisnik;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "/korisnici")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	@GetMapping("")
	public List<Korisnik> listKorisnici(){
		return korisnikService.listAll();
	}
}
