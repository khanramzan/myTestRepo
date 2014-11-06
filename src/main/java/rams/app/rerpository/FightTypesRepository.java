package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.FightTypes;

public interface FightTypesRepository extends JpaRepository<FightTypes,Long>{
	
	public FightTypes findByIdFightTypes(Long idFightTypes) ;

}
