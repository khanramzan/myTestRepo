package rams.app.service;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

import rams.app.domain.Country;
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;

/**
 * Spring service that handles CRUD requests for Fighter entities
 * 
 */
public interface FighterService {

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	public void saveFighter(Fighter fighter);

	/**
	 * Return a count of all Fighter entity
	 * 
	 */
	public Long countFighters();

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public Fighter deleteFighterFightCollection1(Long fighter_idFighter, Long related_fightcollection1_idFight);

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	public void deleteFighter(Fighter fighter_1);

	/**
	 * Delete an existing Country entity
	 * 
	 */
	public Fighter deleteFighterFightCountry(Long fighter_idFighter_1, String related_fightcountry_codeCountry);

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public Fighter saveFighterFightCollection1(Long idFighter, Fight related_fightcollection1);

	/**
	 * Load an existing Fighter entity
	 * 
	 */
	public List<Fighter> loadFighters();

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public Fighter deleteFighterFightCollection(Long fighter_idFighter_2, Long related_fightcollection_idFight);

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public Fighter saveFighterFightCollection(Long idFighter_1, Fight related_fightcollection);

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public Fighter deleteFighterMediaRelationCollection(Long fighter_idFighter_3, Long related_mediarelationcollection_idMediaRelation);

	/**
	 */
	public Fighter findFighterByPrimaryKey(Long idFighter_2);

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public Fighter saveFighterMediaRelationCollection(Long idFighter_3, MediaRelation related_mediarelationcollection);

	/**
	 * Return all Fighter entity
	 * 
	 */
	public Page<Fighter> findAllFighters(int idx, int elements, String dir );

	/**
	 * Save an existing Country entity
	 * 
	 */
	public Fighter saveFighterFightCountry(Long idFighter_4, Country related_fightcountry);
}