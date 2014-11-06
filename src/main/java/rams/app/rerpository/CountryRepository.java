package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.Country;

public interface CountryRepository extends JpaRepository<Country, String>{
	public Country findByCodeCountry(String code) ;
	
	
	public City findByCityCollection(Country countryCode, Integer idCity);
}
