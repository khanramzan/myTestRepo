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
import rams.app.domain.EventTable;
import rams.app.domain.MediaRelation;
import rams.app.domain.Promoter;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.PromoterRepository;

/**
 * Spring service that handles CRUD requests for Promoter entities
 * 
 */

@Service("PromoterService")
@Transactional
public class PromoterServiceImpl implements PromoterService {

	/**
	 * DAO injected by Spring that manages Country entities
	 * 
	 */
	@Autowired
	private CountryRepository countryDAO;

	/**
	 * DAO injected by Spring that manages EventTable entities
	 * 
	 */
	@Autowired
	private EventTableRepository eventTableDAO;

	/**
	 * DAO injected by Spring that manages MediaRelation entities
	 * 
	 */
	@Autowired
	private MediaRelationRepository mediaRelationDAO;

	/**
	 * DAO injected by Spring that manages Promoter entities
	 * 
	 */
	@Autowired
	private PromoterRepository promoterDAO;

	/**
	 * Instantiates a new PromoterServiceImpl.
	 *
	 */
	public PromoterServiceImpl() {
	}

	/**
	 * Return a count of all Promoter entity
	 * 
	 */
	@Transactional
	public Long countPromoters() {
		return promoterDAO.count();
	}

	/**
	 * Load an existing Promoter entity
	 * 
	 */
	@Transactional
	public List<Promoter> loadPromoters() {
		return promoterDAO.findAll();
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Promoter savePromoterMediaRelationCollection(Long idPromoter, MediaRelation related_mediarelationcollection) {
		Promoter promoter = promoterDAO.findByIdPromoter(idPromoter);
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

		related_mediarelationcollection.setPromoterMedia(promoter);
		promoter.setMediaRelationCollection((Collection<MediaRelation>) related_mediarelationcollection);
		related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
		mediaRelationDAO.flush();

		promoter = promoterDAO.save(promoter);
		promoterDAO.flush();

		return promoter;
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@Transactional
	public Promoter deletePromoterPromoterCountry(Long promoter_idPromoter, String related_promotercountry_codeCountry) {
		Promoter promoter = promoterDAO.findByIdPromoter(promoter_idPromoter);
		Country related_promotercountry = countryDAO.findByCodeCountry(related_promotercountry_codeCountry);

		promoter.setPromoterCountry(null);
		related_promotercountry.setPromoterCollection(null);
		promoter = promoterDAO.save(promoter);
		promoterDAO.flush();

		related_promotercountry = countryDAO.save(related_promotercountry);
		countryDAO.flush();

		countryDAO.delete(related_promotercountry);
		countryDAO.flush();

		return promoter;
	}

	/**
	 */
	@Transactional
	public Promoter findPromoterByPrimaryKey(Long idPromoter) {
		return promoterDAO.findByIdPromoter(idPromoter);
		
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@Transactional
	public void savePromoter(Promoter promoter) {
		Promoter existingPromoter = promoterDAO.findByIdPromoter(promoter.getIdPromoter());

		if (existingPromoter != null) {
			if (existingPromoter != promoter) {
				existingPromoter.setIdPromoter(promoter.getIdPromoter());
				existingPromoter.setPromoterName(promoter.getPromoterName());
				existingPromoter.setPromoterAddress(promoter.getPromoterAddress());
				existingPromoter.setPromoterPosterPath(promoter.getPromoterPosterPath());
				existingPromoter.setPromoterComments(promoter.getPromoterComments());
			}
			promoter = promoterDAO.save(existingPromoter);
		} else {
			promoter = promoterDAO.save(promoter);
		}
		promoterDAO.flush();
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public Promoter deletePromoterMediaRelationCollection(Long promoter_idPromoter, Long related_mediarelationcollection_idMediaRelation) {
		MediaRelation related_mediarelationcollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation);

		Promoter promoter = promoterDAO.findByIdPromoter(promoter_idPromoter);

		related_mediarelationcollection.setPromoterMedia(null);
		promoter.setMediaRelationCollection(null);

		mediaRelationDAO.delete(related_mediarelationcollection);
		mediaRelationDAO.flush();

		return promoter;
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@Transactional
	public void deletePromoter(Promoter promoter) {
		promoterDAO.delete(promoter);
		promoterDAO.flush();
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@Transactional
	public Promoter savePromoterPromoterCountry(Long idPromoter, Country related_promotercountry) {
		Promoter promoter = promoterDAO.findByIdPromoter(idPromoter);
		Country existingpromoterCountry = countryDAO.findByCodeCountry(related_promotercountry.getCodeCountry());

		// copy into the existing record to preserve existing relationships
		if (existingpromoterCountry != null) {
			existingpromoterCountry.setCodeCountry(related_promotercountry.getCodeCountry());
			existingpromoterCountry.setCountryName(related_promotercountry.getCountryName());
			existingpromoterCountry.setCountryContinent(related_promotercountry.getCountryContinent());
			related_promotercountry = existingpromoterCountry;
		} else {
			related_promotercountry = countryDAO.save(related_promotercountry);
			countryDAO.flush();
		}

		promoter.setPromoterCountry(related_promotercountry);
		related_promotercountry.setPromoterCollection((Collection<Promoter>) promoter);
		promoter = promoterDAO.save(promoter);
		promoterDAO.flush();

		related_promotercountry = countryDAO.save(related_promotercountry);
		countryDAO.flush();

		return promoter;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@Transactional
	public Promoter savePromoterEventTableCollection(Long idPromoter, EventTable related_eventtablecollection) {
		Promoter promoter = promoterDAO.findByIdPromoter(idPromoter);
		EventTable existingeventTableCollection = eventTableDAO.findByIdEvent(related_eventtablecollection.getIdEvent());

		// copy into the existing record to preserve existing relationships
		if (existingeventTableCollection != null) {
			existingeventTableCollection.setIdEvent(related_eventtablecollection.getIdEvent());
			existingeventTableCollection.setEventName(related_eventtablecollection.getEventName());
			existingeventTableCollection.setEventDate(related_eventtablecollection.getEventDate());
			existingeventTableCollection.setEventPosterPath(related_eventtablecollection.getEventPosterPath());
			existingeventTableCollection.setEventComments(related_eventtablecollection.getEventComments());
			related_eventtablecollection = existingeventTableCollection;
		} else {
			related_eventtablecollection = eventTableDAO.save(related_eventtablecollection);
			eventTableDAO.flush();
		}

		related_eventtablecollection.setEventPromoter(promoter);
		promoter.setEventTableCollection((Collection<EventTable>) related_eventtablecollection);
		related_eventtablecollection = eventTableDAO.save(related_eventtablecollection);
		eventTableDAO.flush();

		promoter = promoterDAO.save(promoter);
		promoterDAO.flush();

		return promoter;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@Transactional
	public Promoter deletePromoterEventTableCollection(Long promoter_idPromoter, Long related_eventtablecollection_idEvent) {
		EventTable related_eventtablecollection = eventTableDAO.findByIdEvent(related_eventtablecollection_idEvent);

		Promoter promoter = promoterDAO.findByIdPromoter(promoter_idPromoter);

		related_eventtablecollection.setEventPromoter(null);
		promoter.setEventTableCollection(null);

		eventTableDAO.delete(related_eventtablecollection);
		eventTableDAO.flush();

		return promoter;
	}

	/**
	 * Return all Promoter entity
	 * 
	 */
	

	@Transactional
	public Page<Promoter> findAllPromoters(int idx, int elements, String dir) {
		return promoterDAO.findAll(constructPageSpecification(idx, elements, dir));
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
