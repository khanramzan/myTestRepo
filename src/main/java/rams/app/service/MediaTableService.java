package rams.app.service;

import java.lang.Long;
import java.util.List;

import org.springframework.data.domain.Page;

import rams.app.domain.MediaRelation;
import rams.app.domain.MediaTable;

/**
 * Spring service that handles CRUD requests for MediaTable entities
 * 
 */
public interface MediaTableService {

	/**
	 * Save an existing MediaTable entity
	 * 
	 */
	public void saveMediaTable(MediaTable mediatable);

	/**
	 * Return a count of all MediaTable entity
	 * 
	 */
	public Long countMediaTables();

	/**
	 * Delete an existing MediaTable entity
	 * 
	 */
	public void deleteMediaTable(MediaTable mediatable_1);

	/**
	 */
	public MediaTable findMediaTableByPrimaryKey(Long idMedia);

	/**
	 * Return all MediaTable entity
	 * 
	 */
	public Page<MediaTable> findAllMediaTables(int idx, int elements, String dir );

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public MediaTable saveMediaTableMediaRelationCollection(Long idMedia_1, MediaRelation related_mediarelationcollection);

	/**
	 * Load an existing MediaTable entity
	 * 
	 */
	public List<MediaTable> loadMediaTables();

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public MediaTable deleteMediaTableMediaRelationCollection(Long mediatable_idMedia, Long related_mediarelationcollection_idMediaRelation);
}