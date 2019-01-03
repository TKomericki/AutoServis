package opp.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import opp.domain.*;
import opp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        //System.out.println(auth.isAuthenticated() + " " + auth.getName() + " " + isLoggedIn);
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
    	for(GrantedAuthority authority : auth.getAuthorities()) {
    		if(authority.getAuthority().equals("ADMIN")) users.addAll(userService.getAllUsers());
    		else if(authority.getAuthority().equals("KORISNIK")) prijave.addAll(userService.getUserPrijave(user.getId()));
    		else if(authority.getAuthority().equals("SERVISER")) prijave.addAll(userService.getServiserPrijave(user.getId()));
    	}
    	modelAndView.addObject("users", users);
    	modelAndView.addObject("prijave", prijave); 
        modelAndView.setViewName("ispis");
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
        modelAndView.setViewName("popravak");
        return modelAndView;
    }

}
