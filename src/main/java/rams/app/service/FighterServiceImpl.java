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









import rams.app.domain.Country;
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.FighterRepository;
import rams.app.rerpository.MediaRelationRepository;

/**
 * Spring service that handles CRUD requests for Fighter entities
 * 
 */

@Service("FighterService")
@Transactional
public class FighterServiceImpl implements FighterService {

	/**
	 * DAO injected by Spring that manages Country entities
	 * 
	 */
	@Autowired
	private CountryRepository countryDAO;

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
	 * Instantiates a new FighterServiceImpl.
	 *
	 */
	public FighterServiceImpl() {
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@Transactional
	public void saveFighter(Fighter fighter) {
		Fighter existingFighter = fighterDAO.findByidFighter(fighter.getIdFighter());

		if (existingFighter != null) {
			if (existingFighter != fighter) {
				existingFighter.setIdFighter(fighter.getIdFighter());
				existingFighter.setFighterName(fighter.getFighterName());
				existingFighter.setFighterClub(fighter.getFighterClub());
				existingFighter.setFighterProfilePicPath(fighter.getFighterProfilePicPath());
				existingFighter.setFightComments(fighter.getFightComments());
			}
			fighter = fighterDAO.save(existingFighter);
		} else {
			fighter = fighterDAO.save(fighter);
		}
		fighterDAO.flush();
	}

	/**
	 * Return a count of all Fighter entity
	 * 
	 */
	@Transactional
	public Long countFighters() {
		return fighterDAO.count();
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public Fighter deleteFighterFightCollection1(Long fighter_idFighter, Long related_fightcollection1_idFight) {
		Fight related_fightcollection1 = fightDAO.findByidFight(related_fightcollection1_idFight);

		Fighter fighter = fighterDAO.findByidFighter(fighter_idFighter);

		related_fightcollection1.setFighterNo1(null);
		fighter.setFightCollection1(null);

		fightDAO.delete(related_fightcollection1);
		fightDAO.flush();

		return fighter;
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@Transactional
	public void deleteFighter(Fighter fighter) {
		fighterDAO.delete(fighter);
		fighterDAO.flush();
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@Transactional
	public Fighter deleteFighterFightCountry(Long fighter_idFighter, String related_fightcountry_codeCountry) {
		Fighter fighter = fighterDAO.findByidFighter(fighter_idFighter);
		Country related_fightcountry = countryDAO.findByCodeCountry(related_fightcountry_codeCountry);

		fighter.setFightCountry(null);
		related_fightcountry.setFighterCollection(null);
		fighter = fighterDAO.save(fighter);
		fighterDAO.flush();

		related_fightcountry = countryDAO.save(related_fightcountry);
		countryDAO.flush();

		countryDAO.delete(related_fightcountry);
		countryDAO.flush();

		return fighter;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public Fighter saveFighterFightCollection1(Long idFighter, Fight related_fightcollection1) {
		Fighter fighter = fighterDAO.findByidFighter(idFighter);
		Fight existingfightCollection1 = fightDAO.findByidFight(related_fightcollection1.getIdFight());

		// copy into the existing record to preserve existing relationships
		if (existingfightCollection1 != null) {
			existingfightCollection1.setIdFight(related_fightcollection1.getIdFight());
			existingfightCollection1.setFightComments(related_fightcollection1.getFightComments());
			related_fightcollection1 = existingfightCollection1;
		} else {
			related_fightcollection1 = fightDAO.save(related_fightcollection1);
			fightDAO.flush();
		}

		related_fightcollection1.setFighterNo1(fighter);
		fighter.setFightCollection1((Collection<Fight>) related_fightcollection1);
		related_fightcollection1 = fightDAO.save(related_fightcollection1);
		fightDAO.flush();

		fighter = fighterDAO.save(fighter);
		fighterDAO.flush();

		return fighter;
	}

	/**
	 * Load an existing Fighter entity
	 * 
	 */
	@Transactional
	public List<Fighter> loadFighters() {
		return  fighterDAO.findAll();
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public Fighter deleteFighterFightCollection(Long fighter_idFighter, Long related_fightcollection_idFight) {
		Fight related_fightcollection = fightDAO.findByidFight(related_fightcollection_idFight);

		Fighter fighter = fighterDAO.findByidFighter(fighter_idFighter);

		related_fightcollection.setFighterNo2(null);
		fighter.setFightCollection(null);

		fightDAO.delete(related_fightcollection);
		fightDAO.flush();

		return fighter;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public Fighter saveFighterFightCollection(Long idFighter, Fight related_fightcollection) {
		Fighter fighter = fighterDAO.findByidFighter(idFighter);
		Fight existingfightCollection = fightDAO.findByidFight(related_fightcollection.getIdFight());

		// copy into the existing record to preserve existing relationships
		if (existingfightCollection != null) {
			existingfightCollection.setIdFight(related_fightcollection.getIdFight());
			existingfightCollection.setFightComments(related_fightcollection.getFightComments());
			related_fightcollection = existingfightCollection;
		} else {
			related_fightcollection = fightDAO.save(related_fightcollection);
			fightDAO.flush();
		}

		related_fightcollection.setFighterNo2(fighter);
		fighter.setFightCollection((Collection<Fight>) related_fightcollection);
		related_fightcollection = fightDAO.save(related_fightcollection);
		fightDAO.flush();

		fighter = fighterDAO.save(fighter);
		fighterDAO.flush();

		return fighter;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Fighter deleteFighterMediaRelationCollection(Long fighter_idFighter, Long related_mediarelationcollection_idMediaRelation) {
		MediaRelation related_mediarelationcollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation);

		Fighter fighter = fighterDAO.findByidFighter(fighter_idFighter);

		related_mediarelationcollection.setFighterMedia(null);
		fighter.setMediaRelationCollection(null);

		mediaRelationDAO.delete(related_mediarelationcollection);
		mediaRelationDAO.flush();

		return fighter;
	}

	/**
	 */
	@Transactional
	public Fighter findFighterByPrimaryKey(Long idFighter) {
		return fighterDAO.findByidFighter(idFighter);
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Fighter saveFighterMediaRelationCollection(Long idFighter, MediaRelation related_mediarelationcollection) {
		Fighter fighter = fighterDAO.findByidFighter(idFighter);
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

		related_mediarelationcollection.setFighterMedia(fighter);
		fighter.setMediaRelationCollection((Collection<MediaRelation>) related_mediarelationcollection);
		related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
		mediaRelationDAO.flush();

		fighter = fighterDAO.save(fighter);
		fighterDAO.flush();

		return fighter;
	}

	/**
	 * Return all Fighter entity
	 * 
	 */
	@Transactional
	public Page<Fighter> findAllFighters(int idx, int elements, String dir) {
		// TODO Auto-generated method stub
		return fighterDAO.findAll(constructPageSpecification(idx, elements, dir));
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@Transactional
	public Fighter saveFighterFightCountry(Long idFighter, Country related_fightcountry) {
		Fighter fighter = fighterDAO.findByidFighter(idFighter);
		Country existingfightCountry = countryDAO.findByCodeCountry(related_fightcountry.getCodeCountry());

		// copy into the existing record to preserve existing relationships
		if (existingfightCountry != null) {
			existingfightCountry.setCodeCountry(related_fightcountry.getCodeCountry());
			existingfightCountry.setCountryName(related_fightcountry.getCountryName());
			existingfightCountry.setCountryContinent(related_fightcountry.getCountryContinent());
			related_fightcountry = existingfightCountry;
		} else {
			related_fightcountry = countryDAO.save(related_fightcountry);
			countryDAO.flush();
		}

		fighter.setFightCountry(related_fightcountry);
		related_fightcountry.setFighterCollection((Collection<Fighter>) fighter);
		fighter = fighterDAO.save(fighter);
		fighterDAO.flush();

		related_fightcountry = countryDAO.save(related_fightcountry);
		countryDAO.flush();

		return fighter;
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
