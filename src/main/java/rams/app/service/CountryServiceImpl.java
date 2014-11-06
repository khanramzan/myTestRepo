package rams.app.service;

import java.lang.Long;
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






import rams.app.domain.City;
import rams.app.domain.Country;
import rams.app.domain.EventTable;
import rams.app.domain.Fighter;
import rams.app.domain.Promoter;
import rams.app.domain.Sanctioner;
import rams.app.rerpository.CityRepository;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.FighterRepository;
import rams.app.rerpository.PromoterRepository;
import rams.app.rerpository.SanctionerRepository;

/**
 * Spring service that handles CRUD requests for Country entities
 * 
 */

@Service("CountryService")
@Transactional
public class CountryServiceImpl implements CountryService {

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
	 * DAO injected by Spring that manages EventTable entities
	 * 
	 */
	@Autowired
	private EventTableRepository eventTableDAO;

	/**
	 * DAO injected by Spring that manages Fighter entities
	 * 
	 */
	@Autowired
	private FighterRepository fighterDAO;

	/**
	 * DAO injected by Spring that manages Promoter entities
	 * 
	 */
	@Autowired
	private PromoterRepository promoterDAO;

	/**
	 * DAO injected by Spring that manages Sanctioner entities
	 * 
	 */
	@Autowired
	private SanctionerRepository sanctionerDAO;

	/**
	 * Instantiates a new CountryServiceImpl.
	 *
	 */
	public CountryServiceImpl() {
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@Transactional
	public Country saveCountryPromoterCollection(String codeCountry, Promoter related_promotercollection) {
		Country country = countryDAO.findByCodeCountry(codeCountry);
		Promoter existingpromoterCollection = promoterDAO.findByIdPromoter(related_promotercollection.getIdPromoter());

		// copy into the existing record to preserve existing relationships
		if (existingpromoterCollection != null) {
			existingpromoterCollection.setIdPromoter(related_promotercollection.getIdPromoter());
			existingpromoterCollection.setPromoterName(related_promotercollection.getPromoterName());
			existingpromoterCollection.setPromoterAddress(related_promotercollection.getPromoterAddress());
			existingpromoterCollection.setPromoterPosterPath(related_promotercollection.getPromoterPosterPath());
			existingpromoterCollection.setPromoterComments(related_promotercollection.getPromoterComments());
			related_promotercollection = existingpromoterCollection;
		} else {
			related_promotercollection = promoterDAO.save(related_promotercollection);
			promoterDAO.flush();
		}

		related_promotercollection.setPromoterCountry(country);
		country.setPromoterCollection((Collection<Promoter>) related_promotercollection);
		related_promotercollection = promoterDAO.save(related_promotercollection);
		promoterDAO.flush();

		country = countryDAO.save(country);
		countryDAO.flush();

		return country;
	}

	/**
	 * Return all Country entity
	 * 
	 */
	

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@Transactional
	public Country saveCountryEventTableCollection(String codeCountry, EventTable related_eventtablecollection) {
		Country country = countryDAO.findByCodeCountry(codeCountry);
		EventTable existingeventTableCollection = eventTableDAO.findByIdEvent(related_eventtablecollection.getIdEvent());

		// copy into the existing record to preserve existing relationships
		if (existingeventTableCollection != null) {
			existingeventTableCollection.setIdEvent(related_eventtablecollection.getIdEvent());
			existingeventTableCollection.setEventName(related_eventtablecollection.getEventName());
			existingeventTableCollection.setEventDate(related_eventtablecollection.getEventDate());
			existingeventTableCollection.setEventPosterPath(related_eventtablecollection.getEventPosterPath());
			existingeventTableCollection.setEventComments(related_eventtablecollection.getEventComments());
			related_eventtablecollection = existingeventTableCollection;
		} else {
			related_eventtablecollection = eventTableDAO.save(related_eventtablecollection);
			eventTableDAO.flush();
		}

		related_eventtablecollection.setEventCountry(country);
		country.setEventTableCollection((Collection<EventTable>) related_eventtablecollection);
		related_eventtablecollection = eventTableDAO.save(related_eventtablecollection);
		eventTableDAO.flush();

		country = countryDAO.save(country);
		countryDAO.flush();

		return country;
	}

	/**
	 * Return a count of all Country entity
	 * 
	 */
	@Transactional
	public Long countCountrys() {
		return  countryDAO.count();
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public Country deleteCountrySanctionerCollection(String country_codeCountry, Long related_sanctionercollection_idSanctioner) {
		Sanctioner related_sanctionercollection = sanctionerDAO.findByIdSanctioner(related_sanctionercollection_idSanctioner);

		Country country = countryDAO.findByCodeCountry(country_codeCountry);

		related_sanctionercollection.setSanctionerCountry(null);
		country.setSanctionerCollection(null);

		sanctionerDAO.delete(related_sanctionercollection);
		sanctionerDAO.flush();

		return country;
	}

	/**
	 * Load an existing Country entity
	 * 
	 */
	@Transactional
	public List<Country> loadCountrys() {
		return countryDAO.findAll();
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@Transactional
	public Country deleteCountryPromoterCollection(String country_codeCountry, Long related_promotercollection_idPromoter) {
		Promoter related_promotercollection = promoterDAO.findByIdPromoter(related_promotercollection_idPromoter);

		Country country = countryDAO.findByCodeCountry(country_codeCountry);

		related_promotercollection.setPromoterCountry(null);
		country.setPromoterCollection(null);

		promoterDAO.delete(related_promotercollection);
		promoterDAO.flush();

		return country;
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@Transactional
	public void saveCountry(Country country) {
		Country existingCountry = countryDAO.findByCodeCountry(country.getCodeCountry());

		if (existingCountry != null) {
			if (existingCountry != country) {
				existingCountry.setCodeCountry(country.getCodeCountry());
				existingCountry.setCountryName(country.getCountryName());
				existingCountry.setCountryContinent(country.getCountryContinent());
			}
			country = countryDAO.save(existingCountry);
		} else {
			country = countryDAO.save(country);
		}
		countryDAO.flush();
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@Transactional
	public void deleteCountry(Country country) {
		countryDAO.delete(country);
		countryDAO.flush();
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@Transactional
	public Country saveCountryFighterCollection(String codeCountry, Fighter related_fightercollection) {
		Country country = countryDAO.findByCodeCountry(codeCountry);
		Fighter existingfighterCollection = fighterDAO.findByidFighter(related_fightercollection.getIdFighter());

		// copy into the existing record to preserve existing relationships
		if (existingfighterCollection != null) {
			existingfighterCollection.setIdFighter(related_fightercollection.getIdFighter());
			existingfighterCollection.setFighterName(related_fightercollection.getFighterName());
			existingfighterCollection.setFighterClub(related_fightercollection.getFighterClub());
			existingfighterCollection.setFighterProfilePicPath(related_fightercollection.getFighterProfilePicPath());
			existingfighterCollection.setFightComments(related_fightercollection.getFightComments());
			related_fightercollection = existingfighterCollection;
		} else {
			related_fightercollection = fighterDAO.save(related_fightercollection);
			fighterDAO.flush();
		}

		related_fightercollection.setFightCountry(country);
		country.setFighterCollection((Collection<Fighter>) related_fightercollection);
		related_fightercollection = fighterDAO.save(related_fightercollection);
		fighterDAO.flush();

		country = countryDAO.save(country);
		countryDAO.flush();

		return country;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@Transactional
	public Country deleteCountryEventTableCollection(String country_codeCountry, Long related_eventtablecollection_idEvent) {
		EventTable related_eventtablecollection = eventTableDAO.findByIdEvent(related_eventtablecollection_idEvent);

		Country country = countryDAO.findByCodeCountry(country_codeCountry);

		related_eventtablecollection.setEventCountry(null);
		country.setEventTableCollection(null);

		eventTableDAO.delete(related_eventtablecollection);
		eventTableDAO.flush();

		return country;
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public Country saveCountrySanctionerCollection(String codeCountry, Sanctioner related_sanctionercollection) {
		Country country = countryDAO.findByCodeCountry(codeCountry);
		Sanctioner existingsanctionerCollection = sanctionerDAO.findByIdSanctioner(related_sanctionercollection.getIdSanctioner());

		// copy into the existing record to preserve existing relationships
		if (existingsanctionerCollection != null) {
			existingsanctionerCollection.setIdSanctioner(related_sanctionercollection.getIdSanctioner());
			existingsanctionerCollection.setSanctionerName(related_sanctionercollection.getSanctionerName());
			existingsanctionerCollection.setSanctionerAddress(related_sanctionercollection.getSanctionerAddress());
			existingsanctionerCollection.setSanctionerLogoPath(related_sanctionercollection.getSanctionerLogoPath());
			existingsanctionerCollection.setSanctionerComments(related_sanctionercollection.getSanctionerComments());
			related_sanctionercollection = existingsanctionerCollection;
		} else {
			related_sanctionercollection = sanctionerDAO.save(related_sanctionercollection);
			sanctionerDAO.flush();
		}

		related_sanctionercollection.setSanctionerCountry(country);
		country.setSanctionerCollection((Collection<Sanctioner>) related_sanctionercollection);
		related_sanctionercollection = sanctionerDAO.save(related_sanctionercollection);
		sanctionerDAO.flush();

		country = countryDAO.save(country);
		countryDAO.flush();

		return country;
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@Transactional
	public Country deleteCountryFighterCollection(String country_codeCountry, Long related_fightercollection_idFighter) {
		Fighter related_fightercollection = fighterDAO.findByidFighter(related_fightercollection_idFighter);

		Country country = countryDAO.findByCodeCountry(country_codeCountry);

		related_fightercollection.setFightCountry(null);
		country.setFighterCollection(null);

		fighterDAO.save(related_fightercollection);
		fighterDAO.flush();

		return country;
	}

	/**
	 * Save an existing City entity
	 * 
	 */
	@Transactional
	public Country saveCountryCityCollection(String codeCountry, City related_citycollection) {
		Country country = countryDAO.findByCodeCountry(codeCountry);
		City existingcityCollection = cityDAO.findByIdCity(related_citycollection.getIdCity());

		// copy into the existing record to preserve existing relationships
		if (existingcityCollection != null) {
			existingcityCollection.setIdCity(related_citycollection.getIdCity());
			existingcityCollection.setCityName(related_citycollection.getCityName());
			existingcityCollection.setDistrict(related_citycollection.getDistrict());
			related_citycollection = existingcityCollection;
		} else {
			related_citycollection = cityDAO.save(related_citycollection);
			cityDAO.flush();
		}

		related_citycollection.setCountryCode(country);
		country.setCityCollection((Collection<City>) related_citycollection);
		related_citycollection = cityDAO.save(related_citycollection);
		cityDAO.flush();

		country = countryDAO.save(country);
		countryDAO.flush();

		return country;
	}

	/**
	 */
	@Transactional
	public Country findCountryByPrimaryKey(String codeCountry) {
		return countryDAO.findByCodeCountry(codeCountry);
	}

	/**
	 * Delete an existing City entity
	 * 
	 */
	@Transactional
	public Country deleteCountryCityCollection(String country_codeCountry, Integer related_citycollection_idCity) {
		City related_citycollection = cityDAO.findByIdCity(related_citycollection_idCity);

		Country country = countryDAO.findByCodeCountry(country_codeCountry);
		related_citycollection.setCountryCode(null);
		country.setCityCollection(null);

		cityDAO.delete(related_citycollection);
		cityDAO.flush();

		return country;
	}

	@Transactional
	public Page<Country> findAllCountrys(int idx, int elements, String dir ) {
		
		return countryDAO.findAll(constructPageSpecification(idx, elements, dir));
	
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
