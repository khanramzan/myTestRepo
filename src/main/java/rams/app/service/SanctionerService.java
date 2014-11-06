package rams.app.service;

import java.lang.Long;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rams.app.domain.Country;
import rams.app.domain.MediaRelation;
import rams.app.domain.SanctionFights;
import rams.app.domain.Sanctioner;

/**
 * Spring service that handles CRUD requests for Sanctioner entities
 * 
 */
public interface SanctionerService {

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	public Sanctioner deleteSanctionerSanctionFightsCollection(Long sanctioner_idSanctioner, Long related_sanctionfightscollection_idSanctionfights);

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public Sanctioner saveSanctionerMediaRelationCollection(Long idSanctioner, MediaRelation related_mediarelationcollection);

	/**
	 * Return a count of all Sanctioner entity
	 * 
	 */
	public Long countSanctioners();

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	public void saveSanctioner(Sanctioner sanctioner);

	/**
	 * Return all Sanctioner entity
	 * 
	 */
	public Page<Sanctioner> findAllSanctioners(int idx, int elements, String dir );

	/**
	 * Save an existing Country entity
	 * 
	 */
	public Sanctioner saveSanctionerSanctionerCountry(Long idSanctioner_1, Country related_sanctionercountry);

	/**
	 * Load an existing Sanctioner entity
	 * 
	 */
	public List<Sanctioner> loadSanctioners();

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public Sanctioner deleteSanctionerMediaRelationCollection(Long sanctioner_idSanctioner_1, Long related_mediarelationcollection_idMediaRelation);

	/**
	 * Delete an existing Country entity
	 * 
	 */
	public Sanctioner deleteSanctionerSanctionerCountry(Long sanctioner_idSanctioner_2, String related_sanctionercountry_codeCountry);

	/**
	 */
	public Sanctioner findSanctionerByPrimaryKey(Long idSanctioner_2);

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	public void deleteSanctioner(Sanctioner sanctioner_1);

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	public Sanctioner saveSanctionerSanctionFightsCollection(Long idSanctioner_3, SanctionFights related_sanctionfightscollection);
}