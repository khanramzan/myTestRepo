package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.SanctionFights;
import rams.app.domain.Sanctioner;



public interface SanctionFightsRepository extends JpaRepository<SanctionFights,Long>{
	public SanctionFights findByIdSanctionfights(Long idSanctionFights);
}
