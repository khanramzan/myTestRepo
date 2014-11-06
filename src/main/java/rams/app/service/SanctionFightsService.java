package rams.app.service;

import java.lang.Long;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rams.app.domain.Fight;
import rams.app.domain.FightTypes;
import rams.app.domain.SanctionFights;
import rams.app.domain.Sanctioner;

/**
 * Spring service that handles CRUD requests for SanctionFights entities
 * 
 */
public interface SanctionFightsService {

	/**
	 * Save an existing FightTypes entity
	 * 
	 */
	public SanctionFights saveSanctionFightsFightType(Long idSanctionfights, FightTypes related_fighttype);

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public SanctionFights saveSanctionFightsFightCollection(Long idSanctionfights_1, Fight related_fightcollection);

	/**
	 * Delete an existing FightTypes entity
	 * 
	 */
	public SanctionFights deleteSanctionFightsFightType(Long sanctionfights_idSanctionfights, Long related_fighttype_idFightTypes);

	/**
	 * Return a count of all SanctionFights entity
	 * 
	 */
	public Long countSanctionFightss();

	/**
	 * Load an existing SanctionFights entity
	 * 
	 */
	public List<SanctionFights> loadSanctionFightss();

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	public SanctionFights deleteSanctionFightsSanctioner(Long sanctionfights_idSanctionfights_1, Long related_sanctioner_idSanctioner);

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public SanctionFights deleteSanctionFightsFightCollection(Long sanctionfights_idSanctionfights_2, Long related_fightcollection_idFight);

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	public SanctionFights saveSanctionFightsSanctioner(Long idSanctionfights_2, Sanctioner related_sanctioner);

	/**
	 * Return all SanctionFights entity
	 * 
	 */
	public Page<SanctionFights> findAllSanctionFightss(int idx, int elements, String dir );

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	public void saveSanctionFights(SanctionFights sanctionfights);

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	public void deleteSanctionFights(SanctionFights sanctionfights_1);

	/**
	 */
	public SanctionFights findSanctionFightsByPrimaryKey(Long idSanctionfights_3);
}