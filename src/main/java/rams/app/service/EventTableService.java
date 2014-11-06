package rams.app.service;

import java.lang.Long;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rams.app.domain.Country;
import rams.app.domain.EventTable;
import rams.app.domain.Fight;
import rams.app.domain.MediaRelation;
import rams.app.domain.Promoter;

/**
 * Spring service that handles CRUD requests for EventTable entities
 * 
 */
public interface EventTableService {

	/**
	 * Return a count of all EventTable entity
	 * 
	 */
	public Long countEventTables();

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public EventTable deleteEventTableFightCollection(Long eventtable_idEvent, Long related_fightcollection_idFight);

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	public EventTable deleteEventTableEventPromoter(Long eventtable_idEvent_1, Long related_eventpromoter_idPromoter);

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	public EventTable saveEventTableEventPromoter(Long idEvent, Promoter related_eventpromoter);

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public EventTable deleteEventTableMediaRelationCollection(Long eventtable_idEvent_2, Long related_mediarelationcollection_idMediaRelation);

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public EventTable saveEventTableMediaRelationCollection(Long idEvent_1, MediaRelation related_mediarelationcollection);

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	public void saveEventTable(EventTable eventtable);

	/**
	 * Load an existing EventTable entity
	 * 
	 */
	public List<EventTable> loadEventTables();

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public EventTable saveEventTableFightCollection(Long idEvent_2, Fight related_fightcollection);

	/**
	 * Return all EventTable entity
	 * 
	 */
	public Page<EventTable> findAllEventTables(int idx, int elements, String dir );

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	public void deleteEventTable(EventTable eventtable_1);

	/**
	 */
	public EventTable findEventTableByPrimaryKey(Long idEvent_3);

	/**
	 * Delete an existing Country entity
	 * 
	 */
	public EventTable deleteEventTableEventCountry(Long eventtable_idEvent_3, String related_eventcountry_codeCountry);

	/**
	 * Save an existing Country entity
	 * 
	 */
	public EventTable saveEventTableEventCountry(Long idEvent_4, Country related_eventcountry);
}