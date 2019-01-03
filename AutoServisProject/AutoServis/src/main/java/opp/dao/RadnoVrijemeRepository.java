package opp.dao;

import opp.domain.RadnoVrijeme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("radnoVrijemeRepository")
public interface RadnoVrijemeRepository extends JpaRepository<RadnoVrijeme, Long> {

}
