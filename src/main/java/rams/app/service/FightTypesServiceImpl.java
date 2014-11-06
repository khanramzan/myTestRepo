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




import rams.app.domain.FightTypes;
import rams.app.domain.SanctionFights;
import rams.app.rerpository.FightTypesRepository;
import rams.app.rerpository.SanctionFightsRepository;

/**
 * Spring service that handles CRUD requests for FightTypes entities
 * 
 */

@Service("FightTypesService")
@Transactional
public class FightTypesServiceImpl implements FightTypesService {

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
	 * Instantiates a new FightTypesServiceImpl.
	 *
	 */
	public FightTypesServiceImpl() {
	}

	/**
	 * Return all FightTypes entity
	 * 
	 */
	
	@Transactional
	public Page<FightTypes> findAllFightTypes(int idx, int elements, String dir) {
	
		return fightTypesDAO.findAll(constructPageSpecification(idx, elements, dir));
	}
	
	/**
	 * Return a count of all FightTypes entity
	 * 
	 */
	@Transactional
	public Long countFightTypess() {
		return fightTypesDAO.count();
	}

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public FightTypes saveFightTypesSanctionFightsCollection(Long idFightTypes, SanctionFights related_sanctionfightscollection) {
		FightTypes fighttypes = fightTypesDAO.findByIdFightTypes(idFightTypes);
		SanctionFights existingsanctionFightsCollection = sanctionFightsDAO.findByIdSanctionfights(related_sanctionfightscollection.getIdSanctionfights());

		// copy into the existing record to preserve existing relationships
		if (existingsanctionFightsCollection != null) {
			existingsanctionFightsCollection.setIdSanctionfights(related_sanctionfightscollection.getIdSanctionfights());
			related_sanctionfightscollection = existingsanctionFightsCollection;
		} else {
			related_sanctionfightscollection = sanctionFightsDAO.save(related_sanctionfightscollection);
			sanctionFightsDAO.flush();
		}

		related_sanctionfightscollection.setFightType(fighttypes);
		fighttypes.setSanctionFightsCollection((Collection<SanctionFights>) related_sanctionfightscollection);
		related_sanctionfightscollection = sanctionFightsDAO.save(related_sanctionfightscollection);
		sanctionFightsDAO.flush();

		fighttypes = fightTypesDAO.save(fighttypes);
		fightTypesDAO.flush();

		return fighttypes;
	}

	/**
	 */
	@Transactional
	public FightTypes findFightTypesByPrimaryKey(Long idFightTypes) {
		return fightTypesDAO.findByIdFightTypes(idFightTypes);
	}

	/**
	 * Save an existing FightTypes entity
	 * 
	 */
	@Transactional
	public void saveFightTypes(FightTypes fighttypes) {
		FightTypes existingFightTypes = fightTypesDAO.findByIdFightTypes(fighttypes.getIdFightTypes());

		if (existingFightTypes != null) {
			if (existingFightTypes != fighttypes) {
				existingFightTypes.setIdFightTypes(fighttypes.getIdFightTypes());
				existingFightTypes.setFightType(fighttypes.getFightType());
				existingFightTypes.setFightTypeDescription(fighttypes.getFightTypeDescription());
			}
			fighttypes = fightTypesDAO.save(existingFightTypes);
		} else {
			fighttypes = fightTypesDAO.save(fighttypes);
		}
		fightTypesDAO.flush();
	}

	/**
	 * Delete an existing FightTypes entity
	 * 
	 */
	@Transactional
	public void deleteFightTypes(FightTypes fighttypes) {
		fightTypesDAO.delete(fighttypes);
		fightTypesDAO.flush();
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public FightTypes deleteFightTypesSanctionFightsCollection(Long fighttypes_idFightTypes, Long related_sanctionfightscollection_idSanctionfights) {
		SanctionFights related_sanctionfightscollection = sanctionFightsDAO.findByIdSanctionfights(related_sanctionfightscollection_idSanctionfights);

		FightTypes fighttypes = fightTypesDAO.findByIdFightTypes(fighttypes_idFightTypes);

		related_sanctionfightscollection.setFightType(null);
		fighttypes.setSanctionFightsCollection(null);

		sanctionFightsDAO.delete(related_sanctionfightscollection);
		sanctionFightsDAO.flush();

		return fighttypes;
	}

	/**
	 * Load an existing FightTypes entity
	 * 
	 */
	@Transactional
	public List<FightTypes> loadFightTypess() {
		return fightTypesDAO.findAll();
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
