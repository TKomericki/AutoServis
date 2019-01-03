package opp.dao;

import opp.domain.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("uslugaRepository")
public interface UslugaRepository extends JpaRepository<Usluga, Long>{

}
