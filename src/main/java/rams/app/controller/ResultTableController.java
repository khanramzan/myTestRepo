package rams.app.controller;

import java.lang.Long;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;





import rams.app.domain.Fight;
import rams.app.domain.MediaRelation;
import rams.app.domain.ResultTable;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.ResultTableRepository;
import rams.app.service.ResultTableService;

/**
 * Spring MVC controller that handles CRUD requests for ResultTable entities
 * 
 */

@Controller("ResultTableController")
public class ResultTableController {

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
	 * Service injected by Spring that provides CRUD operations for ResultTable entities
	 * 
	 */
	@Autowired
	private ResultTableService resultTableService;

	/**
	 * Select the child Fight entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteResultTableFight")
	public ModelAndView confirmDeleteResultTableFight(@RequestParam Long resulttable_idResult, @RequestParam Long related_fight_idFight) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(related_fight_idFight));
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.setViewName("resulttable/fight/deleteFight.jsp");

		return mav;
	}

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/deleteResultTable")
	public String deleteResultTable(@RequestParam Long idResultKey) {
		ResultTable resulttable = resultTableDAO.findByIdResult(idResultKey);
		resultTableService.deleteResultTable(resulttable);
		return "forward:/indexResultTable";
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deleteResultTableMediaRelationCollection")
	public ModelAndView deleteResultTableMediaRelationCollection(@RequestParam Long resulttable_idResult, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		ResultTable resulttable = resultTableService.deleteResultTableMediaRelationCollection(resulttable_idResult, related_mediarelationcollection_idMediaRelation);

		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("resulttable", resulttable);
		mav.setViewName("resulttable/viewResultTable.jsp");

		return mav;
	}

	/**
	 * Select an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/selectResultTable")
	public ModelAndView selectResultTable(@RequestParam Long idResultKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", resultTableDAO.findByIdResult(idResultKey));
		mav.setViewName("resulttable/viewResultTable.jsp");

		return mav;
	}

	/**
	 * Show all ResultTable entities
	 * 
	 */
	@RequestMapping("/indexResultTable")
	public ModelAndView listResultTables() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttables", resultTableService.loadResultTables());

		mav.setViewName("resulttable/listResultTables.jsp");

		return mav;
	}

	/**
	 * Entry point to show all ResultTable entities
	 * 
	 */
	public String indexResultTable() {
		return "redirect:/indexResultTable";
	}

	/**
	 * View an existing Fight entity
	 * 
	 */
	@RequestMapping("/selectResultTableFight")
	public ModelAndView selectResultTableFight(@RequestParam Long resulttable_idResult, @RequestParam Long fight_idFight) {
		Fight fight = fightDAO.findByidFight(fight_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("fight", fight);
		mav.setViewName("resulttable/fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/saveResultTableMediaRelationCollection")
	public ModelAndView saveResultTableMediaRelationCollection(@RequestParam Long resulttable_idResult, @ModelAttribute MediaRelation mediarelationcollection) {
		ResultTable parent_resulttable = resultTableService.saveResultTableMediaRelationCollection(resulttable_idResult, mediarelationcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("resulttable", parent_resulttable);
		mav.setViewName("resulttable/viewResultTable.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities by ResultTable
	 * 
	 */
	@RequestMapping("/listResultTableMediaRelationCollection")
	public ModelAndView listResultTableMediaRelationCollection(@RequestParam Long idResultKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", resultTableDAO.findByIdResult(idResultKey));
		mav.setViewName("resulttable/mediarelationcollection/listMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * View an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectResultTableMediaRelationCollection")
	public ModelAndView selectResultTableMediaRelationCollection(@RequestParam Long resulttable_idResult, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("resulttable/mediarelationcollection/viewMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/resulttableController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Select the ResultTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteResultTable")
	public ModelAndView confirmDeleteResultTable(@RequestParam Long idResultKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", resultTableDAO.findByIdResult(idResultKey));
		mav.setViewName("resulttable/deleteResultTable.jsp");

		return mav;
	}

	/**
	 * Show all Fight entities by ResultTable
	 * 
	 */
	@RequestMapping("/listResultTableFight")
	public ModelAndView listResultTableFight(@RequestParam Long idResultKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", resultTableDAO.findByIdResult(idResultKey));
		mav.setViewName("resulttable/fight/listFight.jsp");

		return mav;
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newResultTableMediaRelationCollection")
	public ModelAndView newResultTableMediaRelationCollection(@RequestParam Long resulttable_idResult) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("resulttable/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/saveResultTable")
	public String saveResultTable(@ModelAttribute ResultTable resulttable) {
		resultTableService.saveResultTable(resulttable);
		return "forward:/indexResultTable";
	}

	/**
	 * Select the child MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteResultTableMediaRelationCollection")
	public ModelAndView confirmDeleteResultTableMediaRelationCollection(@RequestParam Long resulttable_idResult, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation));
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.setViewName("resulttable/mediarelationcollection/deleteMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fight entity
	 * 
	 */
	@RequestMapping("/editResultTableFight")
	public ModelAndView editResultTableFight(@RequestParam Long resulttable_idResult, @RequestParam Long fight_idFight) {
		Fight fight = fightDAO.findByidFight(fight_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("fight", fight);
		mav.setViewName("resulttable/fight/editFight.jsp");

		return mav;
	}

	/**
	 * Create a new Fight entity
	 * 
	 */
	@RequestMapping("/newResultTableFight")
	public ModelAndView newResultTableFight(@RequestParam Long resulttable_idResult) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("fight", new Fight());
		mav.addObject("newFlag", true);
		mav.setViewName("resulttable/fight/editFight.jsp");

		return mav;
	}

	/**
	 * Edit an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/editResultTable")
	public ModelAndView editResultTable(@RequestParam Long idResultKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", resultTableDAO.findByIdResult(idResultKey));
		mav.setViewName("resulttable/editResultTable.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editResultTableMediaRelationCollection")
	public ModelAndView editResultTableMediaRelationCollection(@RequestParam Long resulttable_idResult, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("resulttable/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Create a new ResultTable entity
	 * 
	 */
	@RequestMapping("/newResultTable")
	public ModelAndView newResultTable() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", new ResultTable());
		mav.addObject("newFlag", true);
		mav.setViewName("resulttable/editResultTable.jsp");

		return mav;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@RequestMapping("/saveResultTableFight")
	public ModelAndView saveResultTableFight(@RequestParam Long resulttable_idResult, @ModelAttribute Fight fight) {
		ResultTable parent_resulttable = resultTableService.saveResultTableFight(resulttable_idResult, fight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("resulttable", parent_resulttable);
		mav.setViewName("resulttable/viewResultTable.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@RequestMapping("/deleteResultTableFight")
	public ModelAndView deleteResultTableFight(@RequestParam Long resulttable_idResult, @RequestParam Long related_fight_idFight) {
		ModelAndView mav = new ModelAndView();

		ResultTable resulttable = resultTableService.deleteResultTableFight(resulttable_idResult, related_fight_idFight);

		mav.addObject("resulttable_idResult", resulttable_idResult);
		mav.addObject("resulttable", resulttable);
		mav.setViewName("resulttable/viewResultTable.jsp");

		return mav;
	}

	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) { // Register static property editors.
		binder.registerCustomEditor(java.util.Calendar.class, new org.skyway.spring.util.databinding.CustomCalendarEditor());
		binder.registerCustomEditor(byte[].class, new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(false));
		binder.registerCustomEditor(Boolean.class, new org.skyway.spring.util.databinding.EnhancedBooleanEditor(true));
		binder.registerCustomEditor(java.math.BigDecimal.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(java.math.BigDecimal.class, true));
		binder.registerCustomEditor(Integer.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Integer.class, true));
		binder.registerCustomEditor(java.util.Date.class, new org.skyway.spring.util.databinding.CustomDateEditor());
		binder.registerCustomEditor(String.class, new org.skyway.spring.util.databinding.StringEditor());
		binder.registerCustomEditor(Long.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Long.class, true));
		binder.registerCustomEditor(Double.class, new org.skyway.spring.util.databinding.NaNHandlingNumberEditor(Double.class, true));
	}
}