package rams.app.rerpository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.Authorities;
import rams.app.domain.City;

public interface CityRepository extends JpaRepository<City, Integer>{

	public City findByIdCity(Integer idCity) ;
	

}
