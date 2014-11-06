package rams.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rams.app.domain.Authorities;
import rams.app.domain.City;
import rams.app.domain.Country;

/**
 * Spring service that handles CRUD requests for City entities
 * 
 */
public interface CityService {

	/**
	 * Return all City entity
	 * 
	 */
	public Page<City> findAllCities(int idx, int elements, String dir );

	/**
	 */
	public City findCityByPrimaryKey(Integer idCity);

	/**
	 * Save an existing City entity
	 * 
	 */
	public void saveCity(City city);

	/**
	 * Return a count of all City entity
	 * 
	 */
	public long countCitys();

	/**
	 * Delete an existing Country entity
	 * 
	 */
	public City deleteCityCountryCode(Integer city_idCity, String related_countrycode_codeCountry);

	/**
	 * Save an existing Country entity
	 * 
	 */
	public City saveCityCountryCode(Integer idCity_1, Country related_countrycode);

	/**
	 * Delete an existing City entity
	 * 
	 */
	public void deleteCity(City city_1);

	/**
	 * Load an existing City entity
	 * 
	 */
	public List<City> loadCitys();



	
}