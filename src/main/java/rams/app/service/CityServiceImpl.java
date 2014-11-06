package rams.app.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;









import rams.app.domain.Authorities;
import rams.app.domain.City;
import rams.app.domain.Country;
import rams.app.rerpository.CityRepository;
import rams.app.rerpository.CountryRepository;

/**
 * Spring service that handles CRUD requests for City entities
 * 
 */

@Service("CityService")
@Transactional
public class CityServiceImpl implements CityService {

	/**
	 * DAO injected by Spring that manages City entities
	 * 
	 */
	@Autowired
	private CityRepository cityDAO;

	/**
	 * DAO injected by Spring that manages Country entities
	 * 
	 */
	@Autowired
	private CountryRepository countryDAO;

	/**
	 * Instantiates a new CityServiceImpl.
	 *
	 */
	public CityServiceImpl() {
	}


	/**
	 */
	@Transactional
	public City findCityByPrimaryKey(Integer idCity) {
		return cityDAO.findByIdCity(idCity);
	}

	/**
	 * Save an existing City entity
	 * 
	 */
	@Transactional
	public void saveCity(City city) {
		City existingCity = cityDAO.findByIdCity(city.getIdCity());

		if (existingCity != null) {
			if (existingCity != city) {
				existingCity.setIdCity(city.getIdCity());
				existingCity.setCityName(city.getCityName());
				existingCity.setDistrict(city.getDistrict());
			}
			city = cityDAO.save(existingCity);
		} else {
			city = cityDAO.save(city);
		}
		cityDAO.flush();
	}

	/**
	 * Return a count of all City entity
	 * 
	 */
	@Transactional
	public long countCitys() {
		return cityDAO.count();
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@Transactional
	public City deleteCityCountryCode(Integer city_idCity, String related_countrycode_codeCountry) {
		City city = cityDAO.findByIdCity(city_idCity);
		Country related_countrycode = countryDAO.findByCodeCountry(related_countrycode_codeCountry);

		city.setCountryCode(null);
		related_countrycode.setCityCollection(null);
		city = cityDAO.save(city);
		cityDAO.flush();

		related_countrycode = countryDAO.save(related_countrycode);
		countryDAO.flush();

		countryDAO.delete(related_countrycode);
		countryDAO.flush();

		return city;
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@Transactional
	public City saveCityCountryCode(Integer idCity, Country related_countrycode) {
		City city = cityDAO.findByIdCity(idCity);
		Country existingcountryCode = countryDAO.findByCodeCountry(related_countrycode.getCodeCountry());

		// copy into the existing record to preserve existing relationships
		if (existingcountryCode != null) {
			existingcountryCode.setCodeCountry(related_countrycode.getCodeCountry());
			existingcountryCode.setCountryName(related_countrycode.getCountryName());
			existingcountryCode.setCountryContinent(related_countrycode.getCountryContinent());
			related_countrycode = existingcountryCode;
		} else {
			related_countrycode = countryDAO.save(related_countrycode);
			countryDAO.flush();
		}

		city.setCountryCode(related_countrycode);
		related_countrycode.setCityCollection((Collection<City>) city); 
		city = cityDAO.save(city);
		cityDAO.flush();

		related_countrycode = countryDAO.save(related_countrycode);
		countryDAO.flush();

		return city;
	}

	/**
	 * Delete an existing City entity
	 * 
	 */
	@Transactional
	public void deleteCity(City city) {
		cityDAO.delete(city);
		cityDAO.flush();
	}

	/**
	 * Load an existing City entity
	 * 
	 */
	@Transactional
	public List<City> loadCitys() {
		return cityDAO.findAll();
	}


	@Transactional
	public Page<City> findAllCities(int idx, int elements,
			String dir) {
		 return cityDAO.findAll(constructPageSpecification(idx,elements,dir));
	}
	
	private Pageable constructPageSpecification(int pageIndex, int elements,String dir) {
		Sort srt;
		if(dir=="ASC")
			srt= new Sort(Sort.Direction.ASC, "cityName");
		else
			srt= new Sort(Sort.Direction.DESC, "cityName");
        Pageable pageSpecification = new PageRequest(pageIndex, elements, srt );
        return pageSpecification;
    }


	
}
