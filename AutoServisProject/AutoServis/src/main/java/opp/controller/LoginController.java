package opp.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.validation.Valid;

import opp.domain.*;
import opp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
    
    @RequestMapping(value="/registracijaServisera", method = RequestMethod.GET)
    public ModelAndView serviserRegistration(){
        ModelAndView modelAndView = new ModelAndView();
        List<RadnoVrijeme> radnaVremena = userService.getAllRadnaVremena();
        User serviser = new User();
        modelAndView.addObject("user", serviser);
        modelAndView.addObject("radnaVremena", radnaVremena);
        modelAndView.setViewName("registracijaServisera");
        return modelAndView;
    }
    
    @RequestMapping(value = "/registracijaServisera", method = RequestMethod.POST)
    public ModelAndView createNewServiser(@Valid User serviser, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User postojeciServiser = userService.findUserByEmail(serviser.getEmail());
        if (postojeciServiser != null) {
            bindingResult.rejectValue("email", "error.serviser",
                            "There is already a serviser registered with the email provided");
        }
        List<RadnoVrijeme> radnaVremena = userService.getAllRadnaVremena();
    	modelAndView.addObject("radnaVremena", radnaVremena);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registracijaServisera");
        } else {
            userService.saveServiser(serviser);
            modelAndView.addObject("successMessage", "Serviser has been registered successfully");
            modelAndView.addObject("serviser", new User());
            modelAndView.setViewName("registracijaServisera");

        }
        return modelAndView;
    }

    
    @RequestMapping(value={"/", "/pocetna"}, method = RequestMethod.GET)
    public ModelAndView pocetna(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	boolean isLoggedIn = !auth.getName().equals("anonymousUser");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("isLoggedIn", isLoggedIn);
        modelAndView.setViewName("pocetna");
        return modelAndView;
    }
    
    @RequestMapping(value={"/ispis"}, method = RequestMethod.GET)
    public ModelAndView ispis(){
    	ModelAndView modelAndView = new ModelAndView();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findUserByEmail(auth.getName());
    	List<User> users = new ArrayList<>();
    	List<Prijava> prijave = new ArrayList<>();
    	List<Prijava> preuzetePrijave = new ArrayList<>();
    	List<Prijava> gotovePrijave = new ArrayList<>();
    	String role = "";
    	
    	for(GrantedAuthority authority : auth.getAuthorities()) {
    		role = authority.getAuthority();
    		if(authority.getAuthority().equals("ADMIN")) users.addAll(userService.getAllUsers());
    		else if(authority.getAuthority().equals("KORISNIK")) { 
    			prijave.addAll(userService.getUserPrijave(user.getId()));
    			preuzetePrijave.addAll(userService.getUserPreuzetePrijave(user.getId()));
    			gotovePrijave.addAll(userService.getUserZavrsenePrijave(user.getId()));
    		}
    		else if(authority.getAuthority().equals("SERVISER")) { 
    			prijave.addAll(userService.getServiserPrijave(user.getId()));
    			preuzetePrijave.addAll(userService.getServiserPreuzetePrijave(user.getId()));
    			gotovePrijave.addAll(userService.getServiserZavrsenePrijave(user.getId()));
    		}
    	}
    	
    	modelAndView.addObject("users", users);
    	modelAndView.addObject("prijave", prijave);
    	modelAndView.addObject("preuzetePrijave", preuzetePrijave);
    	modelAndView.addObject("gotovePrijave", gotovePrijave);
    	modelAndView.addObject("role", role);
    	
        modelAndView.setViewName("pocetnaLogin");
        return modelAndView;
    }
    
    @RequestMapping(value={"/error"}, method = RequestMethod.GET)
    public ModelAndView error(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        return modelAndView;
    }
    
    @RequestMapping(value={"/popravak"}, method = RequestMethod.GET)
    public ModelAndView popravak(){
        ModelAndView modelAndView = new ModelAndView();
        User odabraniServiser = new User();
        modelAndView.addObject("odabraniServiser", odabraniServiser);
        modelAndView.addObject("serviseri", userService.getAllServiseri());
        modelAndView.setViewName("popravak");
        return modelAndView;
    }
    
    @RequestMapping(value= {"/popravak2"}, method = RequestMethod.GET)
    public ModelAndView popravak(@RequestParam String email) {
    	ModelAndView modelAndView = new ModelAndView();
    	PrijavaModel prijava = new PrijavaModel();
    	//prijava.setUsluge(new HashSet<>());
    	Set<LocalDate> datumi = new HashSet<>();
    	LocalDateTime date = LocalDateTime.now();
    	List<ZamjenskoVozilo> zamjenskaVozila = userService.getSlobodnaVozila();
    	if(email.isEmpty()) {
    		for(int i = 1; i <= 10; i++) {
        		LocalDateTime newDate = date.plusDays(i);
        		int day = newDate.getDayOfWeek().getValue();
        		if(day < 6) datumi.add(newDate.toLocalDate());    			   		
        	}

    	}
    	else {
    		User serviser = userService.findUserByEmail(email);   	
	    	RadnoVrijeme radnoVrijeme = userService.getRadnoVrijeme(serviser.getIdRadnogVremena()).get();
	    	
	    	List<LocalTime> vremena = new LinkedList<>();
	    	vremena.add(radnoVrijeme.getPonedjeljakPocetak());
	    	vremena.add(radnoVrijeme.getUtorakPocetak());
	    	vremena.add(radnoVrijeme.getSrijedaPocetak());
	    	vremena.add(radnoVrijeme.getCetvrtakPocetak());
	    	vremena.add(radnoVrijeme.getPetakPocetak());
	    	for(int i = 1; i <= 10; i++) {
	    		LocalDateTime newDate = date.plusDays(i);
	    		int day = newDate.getDayOfWeek().getValue();
	    		if(day < 6) {
	    			if(vremena.get(day - 1).getHour() == 7) datumi.add(newDate.toLocalDate());    			
	    		}	
	    	}
	    	prijava.setIdServisera(String.valueOf(serviser.getId()));
    	}
    	
    	modelAndView.addObject("usluge", userService.getAllUsluge());
    	modelAndView.addObject("datumi", datumi.stream().sorted().collect(Collectors.toList()));
    	modelAndView.addObject("prijava", prijava);
    	modelAndView.addObject("zamjenskaVozila", zamjenskaVozila);
        modelAndView.setViewName("popravak2");
        return modelAndView;
    }
    
    @RequestMapping(value= {"/prijavaPopravka"}, method = RequestMethod.POST)
    public ModelAndView prijavaPopravka(PrijavaModel prijavaModel) {
    	
    	if(prijavaModel.getVrijemeDolaska() == null || prijavaModel.getUsluge().isEmpty()) {
    		ModelAndView modelAndView;
    		if(prijavaModel.getIdServisera().isEmpty()) modelAndView = this.popravak("");
    		else modelAndView = this.popravak(userService.findUserById(Integer.parseInt(prijavaModel.getIdServisera())).getEmail());
    		modelAndView.addObject("prijava", prijavaModel);
    		if(prijavaModel.getVrijemeDolaska() == null) modelAndView.addObject("missingDatum", "Molimo unesite datum dolaska u servis");
    		if(prijavaModel.getUsluge().isEmpty()) modelAndView.addObject("missingUsluge", "Molimo odaberite barem jednu uslugu za popravak");
    		return modelAndView;   		 
    	}
    	
    	ModelAndView modelAndView = new ModelAndView();

    	User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	Prijava prijava = new Prijava();
    	if(prijavaModel.getIdServisera().isEmpty()) {
    		List<Integer> jutarnjeSmjene = userService.getJutarnjeSmjene(LocalDate.parse(prijavaModel.getVrijemeDolaska()).getDayOfWeek().getValue());
    		List<User> sviServiseri = new ArrayList<>();
    		for(User serviser : userService.getAllServiseri()){
    			if(jutarnjeSmjene.contains(Integer.parseInt(serviser.getIdRadnogVremena()))) sviServiseri.add(serviser);
    		}
    		
    		Random rand = new Random();
    		prijava.setIdServisera(sviServiseri.get(rand.nextInt(sviServiseri.size())).getId());
    		    		
    	}
    	else prijava.setIdServisera(Integer.parseInt(prijavaModel.getIdServisera()));
    	
    	if(!prijavaModel.getRegZamjensko().isEmpty()) {
    		prijava.setRegZamjensko(prijavaModel.getRegZamjensko());
    		userService.zauzmiVozilo(user.getId(), prijavaModel.getRegZamjensko());
    	}
    	prijava.setPrijavaKey(new PrijavaKey());
    	prijava.getPrijavaKey().setIdKorisnika(user.getId());
    	prijava.getPrijavaKey().setVrijemePrijave(Timestamp.valueOf(LocalDateTime.now()));
    	prijava.setPreuzeto(false);
    	prijava.setZavrseno(false);
    	prijava.setDodatniZahtjevi(prijavaModel.getDodatniZahtjevi());
    	prijava.setVrijemeDolaska(LocalDate.parse(prijavaModel.getVrijemeDolaska()));
    	
    	/*Set<Usluga> usluge = new HashSet<>();
    	for(int usluga : prijavaModel.getUsluge()) {
    		usluge.add(userService.getUslugaById(usluga).get());
    	}*/
    	userService.addUslugaPrijavaVeze(prijavaModel.getUsluge(), prijava.getPrijavaKey().getIdKorisnika(), prijava.getPrijavaKey().getVrijemePrijave());
    	
    	prijava = userService.savePrijava(prijava); 
    	modelAndView.addObject("Message", "Prijava popravka je uspješno stvorena.");
        modelAndView.setViewName("uspjeh");
        return modelAndView;
    }
    
    @RequestMapping(value= {"/editPrijava"}, method = RequestMethod.GET)
    public ModelAndView editPrijava(@RequestParam int id, @RequestParam Timestamp vrijeme) {    	
    	//System.out.println(id + " " + vrijeme);
    	ModelAndView modelAndView = new ModelAndView();
    	Prijava prijava = userService.findPrijavaByPrijavaKey(id, vrijeme).get();
    	//System.out.println(prijava.getIdServisera() + " " + prijava.getRegZamjensko() + " " + prijava.getPrijavaKey().getIdKorisnika() + " " + prijava.getPrijavaKey().getVrijemePrijave() + " " + prijava.getVrijemeDolaska());
    	
    	PrijavaModel prijavaModel = new PrijavaModel();
    	prijavaModel.setVrijemePrijave(prijava.getPrijavaKey().getVrijemePrijave().toString());
    	prijavaModel.setKorisnikId(prijava.getPrijavaKey().getIdKorisnika());
    	prijavaModel.setDodatniZahtjevi(prijava.getDodatniZahtjevi());
    	prijavaModel.setIdServisera(String.valueOf(prijava.getIdServisera()));
    	prijavaModel.setRegZamjensko(prijava.getRegZamjensko());
    	prijavaModel.setVrijemeDolaska(prijava.getVrijemeDolaska().toString());

    	prijavaModel.setUsluge(userService.getPrijavaUslugeId(prijavaModel.getKorisnikId(), Timestamp.valueOf(prijavaModel.getVrijemePrijave())));
    	
    	User serviser = userService.findUserById(prijava.getIdServisera());
    	RadnoVrijeme radnoVrijeme = userService.getRadnoVrijeme(serviser.getIdRadnogVremena()).get();
    	
    	LocalDateTime date = LocalDateTime.now();
    	List<LocalTime> vremena = new LinkedList<>();
    	vremena.add(radnoVrijeme.getPonedjeljakPocetak());
    	vremena.add(radnoVrijeme.getUtorakPocetak());
    	vremena.add(radnoVrijeme.getSrijedaPocetak());
    	vremena.add(radnoVrijeme.getCetvrtakPocetak());
    	vremena.add(radnoVrijeme.getPetakPocetak());
    	
    	List<LocalDate> datumi = new ArrayList<>();
    	for(int i = 1; i <= 10; i++) {
    		LocalDateTime newDate = date.plusDays(i);
    		int day = newDate.getDayOfWeek().getValue();
    		if(day < 6) {
    			if(vremena.get(day - 1).getHour() == 7) datumi.add(newDate.toLocalDate());    			
    		}    		
    	}
    	
    	List<ZamjenskoVozilo> vozila = new ArrayList<>();
    	if(!prijavaModel.getRegZamjensko().isEmpty()) vozila.add(userService.getVoziloById(prijavaModel.getRegZamjensko()));
    	vozila.addAll(userService.getSlobodnaVozila());
    	User currentUser = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    	
    	modelAndView.addObject("isKorisnik", currentUser.getRole().equals("KORISNIK"));
    	modelAndView.addObject("datumi", datumi);
    	modelAndView.addObject("prijavaModel", prijavaModel);
    	modelAndView.addObject("usluge", userService.getAllUsluge());
    	modelAndView.addObject("zamjenskaVozila", vozila);
    	modelAndView.setViewName("popravakEdit");
    	return modelAndView;
    }
        
    @RequestMapping(value = {"/editKorisnik"}, method = RequestMethod.GET) 
    public ModelAndView editKorisnik(@RequestParam String email) {
    	ModelAndView modelAndView = new ModelAndView();
    	User postojeciKorisnik = userService.findUserByEmail(email);
    	//System.out.println(postojeciKorisnik);
    	//System.out.println(postojeciKorisnik.getPassword());
        modelAndView.addObject("postojeciKorisnik", postojeciKorisnik);
        if(postojeciKorisnik.getRole().equals("KORISNIK")) modelAndView.setViewName("korisnikEdit");
        else {
        	modelAndView.addObject("radnaVremena", userService.getAllRadnaVremena());
        	modelAndView.setViewName("serviserEdit");
        }
        return modelAndView;
    }
    
    @RequestMapping(value = {"/popravakPromijenjen"}, method = RequestMethod.POST)
    public ModelAndView popravakPromijenjen(PrijavaModel prijavaModel) {
    	if(prijavaModel.getUsluge().isEmpty()) {
    		ModelAndView modelAndView = this.editPrijava(prijavaModel.getKorisnikId(), Timestamp.valueOf(prijavaModel.getVrijemePrijave()));
    		modelAndView.addObject("missingUsluge", "Molimo odaberite barem jednu uslugu za popravak");
    		return modelAndView;   		 
    	}
    	
    	ModelAndView modelAndView = new ModelAndView();
    	
    	Prijava staraPrijava = userService.findPrijavaByPrijavaKey(prijavaModel.getKorisnikId(), Timestamp.valueOf(prijavaModel.getVrijemePrijave())).get();
    	Prijava prijava = new Prijava();
    	prijava.setIdServisera(Integer.parseInt(prijavaModel.getIdServisera()));
    	if(!staraPrijava.getRegZamjensko().isEmpty()) {
    		if(!staraPrijava.getRegZamjensko().equals(prijavaModel.getRegZamjensko())) userService.oslobodiVozilo(staraPrijava.getRegZamjensko());
    	}
    	if(!prijavaModel.getRegZamjensko().isEmpty()) userService.zauzmiVozilo(prijavaModel.getKorisnikId(), prijavaModel.getRegZamjensko());
    	
    	prijava.setRegZamjensko(prijavaModel.getRegZamjensko());
    	prijava.setPrijavaKey(new PrijavaKey());
    	prijava.getPrijavaKey().setIdKorisnika(prijavaModel.getKorisnikId());
    	prijava.getPrijavaKey().setVrijemePrijave(Timestamp.valueOf(prijavaModel.getVrijemePrijave()));
    	prijava.setPreuzeto(staraPrijava.isPreuzeto());
    	prijava.setZavrseno(staraPrijava.isZavrseno());
    	prijava.setDodatniZahtjevi(prijavaModel.getDodatniZahtjevi());
    	prijava.setVrijemeDolaska(LocalDate.parse(prijavaModel.getVrijemeDolaska()));
    	//Set<Usluga> usluge = new HashSet<>();
    	/*for(int usluga : prijavaModel.getUsluge()) {
    		usluge.add(userService.getUslugaById(usluga).get());
    		System.out.println(usluga);
    	}*/
    	userService.replaceUslugaPrijavaVeze(prijavaModel.getUsluge(), prijavaModel.getKorisnikId(), Timestamp.valueOf(prijavaModel.getVrijemePrijave()));
    	userService.savePrijava(prijava);
    	
    	modelAndView.addObject("Message", "Prijava popravka je uspješno promijenjena.");
    	modelAndView.setViewName("uspjeh");
    	return modelAndView;
    }
    
    @RequestMapping(value = {"/korisnikPromijenjen"}, method = RequestMethod.POST)
    public ModelAndView korisnikPromijenjen(User user) {
    	ModelAndView modelAndView = new ModelAndView();    	
    	userService.replaceUser(user);
    	
    	modelAndView.addObject("Message", "Podaci o korisniku su uspješno promijenjeni.");
    	modelAndView.setViewName("uspjeh");
    	return modelAndView;
    }
    
    @RequestMapping(value = {"/prihvatiPopravak"}, method = RequestMethod.GET)
    public ModelAndView prihvatiPopravak(@RequestParam int id, @RequestParam Timestamp vrijeme) {
    	ModelAndView modelAndView = new ModelAndView();    	
    	
    	Prijava prijava = userService.findPrijavaByPrijavaKey(id, vrijeme).get();
    	prijava.setPreuzeto(true);
    	userService.savePrijava(prijava);
    	
    	modelAndView.addObject("Message", "Prijava je uspješno prihvaćena.");
    	modelAndView.setViewName("uspjeh");
    	return modelAndView;
    }
    
    @RequestMapping(value = {"/popravakZavrsen"}, method = RequestMethod.GET)
    public ModelAndView popravakZavrsen(@RequestParam int id, @RequestParam Timestamp vrijeme) {
    	ModelAndView modelAndView = new ModelAndView();    	
    	
    	Prijava prijava = userService.findPrijavaByPrijavaKey(id, vrijeme).get();
    	prijava.setZavrseno(true);
    	userService.savePrijava(prijava);
    	
    	modelAndView.addObject("Message", "Prijava je uspjesno oznaćena kao završena!");
    	modelAndView.setViewName("uspjeh");
    	return modelAndView;
    } 
}
