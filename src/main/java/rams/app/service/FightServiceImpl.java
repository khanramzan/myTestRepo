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

import rams.app.domain.EventTable;
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;
import rams.app.domain.ResultTable;
import rams.app.domain.SanctionFights;
import rams.app.domain.WeightCatagory;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.FighterRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.ResultTableRepository;
import rams.app.rerpository.SanctionFightsRepository;
import rams.app.rerpository.WeightCatagoryRepository;

/**
 * Spring service that handles CRUD requests for Fight entities
 * 
 */

@Service("FightService")
@Transactional
public class FightServiceImpl implements FightService {

	/**
	 * DAO injected by Spring that manages EventTable entities
	 * 
	 */
	@Autowired
	private EventTableRepository eventTableDAO;

	/**
	 * DAO injected by Spring that manages Fight entities
	 * 
	 */
	@Autowired
	private FightRepository fightDAO;

	/**
	 * DAO injected by Spring that manages Fighter entities
	 * 
	 */
	@Autowired
	private FighterRepository fighterDAO;

	/**
	 * DAO injected by Spring that manages MediaRelation entities
	 * 
	 */
	@Autowired
	private MediaRelationRepository mediaRelationDAO;

	/**
	 * DAO injected by Spring that manages ResultTable entities
	 * 
	 */
	@Autowired
	private ResultTableRepository resultTableDAO;

	/**
	 * DAO injected by Spring that manages SanctionFights entities
	 * 
	 */
	@Autowired
	private SanctionFightsRepository sanctionFightsDAO;

	/**
	 * DAO injected by Spring that manages WeightCatagory entities
	 * 
	 */
	@Autowired
	private WeightCatagoryRepository weightCatagoryDAO;

	/**
	 * Instantiates a new FightServiceImpl.
	 *
	 */
	public FightServiceImpl() {
	}

	/**
	 * Load an existing Fight entity
	 * 
	 */
	@Transactional
	public List<Fight> loadFights() {
		return fightDAO.findAll();
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@Transactional
	public Fight deleteFightFighterNo2(Long fight_idFight, Long related_fighterno2_idFighter) {
		Fight fight = fightDAO.findByidFight(fight_idFight);
		Fighter related_fighterno2 = fighterDAO.findByidFighter(related_fighterno2_idFighter);

		fight.setFighterNo2(null);
		related_fighterno2.setFightCollection(null);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_fighterno2 = fighterDAO.save(related_fighterno2);
		fighterDAO.flush();

		fighterDAO.delete(related_fighterno2);
		fighterDAO.flush();

		return fight;
	}

	/**
	 * Save an existing WeightCatagory entity
	 * 
	 */
	@Transactional
	public Fight saveFightWeightCatagoryId(Long idFight, WeightCatagory related_weightcatagoryid) {
		Fight fight = fightDAO.findByidFight(idFight);
		WeightCatagory existingweightCatagoryId = weightCatagoryDAO.findByIdWeightCatagory(related_weightcatagoryid.getIdWeightCatagory());

		// copy into the existing record to preserve existing relationships
		if (existingweightCatagoryId != null) {
			existingweightCatagoryId.setIdWeightCatagory(related_weightcatagoryid.getIdWeightCatagory());
			existingweightCatagoryId.setWeightCatagory(related_weightcatagoryid.getWeightCatagory());
			existingweightCatagoryId.setWeightCatagoryComments(related_weightcatagoryid.getWeightCatagoryComments());
			related_weightcatagoryid = existingweightCatagoryId;
		} else {
			related_weightcatagoryid = weightCatagoryDAO.save(related_weightcatagoryid);
			weightCatagoryDAO.flush();
		}

		fight.setWeightCatagoryId(related_weightcatagoryid);
		related_weightcatagoryid.setFightCollection((Collection<Fight>) fight);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_weightcatagoryid = weightCatagoryDAO.save(related_weightcatagoryid);
		weightCatagoryDAO.flush();

		return fight;
	}

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	@Transactional
	public Fight deleteFightResultTable(Long fight_idFight, Long related_resulttable_idResult) {
		ResultTable related_resulttable = resultTableDAO.findByIdResult(related_resulttable_idResult);

		Fight fight = fightDAO.findByidFight(fight_idFight);

		related_resulttable.setFight(null);
		fight.setResultTable(null);

		resultTableDAO.delete(related_resulttable);
		resultTableDAO.flush();

		return fight;
	}

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	@Transactional
	public Fight saveFightResultTable(Long idFight, ResultTable related_resulttable) {
		Fight fight = fightDAO.findByidFight(idFight);
		ResultTable existingresultTable = resultTableDAO.findByIdResult(related_resulttable.getIdResult());

		// copy into the existing record to preserve existing relationships
		if (existingresultTable != null) {
			existingresultTable.setIdResult(related_resulttable.getIdResult());
			existingresultTable.setAnnouncedResult(related_resulttable.getAnnouncedResult());
			existingresultTable.setResultComment(related_resulttable.getResultComment());
			related_resulttable = existingresultTable;
		} else {
			related_resulttable = resultTableDAO.save(related_resulttable);
			resultTableDAO.flush();
		}

		related_resulttable.setFight(fight);
		fight.setResultTable(related_resulttable);
		related_resulttable = resultTableDAO.save(related_resulttable);
		resultTableDAO.flush();

		fight = fightDAO.save(fight);
		fightDAO.flush();

		return fight;
	}

	/**
	 * Return a count of all Fight entity
	 * 
	 */
	@Transactional
	public Long countFights() {
		return fightDAO.count();
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@Transactional
	public Fight saveFightFighterNo2(Long idFight, Fighter related_fighterno2) {
		Fight fight = fightDAO.findByidFight(idFight);
		Fighter existingfighterNo2 = fighterDAO.findByidFighter(related_fighterno2.getIdFighter());

		// copy into the existing record to preserve existing relationships
		if (existingfighterNo2 != null) {
			existingfighterNo2.setIdFighter(related_fighterno2.getIdFighter());
			existingfighterNo2.setFighterName(related_fighterno2.getFighterName());
			existingfighterNo2.setFighterClub(related_fighterno2.getFighterClub());
			existingfighterNo2.setFighterProfilePicPath(related_fighterno2.getFighterProfilePicPath());
			existingfighterNo2.setFightComments(related_fighterno2.getFightComments());
			related_fighterno2 = existingfighterNo2;
		} else {
			related_fighterno2 = fighterDAO.save(related_fighterno2);
			fighterDAO.flush();
		}

		fight.setFighterNo2(related_fighterno2);
		related_fighterno2.setFightCollection((Collection<Fight>) fight);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_fighterno2 = fighterDAO.save(related_fighterno2);
		fighterDAO.flush();

		return fight;
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@Transactional
	public Fight saveFightFighterNo1(Long idFight, Fighter related_fighterno1) {
		Fight fight = fightDAO.findByidFight(idFight);
		Fighter existingfighterNo1 = fighterDAO.findByidFighter(related_fighterno1.getIdFighter());

		// copy into the existing record to preserve existing relationships
		if (existingfighterNo1 != null) {
			existingfighterNo1.setIdFighter(related_fighterno1.getIdFighter());
			existingfighterNo1.setFighterName(related_fighterno1.getFighterName());
			existingfighterNo1.setFighterClub(related_fighterno1.getFighterClub());
			existingfighterNo1.setFighterProfilePicPath(related_fighterno1.getFighterProfilePicPath());
			existingfighterNo1.setFightComments(related_fighterno1.getFightComments());
			related_fighterno1 = existingfighterNo1;
		} else {
			related_fighterno1 = fighterDAO.save(related_fighterno1);
			fighterDAO.flush();
		}

		fight.setFighterNo1(related_fighterno1);
		related_fighterno1.setFightCollection1((Collection<Fight>) fight);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_fighterno1 = fighterDAO.save(related_fighterno1);
		fighterDAO.flush();

		return fight;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Fight saveFightMediaRelationCollection(Long idFight, MediaRelation related_mediarelationcollection) {
		Fight fight = fightDAO.findByidFight(idFight);
		MediaRelation existingmediaRelationCollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection.getIdMediaRelation());

		// copy into the existing record to preserve existing relationships
		if (existingmediaRelationCollection != null) {
			existingmediaRelationCollection.setIdMediaRelation(related_mediarelationcollection.getIdMediaRelation());
			existingmediaRelationCollection.setMediaComments(related_mediarelationcollection.getMediaComments());
			related_mediarelationcollection = existingmediaRelationCollection;
		} else {
			related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
			mediaRelationDAO.flush();
		}

		related_mediarelationcollection.setFightMedia(fight);
		fight.setMediaRelationCollection((Collection<MediaRelation>) related_mediarelationcollection);
		related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
		mediaRelationDAO.flush();

		fight = fightDAO.save(fight);
		fightDAO.flush();

		return fight;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public void saveFight(Fight fight) {
		Fight existingFight = fightDAO.findByidFight(fight.getIdFight());

		if (existingFight != null) {
			if (existingFight != fight) {
				existingFight.setIdFight(fight.getIdFight());
				existingFight.setFightComments(fight.getFightComments());
			}
			fight = fightDAO.save(existingFight);
		} else {
			fight = fightDAO.save(fight);
		}
		fightDAO.flush();
	}

	/**
	 * Delete an existing WeightCatagory entity
	 * 
	 */
	@Transactional
	public Fight deleteFightWeightCatagoryId(Long fight_idFight, Long related_weightcatagoryid_idWeightCatagory) {
		Fight fight = fightDAO.findByidFight(fight_idFight);
		WeightCatagory related_weightcatagoryid = weightCatagoryDAO.findByIdWeightCatagory(related_weightcatagoryid_idWeightCatagory);
		
		fight.setWeightCatagoryId(null);
		related_weightcatagoryid.setFightCollection(null);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_weightcatagoryid = weightCatagoryDAO.save(related_weightcatagoryid);
		weightCatagoryDAO.flush();

		weightCatagoryDAO.delete(related_weightcatagoryid);
		weightCatagoryDAO.flush();

		return fight;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@Transactional
	public Fight saveFightEventId(Long idFight, EventTable related_eventid) {
		Fight fight = fightDAO.findByidFight(idFight);
		EventTable existingeventId = eventTableDAO.findByIdEvent(related_eventid.getIdEvent());

		// copy into the existing record to preserve existing relationships
		if (existingeventId != null) {
			existingeventId.setIdEvent(related_eventid.getIdEvent());
			existingeventId.setEventName(related_eventid.getEventName());
			existingeventId.setEventDate(related_eventid.getEventDate());
			existingeventId.setEventPosterPath(related_eventid.getEventPosterPath());
			existingeventId.setEventComments(related_eventid.getEventComments());
			related_eventid = existingeventId;
		} else {
			related_eventid = eventTableDAO.save(related_eventid);
			eventTableDAO.flush();
		}

		fight.setEventId(related_eventid);
		related_eventid.setFightCollection((Collection<Fight>) fight);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_eventid = eventTableDAO.save(related_eventid);
		eventTableDAO.flush();

		return fight;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@Transactional
	public Fight deleteFightEventId(Long fight_idFight, Long related_eventid_idEvent) {
		Fight fight = fightDAO.findByidFight(fight_idFight);
		EventTable related_eventid = eventTableDAO.findByIdEvent(related_eventid_idEvent);

		fight.setEventId(null);
		related_eventid.setFightCollection(null);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_eventid = eventTableDAO.save(related_eventid);
		eventTableDAO.flush();

		eventTableDAO.delete(related_eventid);
		eventTableDAO.flush();

		return fight;
	}

	/**
	 */
	@Transactional
	public Fight findFightByPrimaryKey(Long idFight) {
		return fightDAO.findByidFight(idFight);
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public void deleteFight(Fight fight) {
		fightDAO.delete(fight);
		fightDAO.flush();
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Fight deleteFightMediaRelationCollection(Long fight_idFight, Long related_mediarelationcollection_idMediaRelation) {
		MediaRelation related_mediarelationcollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation);

		Fight fight = fightDAO.findByidFight(fight_idFight);

		related_mediarelationcollection.setFightMedia(null);
		fight.setMediaRelationCollection(null);

		mediaRelationDAO.delete(related_mediarelationcollection);
		mediaRelationDAO.flush();

		return fight;
	}

	/**
	 * Return all Fight entity
	 * 
	 */
	
	@Transactional
	public Page<Fight> findAllFights(int idx, int elements, String dir) {
		
		return fightDAO.findAll(constructPageSpecification(idx, elements, dir));
	}
	
	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@Transactional
	public Fight deleteFightFighterNo1(Long fight_idFight, Long related_fighterno1_idFighter) {
		Fight fight = fightDAO.findByidFight(fight_idFight);
		Fighter related_fighterno1 = fighterDAO.findByidFighter(related_fighterno1_idFighter);

		fight.setFighterNo1(null);
		related_fighterno1.setFightCollection1(null);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_fighterno1 = fighterDAO.save(related_fighterno1);
		fighterDAO.flush();

		fighterDAO.delete(related_fighterno1);
		fighterDAO.flush();

		return fight;
	}

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public Fight saveFightFightSanctionedFor(Long idFight, SanctionFights related_fightsanctionedfor) {
		Fight fight = fightDAO.findByidFight(idFight);
		SanctionFights existingfightSanctionedFor = sanctionFightsDAO.findByIdSanctionfights(related_fightsanctionedfor.getIdSanctionfights());

		// copy into the existing record to preserve existing relationships
		if (existingfightSanctionedFor != null) {
			existingfightSanctionedFor.setIdSanctionfights(related_fightsanctionedfor.getIdSanctionfights());
			related_fightsanctionedfor = existingfightSanctionedFor;
		} else {
			related_fightsanctionedfor = sanctionFightsDAO.save(related_fightsanctionedfor);
			sanctionFightsDAO.flush();
		}

		fight.setFightSanctionedFor(related_fightsanctionedfor);
		related_fightsanctionedfor.setFightCollection((Collection<Fight>) fight);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_fightsanctionedfor = sanctionFightsDAO.save(related_fightsanctionedfor);
		sanctionFightsDAO.flush();

		return fight;
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public Fight deleteFightFightSanctionedFor(Long fight_idFight, Long related_fightsanctionedfor_idSanctionfights) {
		Fight fight = fightDAO.findByidFight(fight_idFight);
		SanctionFights related_fightsanctionedfor = sanctionFightsDAO.findByIdSanctionfights(related_fightsanctionedfor_idSanctionfights);

		fight.setFightSanctionedFor(null);
		related_fightsanctionedfor.setFightCollection(null);
		fight = fightDAO.save(fight);
		fightDAO.flush();

		related_fightsanctionedfor = sanctionFightsDAO.save(related_fightsanctionedfor);
		sanctionFightsDAO.flush();

		sanctionFightsDAO.delete(related_fightsanctionedfor);
		sanctionFightsDAO.flush();

		return fight;
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
