package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.Promoter;

public interface PromoterRepository extends JpaRepository<Promoter,Long>{
	public Promoter findByIdPromoter(Long idPromoter) ;
}
