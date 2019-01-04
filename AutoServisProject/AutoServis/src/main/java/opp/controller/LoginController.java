package opp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        System.out.println(radnaVremena.size());
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

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated() + " " + auth.getAuthorities());
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    
    @RequestMapping(value={"/", "/pocetna"}, method = RequestMethod.GET)
    public ModelAndView pocetna(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	boolean isLoggedIn = !auth.getName().equals("anonymousUser");
        System.out.println(auth.isAuthenticated() + " " + auth.getName() + " " + isLoggedIn);
    	/*Set<Prijava> prijave = new HashSet<>();
    	prijave.addAll(userService.getUserPrijave(1));
    	for(Prijava prijava : prijave) {
    		for(Usluga usluga : prijava.getUsluge()) {
    			System.out.println(usluga.getIdUsluge() + " " + usluga.getCijena() + " " + usluga.getImeUsluge());
    		}
    		System.out.println();
    	}*/
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
    	Set<User> users = new HashSet<>();
    	Set<Prijava> prijave = new HashSet<>();
    	String role = "";
    	
    	for(GrantedAuthority authority : auth.getAuthorities()) {
    		role = authority.getAuthority();
    		if(authority.getAuthority().equals("ADMIN")) users.addAll(userService.getAllUsers());
    		else if(authority.getAuthority().equals("KORISNIK")) prijave.addAll(userService.getUserPrijave(user.getId()));
    		else if(authority.getAuthority().equals("SERVISER")) prijave.addAll(userService.getServiserPrijave(user.getId()));
    	}
    	
    	modelAndView.addObject("users", users);
    	modelAndView.addObject("prijave", prijave);   	
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
    
    @RequestMapping(value= {"/popravak"}, method = RequestMethod.POST)
    public ModelAndView popravak(@RequestParam String email) {
    	ModelAndView modelAndView = new ModelAndView();
    	User serviser = userService.findUserByEmail(email);
    	modelAndView.addObject("serviser", serviser);
        modelAndView.setViewName("proba");
        return modelAndView;
    }
    
    /*@RequestMapping(value= {"/editPrijava"}, method = RequestMethod.POST)
    public ModelAndView editPrijava(@RequestParam PrijavaKey prijavaKey) {
    	ModelAndView modelAndView = new ModelAndView();
    	Optional<Prijava> prijava = userService.findPrijavaByPrijavaKey(prijavaKey);
    	if(prijava.isPresent()) modelAndView.addObject("prijava", prijava.get());
        modelAndView.setViewName("proba2");
        return modelAndView;
    }*/

}
