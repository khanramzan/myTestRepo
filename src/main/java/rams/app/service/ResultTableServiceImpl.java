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








import rams.app.domain.Fight;
import rams.app.domain.MediaRelation;
import rams.app.domain.ResultTable;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.ResultTableRepository;

/**
 * Spring service that handles CRUD requests for ResultTable entities
 * 
 */

@Service("ResultTableService")
@Transactional
public class ResultTableServiceImpl implements ResultTableService {

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
	 * DAO injected by Spring that manages ResultTable entities
	 * 
	 */
	@Autowired
	private ResultTableRepository resultTableDAO;

	/**
	 * Instantiates a new ResultTableServiceImpl.
	 *
	 */
	public ResultTableServiceImpl() {
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public ResultTable deleteResultTableFight(Long resulttable_idResult, Long related_fight_idFight) {
		ResultTable resulttable = resultTableDAO.findByIdResult(resulttable_idResult);
		Fight related_fight = fightDAO.findByidFight(related_fight_idFight);

		resulttable.setFight(null);
		related_fight.setResultTable(null);
		resulttable = resultTableDAO.save(resulttable);
		resultTableDAO.flush();

		related_fight = fightDAO.save(related_fight);
		fightDAO.flush();

		fightDAO.delete(related_fight);
		fightDAO.flush();

		return resulttable;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public ResultTable deleteResultTableMediaRelationCollection(Long resulttable_idResult, Long related_mediarelationcollection_idMediaRelation) {
		MediaRelation related_mediarelationcollection = mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation);

		ResultTable resulttable = resultTableDAO.findByIdResult(resulttable_idResult);

		related_mediarelationcollection.setResultsMedia(null);
		resulttable.setMediaRelationCollection(null);

		mediaRelationDAO.delete(related_mediarelationcollection);
		mediaRelationDAO.flush();

		return resulttable;
	}

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	@Transactional
	public void deleteResultTable(ResultTable resulttable) {
		resultTableDAO.delete(resulttable);
		resultTableDAO.flush();
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public ResultTable saveResultTableFight(Long idResult, Fight related_fight) {
		ResultTable resulttable = resultTableDAO.findByIdResult(idResult);
		Fight existingfight = fightDAO.findByidFight(related_fight.getIdFight());

		// copy into the existing record to preserve existing relationships
		if (existingfight != null) {
			existingfight.setIdFight(related_fight.getIdFight());
			existingfight.setFightComments(related_fight.getFightComments());
			related_fight = existingfight;
		} else {
			related_fight = fightDAO.save(related_fight);
			fightDAO.flush();
		}

		resulttable.setFight(related_fight);
		related_fight.setResultTable(resulttable);
		resulttable = resultTableDAO.save(resulttable);
		resultTableDAO.flush();

		related_fight = fightDAO.save(related_fight);
		fightDAO.flush();

		return resulttable;
	}

	/**
	 * Load an existing ResultTable entity
	 * 
	 */
	@Transactional
	public List<ResultTable> loadResultTables() {
		return resultTableDAO.findAll();
	}

	/**
	 */
	@Transactional
	public ResultTable findResultTableByPrimaryKey(Long idResult) {
		return resultTableDAO.findByIdResult(idResult);
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@Transactional
	public ResultTable saveResultTableMediaRelationCollection(Long idResult, MediaRelation related_mediarelationcollection) {
		ResultTable resulttable = resultTableDAO.findByIdResult(idResult);
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

		related_mediarelationcollection.setResultsMedia(resulttable);
		resulttable.setMediaRelationCollection((Collection<MediaRelation>) related_mediarelationcollection);
		related_mediarelationcollection = mediaRelationDAO.save(related_mediarelationcollection);
		mediaRelationDAO.flush();

		resulttable = resultTableDAO.save(resulttable);
		resultTableDAO.flush();

		return resulttable;
	}

	/**
	 * Return all ResultTable entity
	 * 
	 */
	

	/**
	 * Return a count of all ResultTable entity
	 * 
	 */
	@Transactional
	public Long countResultTables() {
		return resultTableDAO.count();
	}

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	@Transactional
	public void saveResultTable(ResultTable resulttable) {
		ResultTable existingResultTable = resultTableDAO.findByIdResult(resulttable.getIdResult());

		if (existingResultTable != null) {
			if (existingResultTable != resulttable) {
				existingResultTable.setIdResult(resulttable.getIdResult());
				existingResultTable.setAnnouncedResult(resulttable.getAnnouncedResult());
				existingResultTable.setResultComment(resulttable.getResultComment());
			}
			resulttable = resultTableDAO.save(existingResultTable);
		} else {
			resulttable = resultTableDAO.save(resulttable);
		}
		resultTableDAO.flush();
	}

	@Transactional
	public Page<ResultTable> findAllResultTables(int idx, int elements,
			String dir) {
		
		return resultTableDAO.findAll(constructPageSpecification(idx, elements, dir));
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
