package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.MediaRelation;

public interface MediaRelationRepository extends JpaRepository<MediaRelation,Long> {
	public MediaRelation findByIdMediaRelation(Long idMediaRelation) ;
}
