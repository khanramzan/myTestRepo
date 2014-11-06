package rams.app.service;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

import rams.app.domain.FightTypes;
import rams.app.domain.SanctionFights;

/**
 * Spring service that handles CRUD requests for FightTypes entities
 * 
 */
public interface FightTypesService {

	/**
	 * Return all FightTypes entity
	 * 
	 */
	public Page<FightTypes> findAllFightTypes(int idx, int elements, String dir );

	/**
	 * Return a count of all FightTypes entity
	 * 
	 */
	public Long countFightTypess();

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	public FightTypes saveFightTypesSanctionFightsCollection(Long idFightTypes, SanctionFights related_sanctionfightscollection);

	/**
	 */
	public FightTypes findFightTypesByPrimaryKey(Long idFightTypes_1);

	/**
	 * Save an existing FightTypes entity
	 * 
	 */
	public void saveFightTypes(FightTypes fighttypes);

	/**
	 * Delete an existing FightTypes entity
	 * 
	 */
	public void deleteFightTypes(FightTypes fighttypes_1);

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	public FightTypes deleteFightTypesSanctionFightsCollection(Long fighttypes_idFightTypes, Long related_sanctionfightscollection_idSanctionfights);

	/**
	 * Load an existing FightTypes entity
	 * 
	 */
	public List<FightTypes> loadFightTypess();
}