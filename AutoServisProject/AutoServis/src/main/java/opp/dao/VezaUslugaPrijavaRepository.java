package opp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import opp.domain.VezaUslugaPrijava;
import opp.domain.UslugaPrijavaKey;

public interface VezaUslugaPrijavaRepository extends JpaRepository<VezaUslugaPrijava, UslugaPrijavaKey>{

}
