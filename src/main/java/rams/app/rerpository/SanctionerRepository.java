package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.Sanctioner;

public interface SanctionerRepository extends JpaRepository<Sanctioner,Long>{
	public Sanctioner findByIdSanctioner(Long idSanctioner) ;
}
