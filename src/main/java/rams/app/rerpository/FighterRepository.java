package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.Fighter;

public interface FighterRepository extends JpaRepository<Fighter,Long>{
	public Fighter findByidFighter(Long idFighter) ;
}
