package rams.app.service;

import java.lang.Long;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rams.app.domain.Fight;
import rams.app.domain.MediaRelation;
import rams.app.domain.ResultTable;

/**
 * Spring service that handles CRUD requests for ResultTable entities
 * 
 */
public interface ResultTableService {

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public ResultTable deleteResultTableFight(Long resulttable_idResult, Long related_fight_idFight);

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	public ResultTable deleteResultTableMediaRelationCollection(Long resulttable_idResult_1, Long related_mediarelationcollection_idMediaRelation);

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	public void deleteResultTable(ResultTable resulttable);

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public ResultTable saveResultTableFight(Long idResult, Fight related_fight);

	/**
	 * Load an existing ResultTable entity
	 * 
	 */
	public List<ResultTable> loadResultTables();

	/**
	 */
	public ResultTable findResultTableByPrimaryKey(Long idResult_1);

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	public ResultTable saveResultTableMediaRelationCollection(Long idResult_2, MediaRelation related_mediarelationcollection);

	/**
	 * Return all ResultTable entity
	 * 
	 */
	public Page<ResultTable> findAllResultTables(int idx, int elements, String dir );

	/**
	 * Return a count of all ResultTable entity
	 * 
	 */
	public Long countResultTables();

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	public void saveResultTable(ResultTable resulttable_1);
}