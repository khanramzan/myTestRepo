package rams.app.service;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

import rams.app.domain.EventTable;
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;
import rams.app.domain.MediaTable;
import rams.app.domain.Promoter;
import rams.app.domain.ResultTable;
import rams.app.domain.Sanctioner;

/**
 * Spring service that handles CRUD requests for MediaRelation entities
 * 
 */
public interface MediaRelationService {

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public void deleteMediaRelation(MediaRelation mediarelation);

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	public MediaRelation saveMediaRelationFighterMedia(Long idMediaRelation, Fighter related_fightermedia);

	/**
	 * Return a count of all MediaRelation entity
	 * 
	 */
	public Long countMediaRelations();

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	public MediaRelation deleteMediaRelationSanctionerMedia(Long mediarelation_idMediaRelation, Long related_sanctionermedia_idSanctioner);

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	public MediaRelation saveMediaRelationEventMedia(Long idMediaRelation_1, EventTable related_eventmedia);

	/**
	 * Delete an existing MediaTable entity
	 * 
	 */
	public MediaRelation deleteMediaRelationMediaId(Long mediarelation_idMediaRelation_1, Long related_mediaid_idMedia);

	/**
	 * Return all MediaRelation entity
	 * 
	 */
	public Page<MediaRelation> findAllMediaRelations(int idx, int elements, String dir );

	/**
	 * Load an existing MediaRelation entity
	 * 
	 */
	public List<MediaRelation> loadMediaRelations();

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	public MediaRelation deleteMediaRelationPromoterMedia(Long mediarelation_idMediaRelation_2, Long related_promotermedia_idPromoter);

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	public MediaRelation deleteMediaRelationFighterMedia(Long mediarelation_idMediaRelation_3, Long related_fightermedia_idFighter);

	/**
	 */
	public MediaRelation findMediaRelationByPrimaryKey(Long idMediaRelation_2);

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	public MediaRelation saveMediaRelationSanctionerMedia(Long idMediaRelation_3, Sanctioner related_sanctionermedia);

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public MediaRelation saveMediaRelationFightMedia(Long idMediaRelation_4, Fight related_fightmedia);

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	public MediaRelation saveMediaRelationResultsMedia(Long idMediaRelation_5, ResultTable related_resultsmedia);

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	public MediaRelation deleteMediaRelationEventMedia(Long mediarelation_idMediaRelation_4, Long related_eventmedia_idEvent);

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public void saveMediaRelation(MediaRelation mediarelation_1);

	/**
	 * Save an existing MediaTable entity
	 * 
	 */
	public MediaRelation saveMediaRelationMediaId(Long idMediaRelation_6, MediaTable related_mediaid);

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	public MediaRelation saveMediaRelationPromoterMedia(Long idMediaRelation_7, Promoter related_promotermedia);

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public MediaRelation deleteMediaRelationFightMedia(Long mediarelation_idMediaRelation_5, Long related_fightmedia_idFight);

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	public MediaRelation deleteMediaRelationResultsMedia(Long mediarelation_idMediaRelation_6, Long related_resultsmedia_idResult);
}