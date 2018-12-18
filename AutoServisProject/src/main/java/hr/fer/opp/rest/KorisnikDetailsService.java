package hr.fer.opp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.fer.opp.domain.Korisnik;
import hr.fer.opp.service.KorisnikService;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
public class KorisnikDetailsService implements UserDetailsService {
	
	@Value("${opp.admin.password}")
	private String adminPasswordHash;
	
	@Autowired
	private KorisnikService korisnikService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new User(email, adminPasswordHash, authorities(email));
	}

	private List<GrantedAuthority> authorities(String email) {
		if ("admin@admin.hr".equals(email)) {
			return commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		}
		
//		Korisnik korisnik = korisnikService.findByEmail(email)
//				.orElseThrow(() -> new UsernameNotFoundException("Ne postoji korisnik '" + email + "'"));
//		
//		if(korisnik.getClass() == RegistriraniKorisnik.class) {
//			return commaSeparatedStringToAuthorityList("ROLE_REGISTRIRANI");
//		} else if(korisnik.getClass() == OvlasteniServiser.class) {
//			return commaSeparatedStringToAuthorityList("ROLE_SERVISER");
//		}
		
		return null;
	}

}
