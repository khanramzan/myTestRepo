package rams.app.service;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

import rams.app.domain.City;
import rams.app.domain.Country;
import rams.app.domain.EventTable;
import rams.app.domain.Fighter;
import rams.app.domain.Promoter;
import rams.app.domain.Sanctioner;

/**
 * Spring service that handles CRUD requests for Country entities
 * 
 */
public interface CountryService {

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	public Country saveCountryPromoterCollection(String codeCountry, Promoter related_promotercollection);

	/**
	 * Return all Country entity
	 * 
	 */
	public Page<Country> findAllCountrys(int idx, int elements, String dir );

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	public Country saveCountryEventTableCollection(String codeCountry_1, EventTable related_eventtablecollection);

	/**
	 * Return a count of all Country entity
	 * 
	 */
	public Long countCountrys();

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	public Country deleteCountrySanctionerCollection(String country_codeCountry, Long related_sanctionercollection_idSanctioner);

	/**
	 * Load an existing Country entity
	 * 
	 */
	public List<Country> loadCountrys();

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	public Country deleteCountryPromoterCollection(String country_codeCountry_1, Long related_promotercollection_idPromoter);

	/**
	 * Save an existing Country entity
	 * 
	 */
	public void saveCountry(Country country);

	/**
	 * Delete an existing Country entity
	 * 
	 */
	public void deleteCountry(Country country_1);

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	public Country saveCountryFighterCollection(String codeCountry_2, Fighter related_fightercollection);

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	public Country deleteCountryEventTableCollection(String country_codeCountry_2, Long related_eventtablecollection_idEvent);

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	public Country saveCountrySanctionerCollection(String codeCountry_3, Sanctioner related_sanctionercollection);

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	public Country deleteCountryFighterCollection(String country_codeCountry_3, Long related_fightercollection_idFighter);

	/**
	 * Save an existing City entity
	 * 
	 */
	public Country saveCountryCityCollection(String codeCountry_4, City related_citycollection);

	/**
	 */
	public Country findCountryByPrimaryKey(String codeCountry_5);

	/**
	 * Delete an existing City entity
	 * 
	 */
	public Country deleteCountryCityCollection(String country_codeCountry_4, Integer related_citycollection_idCity);
}