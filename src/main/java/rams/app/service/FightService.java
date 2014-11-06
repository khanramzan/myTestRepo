package rams.app.service;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

import rams.app.domain.EventTable;
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;
import rams.app.domain.ResultTable;
import rams.app.domain.SanctionFights;
import rams.app.domain.WeightCatagory;

/**
 * Spring service that handles CRUD requests for Fight entities
 * 
 */
public interface FightService {

	/**
	 * Load an existing Fight entity
	 * 
	 */
	public List<Fight> loadFights();

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	public Fight deleteFightFighterNo2(Long fight_idFight, Long related_fighterno2_idFighter);

	/**
	 * Save an existing WeightCatagory entity
	 * 
	 */
	public Fight saveFightWeightCatagoryId(Long idFight, WeightCatagory related_weightcatagoryid);

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	public Fight deleteFightResultTable(Long fight_idFight_1, Long related_resulttable_idResult);

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	public Fight saveFightResultTable(Long idFight_1, ResultTable related_resulttable);

	/**
	 * Return a count of all Fight entity
	 * 
	 */
	public Long countFights();

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	public Fight saveFightFighterNo2(Long idFight_2, Fighter related_fighterno2);

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	public Fight saveFightFighterNo1(Long idFight_3, Fighter related_fighterno1);

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public Fight saveFightMediaRelationCollection(Long idFight_4, MediaRelation related_mediarelationcollection);

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public void saveFight(Fight fight);

	/**
	 * Delete an existing WeightCatagory entity
	 * 
	 */
	public Fight deleteFightWeightCatagoryId(Long fight_idFight_2, Long related_weightcatagoryid_idWeightCatagory);

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	public Fight saveFightEventId(Long idFight_5, EventTable related_eventid);

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	public Fight deleteFightEventId(Long fight_idFight_3, Long related_eventid_idEvent);

	/**
	 */
	public Fight findFightByPrimaryKey(Long idFight_6);

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public void deleteFight(Fight fight_1);

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public Fight deleteFightMediaRelationCollection(Long fight_idFight_4, Long related_mediarelationcollection_idMediaRelation);

	/**
	 * Return all Fight entity
	 * 
	 */
	public Page<Fight> findAllFights(int idx, int elements, String dir );

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	public Fight deleteFightFighterNo1(Long fight_idFight_5, Long related_fighterno1_idFighter);

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	public Fight saveFightFightSanctionedFor(Long idFight_7, SanctionFights related_fightsanctionedfor);

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	public Fight deleteFightFightSanctionedFor(Long fight_idFight_6, Long related_fightsanctionedfor_idSanctionfights);
}