package hr.fer.opp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.opp.domain.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{

}
