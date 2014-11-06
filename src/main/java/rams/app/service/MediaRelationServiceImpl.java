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













import rams.app.domain.EventTable;
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;
import rams.app.domain.MediaTable;
import rams.app.domain.Promoter;
import rams.app.domain.ResultTable;
import rams.app.domain.Sanctioner;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.FighterRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.MediaTableRepository;
import rams.app.rerpository.PromoterRepository;
import rams.app.rerpository.ResultTableRepository;
import rams.app.rerpository.SanctionerRepository;

/**
 * Spring service that handles CRUD requests for MediaRelation entities
 * 
 */

@Service("MediaRelationService")
@Transactional
public class MediaRelationServiceImpl implements MediaRelationService {

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
	 * DAO injected by Spring that manages Fighter entities
	 * 
	 */
	@Autowired
	private FighterRepository fighterDAO;

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
	 * DAO injected by Spring that manages Promoter entities
	 * 
	 */
	@Autowired
	private PromoterRepository promoterDAO;

	/**
	 * DAO injected by Spring that manages ResultTable entities
	 * 
	 */
	@Autowired
	private ResultTableRepository resultTableDAO;

	/**
	 * DAO injected by Spring that manages Sanctioner entities
	 * 
	 */
	@Autowired
	private SanctionerRepository sanctionerDAO;

	/**
	 * Instantiates a new MediaRelationServiceImpl.
	 *
	 */
	public MediaRelationServiceImpl() {
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public void deleteMediaRelation(MediaRelation mediarelation) {
		mediaRelationDAO.delete(mediarelation);
		mediaRelationDAO.flush();
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@Transactional
	public MediaRelation saveMediaRelationFighterMedia(Long idMediaRelation, Fighter related_fightermedia) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
		Fighter existingfighterMedia = fighterDAO.findByidFighter(related_fightermedia.getIdFighter());

		// copy into the existing record to preserve existing relationships
		if (existingfighterMedia != null) {
			existingfighterMedia.setIdFighter(related_fightermedia.getIdFighter());
			existingfighterMedia.setFighterName(related_fightermedia.getFighterName());
			existingfighterMedia.setFighterClub(related_fightermedia.getFighterClub());
			existingfighterMedia.setFighterProfilePicPath(related_fightermedia.getFighterProfilePicPath());
			existingfighterMedia.setFightComments(related_fightermedia.getFightComments());
			related_fightermedia = existingfighterMedia;
		} else {
			related_fightermedia = fighterDAO.save(related_fightermedia);
			fighterDAO.flush();
		}

		mediarelation.setFighterMedia(related_fightermedia);
		related_fightermedia.setMediaRelationCollection((Collection<MediaRelation>) mediarelation);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_fightermedia = fighterDAO.save(related_fightermedia);
		fighterDAO.flush();

		return mediarelation;
	}

	/**
	 * Return a count of all MediaRelation entity
	 * 
	 */
	@Transactional
	public Long countMediaRelations() {
		return mediaRelationDAO.count();
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public MediaRelation deleteMediaRelationSanctionerMedia(Long mediarelation_idMediaRelation, Long related_sanctionermedia_idSanctioner) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelation_idMediaRelation);
		Sanctioner related_sanctionermedia = sanctionerDAO.findByIdSanctioner(related_sanctionermedia_idSanctioner);

		mediarelation.setSanctionerMedia(null);
		related_sanctionermedia.setMediaRelationCollection(null);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_sanctionermedia = sanctionerDAO.save(related_sanctionermedia);
		sanctionerDAO.flush();

		sanctionerDAO.delete(related_sanctionermedia);
		sanctionerDAO.flush();

		return mediarelation;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@Transactional
	public MediaRelation saveMediaRelationEventMedia(Long idMediaRelation, EventTable related_eventmedia) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
		EventTable existingeventMedia = eventTableDAO.findByIdEvent(related_eventmedia.getIdEvent());

		// copy into the existing record to preserve existing relationships
		if (existingeventMedia != null) {
			existingeventMedia.setIdEvent(related_eventmedia.getIdEvent());
			existingeventMedia.setEventName(related_eventmedia.getEventName());
			existingeventMedia.setEventDate(related_eventmedia.getEventDate());
			existingeventMedia.setEventPosterPath(related_eventmedia.getEventPosterPath());
			existingeventMedia.setEventComments(related_eventmedia.getEventComments());
			related_eventmedia = existingeventMedia;
		} else {
			related_eventmedia = eventTableDAO.save(related_eventmedia);
			eventTableDAO.flush();
		}

		mediarelation.setEventMedia(related_eventmedia);
		related_eventmedia.setMediaRelationCollection((Collection<MediaRelation>) mediarelation);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_eventmedia = eventTableDAO.save(related_eventmedia);
		eventTableDAO.flush();

		return mediarelation;
	}

	/**
	 * Delete an existing MediaTable entity
	 * 
	 */
	@Transactional
	public MediaRelation deleteMediaRelationMediaId(Long mediarelation_idMediaRelation, Long related_mediaid_idMedia) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelation_idMediaRelation);
		MediaTable related_mediaid = mediaTableDAO.findByIdMedia(mediarelation_idMediaRelation);

		mediarelation.setMediaId(null);
		related_mediaid.setMediaRelationCollection(null);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_mediaid = mediaTableDAO.save(related_mediaid);
		mediaTableDAO.flush();

		mediaTableDAO.delete(related_mediaid);
		mediaTableDAO.flush();

		return mediarelation;
	}

	/**
	 * Return all MediaRelation entity
	 * 
	 */


	/**
	 * Load an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public List<MediaRelation> loadMediaRelations() {
		return mediaRelationDAO.findAll();
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@Transactional
	public MediaRelation deleteMediaRelationPromoterMedia(Long mediarelation_idMediaRelation, Long related_promotermedia_idPromoter) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelation_idMediaRelation);
		Promoter related_promotermedia = promoterDAO.findByIdPromoter(related_promotermedia_idPromoter);

		mediarelation.setPromoterMedia(null);
		related_promotermedia.setMediaRelationCollection(null);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_promotermedia = promoterDAO.save(related_promotermedia);
		promoterDAO.flush();

		promoterDAO.delete(related_promotermedia);
		promoterDAO.flush();

		return mediarelation;
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@Transactional
	public MediaRelation deleteMediaRelationFighterMedia(Long mediarelation_idMediaRelation, Long related_fightermedia_idFighter) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelation_idMediaRelation);
		Fighter related_fightermedia = fighterDAO.findByidFighter(related_fightermedia_idFighter);

		mediarelation.setFighterMedia(null);
		related_fightermedia.setMediaRelationCollection(null);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_fightermedia = fighterDAO.save(related_fightermedia);
		fighterDAO.flush();

		fighterDAO.delete(related_fightermedia);
		fighterDAO.flush();

		return mediarelation;
	}

	/**
	 */
	@Transactional
	public MediaRelation findMediaRelationByPrimaryKey(Long idMediaRelation) {
		return mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@Transactional
	public MediaRelation saveMediaRelationSanctionerMedia(Long idMediaRelation, Sanctioner related_sanctionermedia) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
		Sanctioner existingsanctionerMedia = sanctionerDAO.findByIdSanctioner(related_sanctionermedia.getIdSanctioner());

		// copy into the existing record to preserve existing relationships
		if (existingsanctionerMedia != null) {
			existingsanctionerMedia.setIdSanctioner(related_sanctionermedia.getIdSanctioner());
			existingsanctionerMedia.setSanctionerName(related_sanctionermedia.getSanctionerName());
			existingsanctionerMedia.setSanctionerAddress(related_sanctionermedia.getSanctionerAddress());
			existingsanctionerMedia.setSanctionerLogoPath(related_sanctionermedia.getSanctionerLogoPath());
			existingsanctionerMedia.setSanctionerComments(related_sanctionermedia.getSanctionerComments());
			related_sanctionermedia = existingsanctionerMedia;
		} else {
			related_sanctionermedia = sanctionerDAO.save(related_sanctionermedia);
			sanctionerDAO.flush();
		}

		mediarelation.setSanctionerMedia(related_sanctionermedia);
		related_sanctionermedia.setMediaRelationCollection((Collection<MediaRelation>) mediarelation);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_sanctionermedia = sanctionerDAO.save(related_sanctionermedia);
		sanctionerDAO.flush();

		return mediarelation;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public MediaRelation saveMediaRelationFightMedia(Long idMediaRelation, Fight related_fightmedia) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
		Fight existingfightMedia = fightDAO.findByidFight(related_fightmedia.getIdFight());

		// copy into the existing record to preserve existing relationships
		if (existingfightMedia != null) {
			existingfightMedia.setIdFight(related_fightmedia.getIdFight());
			existingfightMedia.setFightComments(related_fightmedia.getFightComments());
			related_fightmedia = existingfightMedia;
		} else {
			related_fightmedia = fightDAO.save(related_fightmedia);
			fightDAO.flush();
		}

		mediarelation.setFightMedia(related_fightmedia);
		related_fightmedia.setMediaRelationCollection((Collection<MediaRelation>) mediarelation);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_fightmedia = fightDAO.save(related_fightmedia);
		fightDAO.flush();

		return mediarelation;
	}

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	@Transactional
	public MediaRelation saveMediaRelationResultsMedia(Long idMediaRelation, ResultTable related_resultsmedia) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
		ResultTable existingresultsMedia = resultTableDAO.findByIdResult(related_resultsmedia.getIdResult());

		// copy into the existing record to preserve existing relationships
		if (existingresultsMedia != null) {
			existingresultsMedia.setIdResult(related_resultsmedia.getIdResult());
			existingresultsMedia.setAnnouncedResult(related_resultsmedia.getAnnouncedResult());
			existingresultsMedia.setResultComment(related_resultsmedia.getResultComment());
			related_resultsmedia = existingresultsMedia;
		} else {
			related_resultsmedia = resultTableDAO.save(related_resultsmedia);
			resultTableDAO.flush();
		}

		mediarelation.setResultsMedia(related_resultsmedia);
		related_resultsmedia.setMediaRelationCollection((Collection<MediaRelation>) mediarelation);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_resultsmedia = resultTableDAO.save(related_resultsmedia);
		resultTableDAO.flush();

		return mediarelation;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@Transactional
	public MediaRelation deleteMediaRelationEventMedia(Long mediarelation_idMediaRelation, Long related_eventmedia_idEvent) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelation_idMediaRelation);
		EventTable related_eventmedia = eventTableDAO.findByIdEvent(related_eventmedia_idEvent);

		mediarelation.setEventMedia(null);
		related_eventmedia.setMediaRelationCollection(null);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_eventmedia = eventTableDAO.save(related_eventmedia);
		eventTableDAO.flush();

		eventTableDAO.delete(related_eventmedia);
		eventTableDAO.flush();

		return mediarelation;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public void saveMediaRelation(MediaRelation mediarelation) {
		MediaRelation existingMediaRelation = mediaRelationDAO.findByIdMediaRelation(mediarelation.getIdMediaRelation());

		if (existingMediaRelation != null) {
			if (existingMediaRelation != mediarelation) {
				existingMediaRelation.setIdMediaRelation(mediarelation.getIdMediaRelation());
				existingMediaRelation.setMediaComments(mediarelation.getMediaComments());
			}
			mediarelation = mediaRelationDAO.save(existingMediaRelation);
		} else {
			mediarelation = mediaRelationDAO.save(mediarelation);
		}
		mediaRelationDAO.flush();
	}

	/**
	 * Save an existing MediaTable entity
	 * 
	 */
	@Transactional
	public MediaRelation saveMediaRelationMediaId(Long idMediaRelation, MediaTable related_mediaid) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
		MediaTable existingmediaId = mediaTableDAO.findByIdMedia(idMediaRelation);

		// copy into the existing record to preserve existing relationships
		if (existingmediaId != null) {
			existingmediaId.setIdMedia(related_mediaid.getIdMedia());
			existingmediaId.setMediaName(related_mediaid.getMediaName());
			existingmediaId.setMediaPath(related_mediaid.getMediaPath());
			existingmediaId.setMediaOnServer(related_mediaid.getMediaOnServer());
			related_mediaid = existingmediaId;
		} else {
			related_mediaid = mediaTableDAO.save(related_mediaid);
			mediaTableDAO.flush();
		}

		mediarelation.setMediaId(related_mediaid);
		related_mediaid.setMediaRelationCollection((Collection<MediaRelation>) mediarelation);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_mediaid = mediaTableDAO.save(related_mediaid);
		mediaTableDAO.flush();

		return mediarelation;
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@Transactional
	public MediaRelation saveMediaRelationPromoterMedia(Long idMediaRelation, Promoter related_promotermedia) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelation);
		Promoter existingpromoterMedia = promoterDAO.findByIdPromoter(related_promotermedia.getIdPromoter());

		// copy into the existing record to preserve existing relationships
		if (existingpromoterMedia != null) {
			existingpromoterMedia.setIdPromoter(related_promotermedia.getIdPromoter());
			existingpromoterMedia.setPromoterName(related_promotermedia.getPromoterName());
			existingpromoterMedia.setPromoterAddress(related_promotermedia.getPromoterAddress());
			existingpromoterMedia.setPromoterPosterPath(related_promotermedia.getPromoterPosterPath());
			existingpromoterMedia.setPromoterComments(related_promotermedia.getPromoterComments());
			related_promotermedia = existingpromoterMedia;
		} else {
			related_promotermedia = promoterDAO.save(related_promotermedia);
			promoterDAO.flush();
		}

		mediarelation.setPromoterMedia(related_promotermedia);
		related_promotermedia.setMediaRelationCollection((Collection<MediaRelation>) mediarelation);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_promotermedia = promoterDAO.save(related_promotermedia);
		promoterDAO.flush();

		return mediarelation;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public MediaRelation deleteMediaRelationFightMedia(Long mediarelation_idMediaRelation, Long related_fightmedia_idFight) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelation_idMediaRelation);
		Fight related_fightmedia = fightDAO.findByidFight(related_fightmedia_idFight);

		mediarelation.setFightMedia(null);
		related_fightmedia.setMediaRelationCollection(null);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_fightmedia = fightDAO.save(related_fightmedia);
		fightDAO.flush();

		fightDAO.delete(related_fightmedia);
		fightDAO.flush();

		return mediarelation;
	}

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	@Transactional
	public MediaRelation deleteMediaRelationResultsMedia(Long mediarelation_idMediaRelation, Long related_resultsmedia_idResult) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelation_idMediaRelation);
		ResultTable related_resultsmedia = resultTableDAO.findByIdResult(related_resultsmedia_idResult);

		mediarelation.setResultsMedia(null);
		related_resultsmedia.setMediaRelationCollection(null);
		mediarelation = mediaRelationDAO.save(mediarelation);
		mediaRelationDAO.flush();

		related_resultsmedia = resultTableDAO.save(related_resultsmedia);
		resultTableDAO.flush();

		resultTableDAO.delete(related_resultsmedia);
		resultTableDAO.flush();

		return mediarelation;
	}

	@Transactional
	public Page<MediaRelation> findAllMediaRelations(int idx, int elements,
			String dir) {
		// TODO Auto-generated method stub
		return mediaRelationDAO.findAll(constructPageSpecification(idx, elements, dir));
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
