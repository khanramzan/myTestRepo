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
import rams.app.domain.Fight;
import rams.app.domain.MediaRelation;
import rams.app.domain.Promoter;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.PromoterRepository;

/**
 * Spring service that handles CRUD requests for EventTable entities
 * 
 */

@Service("EventTableService")
@Transactional
public class EventTableServiceImpl implements EventTableService {

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
	 * DAO injected by Spring that manages Fight entities
	 * 
	 */
	@Autowired
	private FightRepository fightDAO;

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
	 * Instantiates a new EventTableServiceImpl.
	 *
	 */
	public EventTableServiceImpl() {
	}

	/**
	 * Return a count of all EventTable entity
	 * 
	 */
	@Transactional
	public Long countEventTables() {
		return eventTableDAO.count();
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public EventTable deleteEventTableFightCollection(Long eventtable_idEvent, Long related_fightcollection_idFight) {
		Fight related_fightcollection = fightDAO.findByidFight(related_fightcollection_idFight);

		EventTable eventtable = eventTableDAO.findByIdEvent(eventtable_idEvent);

		related_fightcollection.setEventId(null);
		eventtable.setFightCollection(null);

		fightDAO.delete(related_fightcollection);
		fightDAO.flush();

		return eventtable;
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@Transactional
	public EventTable deleteEventTableEventPromoter(Long eventtable_idEvent, Long related_eventpromoter_idPromoter) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventtable_idEvent);
		Promoter related_eventpromoter = promoterDAO.findByIdPromoter(related_eventpromoter_idPromoter);

		eventtable.setEventPromoter(null);
		related_eventpromoter.setEventTableCollection(null);
		eventtable = eventTableDAO.save(eventtable);
		eventTableDAO.flush();

		related_eventpromoter = promoterDAO.save(related_eventpromoter);
		promoterDAO.flush();

		promoterDAO.save(related_eventpromoter);
		promoterDAO.flush();

		return eventtable;
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@Transactional
	public EventTable saveEventTableEventPromoter(Long idEvent, Promoter related_eventpromoter) {
		EventTable eventtable = eventTableDAO.findByIdEvent(idEvent);
		Promoter existingeventPromoter = promoterDAO.findByIdPromoter(related_eventpromoter.getIdPromoter());

		// copy into the existing record to preserve existing relationships
		if (existingeventPromoter != null) {
			existingeventPromoter.setIdPromoter(related_eventpromoter.getIdPromoter());
			existingeventPromoter.setPromoterName(related_eventpromoter.getPromoterName());
			existingeventPromoter.setPromoterAddress(related_eventpromoter.getPromoterAddress());
			existingeventPromoter.setPromoterPosterPath(related_eventpromoter.getPromoterPosterPath());
			existingeventPromoter.setPromoterComments(related_eventpromoter.getPromoterComments());
			related_eventpromoter = existingeventPromoter;
		} else {
			related_eventpromoter = promoterDAO.save(related_eventpromoter);
			promoterDAO.flush();
		}

		eventtable.setEventPromoter(related_eventpromoter);
		related_eventpromoter.setEventTableCollection((Collection<EventTable>) eventtable);
		eventtable = eventTableDAO.save(eventtable);
		eventTableDAO.flush();

		related_eventpromoter = promoterDAO.save(related_eventpromoter);
		promoterDAO.flush();

		return eventtable;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public EventTable deleteEventTableMediaRelationCollection(Long eventtable_idEvent, Long related_mediarelationcollection_idMediaRelation) {
		MediaRelation related_mediarelationcollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation);

		EventTable eventtable = eventTableDAO.findByIdEvent(eventtable_idEvent);

		related_mediarelationcollection.setEventMedia(null);
		eventtable.setMediaRelationCollection(null);

		mediaRelationDAO.delete(related_mediarelationcollection);
		mediaRelationDAO.flush();

		return eventtable;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public EventTable saveEventTableMediaRelationCollection(Long idEvent, MediaRelation related_mediarelationcollection) {
		EventTable eventtable = eventTableDAO.findByIdEvent(idEvent);
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

		related_mediarelationcollection.setEventMedia(eventtable);
		eventtable.setMediaRelationCollection((Collection<MediaRelation>) related_mediarelationcollection);
		related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
		mediaRelationDAO.flush();

		eventtable = eventTableDAO.save(eventtable);
		eventTableDAO.flush();

		return eventtable;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@Transactional
	public void saveEventTable(EventTable eventtable) {
		EventTable existingEventTable = eventTableDAO.findByIdEvent(eventtable.getIdEvent());

		if (existingEventTable != null) {
			if (existingEventTable != eventtable) {
				existingEventTable.setIdEvent(eventtable.getIdEvent());
				existingEventTable.setEventName(eventtable.getEventName());
				existingEventTable.setEventDate(eventtable.getEventDate());
				existingEventTable.setEventPosterPath(eventtable.getEventPosterPath());
				existingEventTable.setEventComments(eventtable.getEventComments());
			}
			eventtable = eventTableDAO.save(existingEventTable);
		} else {
			eventtable = eventTableDAO.save(eventtable);
		}
		eventTableDAO.flush();
	}

	/**
	 * Load an existing EventTable entity
	 * 
	 */
	@Transactional
	public List<EventTable> loadEventTables() {
		return eventTableDAO.findAll();
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public EventTable saveEventTableFightCollection(Long idEvent, Fight related_fightcollection) {
		EventTable eventtable = eventTableDAO.findByIdEvent(idEvent);
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

		related_fightcollection.setEventId(eventtable);
		eventtable.setFightCollection((Collection<Fight>) related_fightcollection);
		related_fightcollection = fightDAO.save(related_fightcollection);
		fightDAO.flush();

		eventtable = eventTableDAO.save(eventtable);
		eventTableDAO.flush();

		return eventtable;
	}

	/**
	 * Return all EventTable entity
	 * 
	 */
	

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@Transactional
	public void deleteEventTable(EventTable eventtable) {
		eventTableDAO.delete(eventtable);
		eventTableDAO.flush();
	}

	/**
	 */
	@Transactional
	public EventTable findEventTableByPrimaryKey(Long idEvent) {
		return eventTableDAO.findByIdEvent(idEvent);
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@Transactional
	public EventTable deleteEventTableEventCountry(Long eventtable_idEvent, String related_eventcountry_codeCountry) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventtable_idEvent);
		Country related_eventcountry = countryDAO.findByCodeCountry(related_eventcountry_codeCountry);

		eventtable.setEventCountry(null);
		related_eventcountry.setEventTableCollection(null);
		eventtable = eventTableDAO.save(eventtable);
		eventTableDAO.flush();

		related_eventcountry = countryDAO.save(related_eventcountry);
		countryDAO.flush();

		countryDAO.delete(related_eventcountry);
		countryDAO.flush();

		return eventtable;
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@Transactional
	public EventTable saveEventTableEventCountry(Long idEvent, Country related_eventcountry) {
		EventTable eventtable = eventTableDAO.findByIdEvent(idEvent);
		Country existingeventCountry = countryDAO.findByCodeCountry(related_eventcountry.getCodeCountry());

		// copy into the existing record to preserve existing relationships
		if (existingeventCountry != null) {
			existingeventCountry.setCodeCountry(related_eventcountry.getCodeCountry());
			existingeventCountry.setCountryName(related_eventcountry.getCountryName());
			existingeventCountry.setCountryContinent(related_eventcountry.getCountryContinent());
			related_eventcountry = existingeventCountry;
		} else {
			related_eventcountry = countryDAO.save(related_eventcountry);
			countryDAO.flush();
		}

		eventtable.setEventCountry(related_eventcountry);
		related_eventcountry.setEventTableCollection((Collection<EventTable>) eventtable);
		eventtable = eventTableDAO.save(eventtable);
		eventTableDAO.flush();

		related_eventcountry = countryDAO.save(related_eventcountry);
		countryDAO.flush();

		return eventtable;
	}

	@Transactional
	public Page<EventTable> findAllEventTables(int idx, int elements, String dir) {
		
		return eventTableDAO.findAll(constructPageSpecification(idx, elements, dir));
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
