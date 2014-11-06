package rams.app.service;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

import rams.app.domain.Country;
import rams.app.domain.EventTable;
import rams.app.domain.MediaRelation;
import rams.app.domain.Promoter;

/**
 * Spring service that handles CRUD requests for Promoter entities
 * 
 */
public interface PromoterService {

	/**
	 * Return a count of all Promoter entity
	 * 
	 */
	public Long countPromoters();

	/**
	 * Load an existing Promoter entity
	 * 
	 */
	public List<Promoter> loadPromoters();

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public Promoter savePromoterMediaRelationCollection(Long idPromoter, MediaRelation related_mediarelationcollection);

	/**
	 * Delete an existing Country entity
	 * 
	 */
	public Promoter deletePromoterPromoterCountry(Long promoter_idPromoter, String related_promotercountry_codeCountry);

	/**
	 */
	public Promoter findPromoterByPrimaryKey(Long idPromoter_1);

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	public void savePromoter(Promoter promoter);

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public Promoter deletePromoterMediaRelationCollection(Long promoter_idPromoter_1, Long related_mediarelationcollection_idMediaRelation);

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	public void deletePromoter(Promoter promoter_1);

	/**
	 * Save an existing Country entity
	 * 
	 */
	public Promoter savePromoterPromoterCountry(Long idPromoter_2, Country related_promotercountry);

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	public Promoter savePromoterEventTableCollection(Long idPromoter_3, EventTable related_eventtablecollection);

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	public Promoter deletePromoterEventTableCollection(Long promoter_idPromoter_2, Long related_eventtablecollection_idEvent);

	/**
	 * Return all Promoter entity
	 * 
	 */
	public Page<Promoter> findAllPromoters(int idx, int elements, String dir );
}