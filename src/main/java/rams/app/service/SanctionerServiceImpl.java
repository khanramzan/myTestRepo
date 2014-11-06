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
import rams.app.domain.MediaRelation;
import rams.app.domain.SanctionFights;
import rams.app.domain.Sanctioner;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.SanctionFightsRepository;
import rams.app.rerpository.SanctionerRepository;

/**
 * Spring service that handles CRUD requests for Sanctioner entities
 * 
 */

@Service("SanctionerService")
@Transactional
public class SanctionerServiceImpl implements SanctionerService {

	/**
	 * DAO injected by Spring that manages Country entities
	 * 
	 */
	@Autowired
	private CountryRepository countryDAO;

	/**
	 * DAO injected by Spring that manages MediaRelation entities
	 * 
	 */
	@Autowired
	private MediaRelationRepository mediaRelationDAO;

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
	 * Instantiates a new SanctionerServiceImpl.
	 *
	 */
	public SanctionerServiceImpl() {
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public Sanctioner deleteSanctionerSanctionFightsCollection(Long sanctioner_idSanctioner, Long related_sanctionfightscollection_idSanctionfights) {
		SanctionFights related_sanctionfightscollection = sanctionFightsDAO.findByIdSanctionfights(related_sanctionfightscollection_idSanctionfights);

		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctioner_idSanctioner);

		related_sanctionfightscollection.setSanctioner(null);
		sanctioner.setSanctionFightsCollection(null);

		sanctionFightsDAO.delete(related_sanctionfightscollection);
		sanctionFightsDAO.flush();

		return sanctioner;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Sanctioner saveSanctionerMediaRelationCollection(Long idSanctioner, MediaRelation related_mediarelationcollection) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(idSanctioner);
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

		related_mediarelationcollection.setSanctionerMedia(sanctioner);
		sanctioner.setMediaRelationCollection((Collection<MediaRelation>) related_mediarelationcollection);
		related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
		mediaRelationDAO.flush();

		sanctioner = sanctionerDAO.save(sanctioner);
		sanctionerDAO.flush();

		return sanctioner;
	}

	/**
	 * Return a count of all Sanctioner entity
	 * 
	 */
	@Transactional
	public Long countSanctioners() {
		return sanctionerDAO.count();
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public void saveSanctioner(Sanctioner sanctioner) {
		Sanctioner existingSanctioner = sanctionerDAO.findByIdSanctioner(sanctioner.getIdSanctioner());

		if (existingSanctioner != null) {
			if (existingSanctioner != sanctioner) {
				existingSanctioner.setIdSanctioner(sanctioner.getIdSanctioner());
				existingSanctioner.setSanctionerName(sanctioner.getSanctionerName());
				existingSanctioner.setSanctionerAddress(sanctioner.getSanctionerAddress());
				existingSanctioner.setSanctionerLogoPath(sanctioner.getSanctionerLogoPath());
				existingSanctioner.setSanctionerComments(sanctioner.getSanctionerComments());
			}
			sanctioner = sanctionerDAO.save(existingSanctioner);
		} else {
			sanctioner = sanctionerDAO.save(sanctioner);
		}
		sanctionerDAO.flush();
	}

	/**
	 * Return all Sanctioner entity
	 * 
	 */
	

	/**
	 * Save an existing Country entity
	 * 
	 */
	@Transactional
	public Sanctioner saveSanctionerSanctionerCountry(Long idSanctioner, Country related_sanctionercountry) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(idSanctioner);
		Country existingsanctionerCountry = countryDAO.findByCodeCountry(related_sanctionercountry.getCodeCountry());

		// copy into the existing record to preserve existing relationships
		if (existingsanctionerCountry != null) {
			existingsanctionerCountry.setCodeCountry(related_sanctionercountry.getCodeCountry());
			existingsanctionerCountry.setCountryName(related_sanctionercountry.getCountryName());
			existingsanctionerCountry.setCountryContinent(related_sanctionercountry.getCountryContinent());
			related_sanctionercountry = existingsanctionerCountry;
		} else {
			related_sanctionercountry = countryDAO.save(related_sanctionercountry);
			countryDAO.flush();
		}

		sanctioner.setSanctionerCountry(related_sanctionercountry);
		related_sanctionercountry.setSanctionerCollection((Collection<Sanctioner>) sanctioner);
		sanctioner = sanctionerDAO.save(sanctioner);
		sanctionerDAO.flush();

		related_sanctionercountry = countryDAO.save(related_sanctionercountry);
		countryDAO.flush();

		return sanctioner;
	}

	/**
	 * Load an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public List<Sanctioner> loadSanctioners() {
		return  sanctionerDAO.findAll();
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Sanctioner deleteSanctionerMediaRelationCollection(Long sanctioner_idSanctioner, Long related_mediarelationcollection_idMediaRelation) {
		MediaRelation related_mediarelationcollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation);

		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctioner_idSanctioner);

		related_mediarelationcollection.setSanctionerMedia(null);
		sanctioner.setMediaRelationCollection(null);

		mediaRelationDAO.delete(related_mediarelationcollection);
		mediaRelationDAO.flush();

		return sanctioner;
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@Transactional
	public Sanctioner deleteSanctionerSanctionerCountry(Long sanctioner_idSanctioner, String related_sanctionercountry_codeCountry) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctioner_idSanctioner);
		Country related_sanctionercountry = countryDAO.findByCodeCountry(related_sanctionercountry_codeCountry);

		sanctioner.setSanctionerCountry(null);
		related_sanctionercountry.setSanctionerCollection(null);
		sanctioner = sanctionerDAO.save(sanctioner);
		sanctionerDAO.flush();

		related_sanctionercountry = countryDAO.save(related_sanctionercountry);
		countryDAO.flush();

		countryDAO.delete(related_sanctionercountry);
		countryDAO.flush();

		return sanctioner;
	}

	/**
	 */
	@Transactional
	public Sanctioner findSanctionerByPrimaryKey(Long idSanctioner) {
		return sanctionerDAO.findByIdSanctioner(idSanctioner);
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public void deleteSanctioner(Sanctioner sanctioner) {
		sanctionerDAO.delete(sanctioner);
		sanctionerDAO.flush();
	}

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	@Transactional
	public Sanctioner saveSanctionerSanctionFightsCollection(Long idSanctioner, SanctionFights related_sanctionfightscollection) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(idSanctioner);
		SanctionFights existingsanctionFightsCollection = sanctionFightsDAO.findByIdSanctionfights(related_sanctionfightscollection.getIdSanctionfights());

		// copy into the existing record to preserve existing relationships
		if (existingsanctionFightsCollection != null) {
			existingsanctionFightsCollection.setIdSanctionfights(related_sanctionfightscollection.getIdSanctionfights());
			related_sanctionfightscollection = existingsanctionFightsCollection;
		} else {
			related_sanctionfightscollection = sanctionFightsDAO.save(related_sanctionfightscollection);
			sanctionFightsDAO.flush();
		}

		related_sanctionfightscollection.setSanctioner(sanctioner);
		sanctioner.setSanctionFightsCollection((Collection<SanctionFights>) related_sanctionfightscollection);
		related_sanctionfightscollection = sanctionFightsDAO.save(related_sanctionfightscollection);
		sanctionFightsDAO.flush();

		sanctioner = sanctionerDAO.save(sanctioner);
		sanctionerDAO.flush();

		return sanctioner;
	}

	@Transactional
	public Page<Sanctioner> findAllSanctioners(int idx, int elements, String dir) {
		// TODO Auto-generated method stub
		return sanctionerDAO.findAll(constructPageSpecification(idx, elements, dir));
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
