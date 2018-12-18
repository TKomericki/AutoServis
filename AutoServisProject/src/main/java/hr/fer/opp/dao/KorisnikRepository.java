package hr.fer.opp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.opp.domain.Korisnik;
@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{

	Optional<Korisnik> findByEmail(String email);
	
}
