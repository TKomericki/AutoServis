package opp.dao;

import opp.domain.Prijava;
import opp.domain.PrijavaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("prijavaRepository")
public interface PrijavaRepository extends JpaRepository<Prijava, PrijavaKey>{

}
