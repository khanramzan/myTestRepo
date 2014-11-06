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






import rams.app.domain.MediaRelation;
import rams.app.domain.MediaTable;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.MediaTableRepository;

/**
 * Spring service that handles CRUD requests for MediaTable entities
 * 
 */

@Service("MediaTableService")
@Transactional
public class MediaTableServiceImpl implements MediaTableService {

	/**
	 * DAO injected by Spring that manages MediaRelation entities
	 * 
	 */
	@Autowired
	private MediaRelationRepository mediaRelationDAO;

	/**
	 * DAO injected by Spring that manages MediaTable entities
	 * 
	 */
	@Autowired
	private MediaTableRepository mediaTableDAO;

	/**
	 * Instantiates a new MediaTableServiceImpl.
	 *
	 */
	public MediaTableServiceImpl() {
	}

	/**
	 * Save an existing MediaTable entity
	 * 
	 */
	@Transactional
	public void saveMediaTable(MediaTable mediatable) {
		MediaTable existingMediaTable = mediaTableDAO.findByIdMedia(mediatable.getIdMedia());

		if (existingMediaTable != null) {
			if (existingMediaTable != mediatable) {
				existingMediaTable.setIdMedia(mediatable.getIdMedia());
				existingMediaTable.setMediaName(mediatable.getMediaName());
				existingMediaTable.setMediaPath(mediatable.getMediaPath());
				existingMediaTable.setMediaOnServer(mediatable.getMediaOnServer());
			}
			mediatable = mediaTableDAO.save(existingMediaTable);
		} else {
			mediatable = mediaTableDAO.save(mediatable);
		}
		mediaTableDAO.flush();
	}

	/**
	 * Return a count of all MediaTable entity
	 * 
	 */
	@Transactional
	public Long countMediaTables() {
		return mediaTableDAO.count();
	}

	/**
	 * Delete an existing MediaTable entity
	 * 
	 */
	@Transactional
	public void deleteMediaTable(MediaTable mediatable) {
		mediaTableDAO.delete(mediatable);
		mediaTableDAO.flush();
	}

	/**
	 */
	@Transactional
	public MediaTable findMediaTableByPrimaryKey(Long idMedia) {
		return mediaTableDAO.findByIdMedia(idMedia);
	}

	/**
	 * Return all MediaTable entity
	 * 
	 */

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public MediaTable saveMediaTableMediaRelationCollection(Long idMedia, MediaRelation related_mediarelationcollection) {
		MediaTable mediatable = mediaTableDAO.findByIdMedia(idMedia);
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

		related_mediarelationcollection.setMediaId(mediatable);
		mediatable.setMediaRelationCollection((Collection<MediaRelation>) related_mediarelationcollection);
		related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
		mediaRelationDAO.flush();

		mediatable = mediaTableDAO.save(mediatable);
		mediaTableDAO.flush();

		return mediatable;
	}

	/**
	 * Load an existing MediaTable entity
	 * 
	 */
	@Transactional
	public List<MediaTable> loadMediaTables() {
		return mediaTableDAO.findAll();
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public MediaTable deleteMediaTableMediaRelationCollection(Long mediatable_idMedia, Long related_mediarelationcollection_idMediaRelation) {
		MediaRelation related_mediarelationcollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation);

		MediaTable mediatable = mediaTableDAO.findByIdMedia(mediatable_idMedia);

		related_mediarelationcollection.setMediaId(null);
		mediatable.setMediaRelationCollection(null);

		mediaRelationDAO.delete(related_mediarelationcollection);
		mediaRelationDAO.flush();

		return mediatable;
	}

	@Transactional
	public Page<MediaTable> findAllMediaTables(int idx, int elements, String dir) {

		return mediaTableDAO.findAll(constructPageSpecification(idx, elements, dir));
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
