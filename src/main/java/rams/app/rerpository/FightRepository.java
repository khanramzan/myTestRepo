package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.Fight;

public interface FightRepository extends JpaRepository<Fight ,Long>{
	public Fight findByidFight(Long idFight) ;
}
