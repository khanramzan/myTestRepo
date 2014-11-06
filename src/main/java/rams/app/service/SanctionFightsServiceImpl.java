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

import rams.app.domain.Fight;
import rams.app.domain.FightTypes;
import rams.app.domain.SanctionFights;
import rams.app.domain.Sanctioner;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.FightTypesRepository;
import rams.app.rerpository.SanctionFightsRepository;
import rams.app.rerpository.SanctionerRepository;

/**
 * Spring service that handles CRUD requests for SanctionFights entities
 * 
 */

@Service("SanctionFightsService")
@Transactional
public class SanctionFightsServiceImpl implements SanctionFightsService {

	/**
	 * DAO injected by Spring that manages Fight entities
	 * 
	 */
	@Autowired
	private FightRepository fightDAO;

	/**
	 * DAO injected by Spring that manages FightTypes entities
	 * 
	 */
	@Autowired
	private FightTypesRepository fightTypesDAO;

	/**
	 * DAO injected by Spring that manages SanctionFights entities
	 * 
	 */
	@Autowired
	private SanctionFightsRepository sanctionFightsDAO;

	/**
	 * DAO injected by Spring that manages Sanctioner entities
	 * 
	 */
	@Autowired
	private SanctionerRepository sanctionerDAO;

	/**
	 * Instantiates a new SanctionFightsServiceImpl.
	 *
	 */
	public SanctionFightsServiceImpl() {
	}

	/**
	 * Save an existing FightTypes entity
	 * 
	 */
	@Transactional
	public SanctionFights saveSanctionFightsFightType(Long idSanctionfights, FightTypes related_fighttype) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(idSanctionfights);
		FightTypes existingfightType = fightTypesDAO.findByIdFightTypes(related_fighttype.getIdFightTypes());

		// copy into the existing record to preserve existing relationships
		if (existingfightType != null) {
			existingfightType.setIdFightTypes(related_fighttype.getIdFightTypes());
			existingfightType.setFightType(related_fighttype.getFightType());
			existingfightType.setFightTypeDescription(related_fighttype.getFightTypeDescription());
			related_fighttype = existingfightType;
		} else {
			related_fighttype = fightTypesDAO.save(related_fighttype);
			fightTypesDAO.flush();
		}

		sanctionfights.setFightType(related_fighttype);
		related_fighttype.setSanctionFightsCollection((Collection<SanctionFights>) sanctionfights);
		sanctionfights = sanctionFightsDAO.save(sanctionfights);
		sanctionFightsDAO.flush();

		related_fighttype = fightTypesDAO.save(related_fighttype);
		fightTypesDAO.flush();

		return sanctionfights;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public SanctionFights saveSanctionFightsFightCollection(Long idSanctionfights, Fight related_fightcollection) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(idSanctionfights);
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

		related_fightcollection.setFightSanctionedFor(sanctionfights);
		sanctionfights.setFightCollection((Collection<Fight>) related_fightcollection);
		related_fightcollection = fightDAO.save(related_fightcollection);
		fightDAO.flush();

		sanctionfights = sanctionFightsDAO.save(sanctionfights);
		sanctionFightsDAO.flush();

		return sanctionfights;
	}

	/**
	 * Delete an existing FightTypes entity
	 * 
	 */
	@Transactional
	public SanctionFights deleteSanctionFightsFightType(Long sanctionfights_idSanctionfights, Long related_fighttype_idFightTypes) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(sanctionfights_idSanctionfights);
		FightTypes related_fighttype = fightTypesDAO.findByIdFightTypes(related_fighttype_idFightTypes);

		sanctionfights.setFightType(null);
		related_fighttype.setSanctionFightsCollection(null);
		sanctionfights = sanctionFightsDAO.save(sanctionfights);
		sanctionFightsDAO.flush();

		related_fighttype = fightTypesDAO.save(related_fighttype);
		fightTypesDAO.flush();

		fightTypesDAO.delete(related_fighttype);
		fightTypesDAO.flush();

		return sanctionfights;
	}

	/**
	 * Return a count of all SanctionFights entity
	 * 
	 */
	@Transactional
	public Long countSanctionFightss() {
		return sanctionFightsDAO.count();
	}

	/**
	 * Load an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public List<SanctionFights> loadSanctionFightss() {
		return sanctionFightsDAO.findAll();
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public SanctionFights deleteSanctionFightsSanctioner(Long sanctionfights_idSanctionfights, Long related_sanctioner_idSanctioner) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(sanctionfights_idSanctionfights);
		Sanctioner related_sanctioner = sanctionerDAO.findByIdSanctioner(related_sanctioner_idSanctioner);

		sanctionfights.setSanctioner(null);
		related_sanctioner.setSanctionFightsCollection(null);
		sanctionfights = sanctionFightsDAO.save(sanctionfights);
		sanctionFightsDAO.flush();

		related_sanctioner = sanctionerDAO.save(related_sanctioner);
		sanctionerDAO.flush();

		sanctionerDAO.delete(related_sanctioner);
		sanctionerDAO.flush();

		return sanctionfights;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public SanctionFights deleteSanctionFightsFightCollection(Long sanctionfights_idSanctionfights, Long related_fightcollection_idFight) {
		Fight related_fightcollection = fightDAO.findByidFight(related_fightcollection_idFight);

		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(sanctionfights_idSanctionfights);

		related_fightcollection.setFightSanctionedFor(null);
		sanctionfights.setFightCollection(null);

		fightDAO.delete(related_fightcollection);
		fightDAO.flush();

		return sanctionfights;
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public SanctionFights saveSanctionFightsSanctioner(Long idSanctionfights, Sanctioner related_sanctioner) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(idSanctionfights);
		Sanctioner existingsanctioner = sanctionerDAO.findByIdSanctioner(related_sanctioner.getIdSanctioner());

		// copy into the existing record to preserve existing relationships
		if (existingsanctioner != null) {
			existingsanctioner.setIdSanctioner(related_sanctioner.getIdSanctioner());
			existingsanctioner.setSanctionerName(related_sanctioner.getSanctionerName());
			existingsanctioner.setSanctionerAddress(related_sanctioner.getSanctionerAddress());
			existingsanctioner.setSanctionerLogoPath(related_sanctioner.getSanctionerLogoPath());
			existingsanctioner.setSanctionerComments(related_sanctioner.getSanctionerComments());
			related_sanctioner = existingsanctioner;
		} else {
			related_sanctioner = sanctionerDAO.save(related_sanctioner);
			sanctionerDAO.flush();
		}

		sanctionfights.setSanctioner(related_sanctioner);
		related_sanctioner.setSanctionFightsCollection((Collection<SanctionFights>) sanctionfights);
		sanctionfights = sanctionFightsDAO.save(sanctionfights);
		sanctionFightsDAO.flush();

		related_sanctioner = sanctionerDAO.save(related_sanctioner);
		sanctionerDAO.flush();

		return sanctionfights;
	}

	/**
	 * Return all SanctionFights entity
	 * 
	 */

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public void saveSanctionFights(SanctionFights sanctionfights) {
		SanctionFights existingSanctionFights = sanctionFightsDAO.findByIdSanctionfights(sanctionfights.getIdSanctionfights());

		if (existingSanctionFights != null) {
			if (existingSanctionFights != sanctionfights) {
				existingSanctionFights.setIdSanctionfights(sanctionfights.getIdSanctionfights());
			}
			sanctionfights = sanctionFightsDAO.save(existingSanctionFights);
		} else {
			sanctionfights = sanctionFightsDAO.save(sanctionfights);
		}
		sanctionFightsDAO.flush();
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public void deleteSanctionFights(SanctionFights sanctionfights) {
		sanctionFightsDAO.delete(sanctionfights);
		sanctionFightsDAO.flush();
	}

	/**
	 */
	@Transactional
	public SanctionFights findSanctionFightsByPrimaryKey(Long idSanctionfights) {
		return sanctionFightsDAO.findByIdSanctionfights(idSanctionfights);
	}

	@Transactional
	public Page<SanctionFights> findAllSanctionFightss(int idx, int elements,
			String dir) {

		return sanctionFightsDAO.findAll(constructPageSpecification(idx, elements, dir));
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
