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

import rams.app.domain.EventTable;
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;
import rams.app.domain.ResultTable;
import rams.app.domain.SanctionFights;
import rams.app.domain.WeightCatagory;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.FighterRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.ResultTableRepository;
import rams.app.rerpository.SanctionFightsRepository;
import rams.app.rerpository.WeightCatagoryRepository;
import rams.app.service.FightService;

/**
 * Spring MVC controller that handles CRUD requests for Fight entities
 * 
 */

@Controller("FightController")
public class FightController {

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
	 * DAO injected by Spring that manages ResultTable entities
	 * 
	 */
	@Autowired
	private ResultTableRepository resultTableDAO;

	/**
	 * DAO injected by Spring that manages SanctionFights entities
	 * 
	 */
	@Autowired
	private SanctionFightsRepository sanctionFightsDAO;

	/**
	 * DAO injected by Spring that manages WeightCatagory entities
	 * 
	 */
	@Autowired
	private WeightCatagoryRepository weightCatagoryDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Fight entities
	 * 
	 */
	@Autowired
	private FightService fightService;

	/**
	 * Show all WeightCatagory entities by Fight
	 * 
	 */
	@RequestMapping("/listFightWeightCatagoryId")
	public ModelAndView listFightWeightCatagoryId(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/weightcatagoryid/listWeightCatagoryId.jsp");

		return mav;
	}

	/**
	 * View an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/selectFightResultTable")
	public ModelAndView selectFightResultTable(@RequestParam Long fight_idFight, @RequestParam Long resulttable_idResult) {
		ResultTable resulttable = resultTableDAO.findByIdResult(resulttable_idResult);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("resulttable", resulttable);
		mav.setViewName("fight/resulttable/viewResultTable.jsp");

		return mav;
	}

	/**
	 * Edit an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/editFightFightSanctionedFor")
	public ModelAndView editFightFightSanctionedFor(@RequestParam Long fight_idFight, @RequestParam Long fightsanctionedfor_idSanctionfights) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(fightsanctionedfor_idSanctionfights);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("fight/fightsanctionedfor/editFightSanctionedFor.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@RequestMapping("/deleteFightFighterNo1")
	public ModelAndView deleteFightFighterNo1(@RequestParam Long fight_idFight, @RequestParam Long related_fighterno1_idFighter) {
		ModelAndView mav = new ModelAndView();

		Fight fight = fightService.deleteFightFighterNo1(fight_idFight, related_fighterno1_idFighter);

		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newFightMediaRelationCollection")
	public ModelAndView newFightMediaRelationCollection(@RequestParam Long fight_idFight) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Create a new Fighter entity
	 * 
	 */
	@RequestMapping("/newFightFighterNo1")
	public ModelAndView newFightFighterNo1(@RequestParam Long fight_idFight) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fighter", new Fighter());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/fighterno1/editFighterNo1.jsp");

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

	/**
	 * Select an existing Fight entity
	 * 
	 */
	@RequestMapping("/selectFight")
	public ModelAndView selectFight(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fight entity
	 * 
	 */
	@RequestMapping("/editFight")
	public ModelAndView editFight(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/editFight.jsp");

		return mav;
	}

	/**
	 * View an existing Fighter entity
	 * 
	 */
	@RequestMapping("/selectFightFighterNo2")
	public ModelAndView selectFightFighterNo2(@RequestParam Long fight_idFight, @RequestParam Long fighterno2_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fighterno2_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fighter", fighter);
		mav.setViewName("fight/fighterno2/viewFighterNo2.jsp");

		return mav;
	}

	/**
	 * Select the child WeightCatagory entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightWeightCatagoryId")
	public ModelAndView confirmDeleteFightWeightCatagoryId(@RequestParam Long fight_idFight, @RequestParam Long related_weightcatagoryid_idWeightCatagory) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("weightcatagory", weightCatagoryDAO.findByIdWeightCatagory(related_weightcatagoryid_idWeightCatagory));
		mav.addObject("fight_idFight", fight_idFight);
		mav.setViewName("fight/weightcatagoryid/deleteWeightCatagoryId.jsp");

		return mav;
	}

	/**
	 * Show all ResultTable entities by Fight
	 * 
	 */
	@RequestMapping("/listFightResultTable")
	public ModelAndView listFightResultTable(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/resulttable/listResultTable.jsp");

		return mav;
	}

	/**
	 * Show all EventTable entities by Fight
	 * 
	 */
	@RequestMapping("/listFightEventId")
	public ModelAndView listFightEventId(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/eventid/listEventId.jsp");

		return mav;
	}

	/**
	 * View an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectFightMediaRelationCollection")
	public ModelAndView selectFightMediaRelationCollection(@RequestParam Long fight_idFight, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("fight/mediarelationcollection/viewMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Create a new WeightCatagory entity
	 * 
	 */
	@RequestMapping("/newFightWeightCatagoryId")
	public ModelAndView newFightWeightCatagoryId(@RequestParam Long fight_idFight) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("weightcatagory", new WeightCatagory());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/weightcatagoryid/editWeightCatagoryId.jsp");

		return mav;
	}

	/**
	 * Select the child MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightMediaRelationCollection")
	public ModelAndView confirmDeleteFightMediaRelationCollection(@RequestParam Long fight_idFight, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation));
		mav.addObject("fight_idFight", fight_idFight);
		mav.setViewName("fight/mediarelationcollection/deleteMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Select the child SanctionFights entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightFightSanctionedFor")
	public ModelAndView confirmDeleteFightFightSanctionedFor(@RequestParam Long fight_idFight, @RequestParam Long related_fightsanctionedfor_idSanctionfights) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(related_fightsanctionedfor_idSanctionfights));
		mav.addObject("fight_idFight", fight_idFight);
		mav.setViewName("fight/fightsanctionedfor/deleteFightSanctionedFor.jsp");

		return mav;
	}

	/**
	 * Edit an existing EventTable entity
	 * 
	 */
	@RequestMapping("/editFightEventId")
	public ModelAndView editFightEventId(@RequestParam Long fight_idFight, @RequestParam Long eventid_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventid_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("fight/eventid/editEventId.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fighter entity
	 * 
	 */
	@RequestMapping("/editFightFighterNo2")
	public ModelAndView editFightFighterNo2(@RequestParam Long fight_idFight, @RequestParam Long fighterno2_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fighterno2_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fighter", fighter);
		mav.setViewName("fight/fighterno2/editFighterNo2.jsp");

		return mav;
	}

	/**
	 * Create a new Fighter entity
	 * 
	 */
	@RequestMapping("/newFightFighterNo2")
	public ModelAndView newFightFighterNo2(@RequestParam Long fight_idFight) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fighter", new Fighter());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/fighterno2/editFighterNo2.jsp");

		return mav;
	}

	/**
	 * Create a new Fight entity
	 * 
	 */
	@RequestMapping("/newFight")
	public ModelAndView newFight() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", new Fight());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/editFight.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editFightMediaRelationCollection")
	public ModelAndView editFightMediaRelationCollection(@RequestParam Long fight_idFight, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("fight/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities by Fight
	 * 
	 */
	@RequestMapping("/listFightMediaRelationCollection")
	public ModelAndView listFightMediaRelationCollection(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/mediarelationcollection/listMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing WeightCatagory entity
	 * 
	 */
	@RequestMapping("/saveFightWeightCatagoryId")
	public ModelAndView saveFightWeightCatagoryId(@RequestParam Long fight_idFight, @ModelAttribute WeightCatagory weightcatagoryid) {
		Fight parent_fight = fightService.saveFightWeightCatagoryId(fight_idFight, weightcatagoryid);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", parent_fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deleteFightMediaRelationCollection")
	public ModelAndView deleteFightMediaRelationCollection(@RequestParam Long fight_idFight, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		Fight fight = fightService.deleteFightMediaRelationCollection(fight_idFight, related_mediarelationcollection_idMediaRelation);

		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fighter entity
	 * 
	 */
	@RequestMapping("/editFightFighterNo1")
	public ModelAndView editFightFighterNo1(@RequestParam Long fight_idFight, @RequestParam Long fighterno1_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fighterno1_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fighter", fighter);
		mav.setViewName("fight/fighterno1/editFighterNo1.jsp");

		return mav;
	}

	/**
	 * Select the child EventTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightEventId")
	public ModelAndView confirmDeleteFightEventId(@RequestParam Long fight_idFight, @RequestParam Long related_eventid_idEvent) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(related_eventid_idEvent));
		mav.addObject("fight_idFight", fight_idFight);
		mav.setViewName("fight/eventid/deleteEventId.jsp");

		return mav;
	}

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/saveFightFightSanctionedFor")
	public ModelAndView saveFightFightSanctionedFor(@RequestParam Long fight_idFight, @ModelAttribute SanctionFights fightsanctionedfor) {
		Fight parent_fight = fightService.saveFightFightSanctionedFor(fight_idFight, fightsanctionedfor);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", parent_fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/saveFightResultTable")
	public ModelAndView saveFightResultTable(@RequestParam Long fight_idFight, @ModelAttribute ResultTable resulttable) {
		Fight parent_fight = fightService.saveFightResultTable(fight_idFight, resulttable);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", parent_fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Show all Fighter entities by Fight
	 * 
	 */
	@RequestMapping("/listFightFighterNo2")
	public ModelAndView listFightFighterNo2(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/fighterno2/listFighterNo2.jsp");

		return mav;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@RequestMapping("/saveFightEventId")
	public ModelAndView saveFightEventId(@RequestParam Long fight_idFight, @ModelAttribute EventTable eventid) {
		Fight parent_fight = fightService.saveFightEventId(fight_idFight, eventid);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", parent_fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Select the child ResultTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightResultTable")
	public ModelAndView confirmDeleteFightResultTable(@RequestParam Long fight_idFight, @RequestParam Long related_resulttable_idResult) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", resultTableDAO.findByIdResult(related_resulttable_idResult));
		mav.addObject("fight_idFight", fight_idFight);
		mav.setViewName("fight/resulttable/deleteResultTable.jsp");

		return mav;
	}

	/**
	 * View an existing WeightCatagory entity
	 * 
	 */
	@RequestMapping("/selectFightWeightCatagoryId")
	public ModelAndView selectFightWeightCatagoryId(@RequestParam Long fight_idFight, @RequestParam Long weightcatagoryid_idWeightCatagory) {
		WeightCatagory weightcatagory = weightCatagoryDAO.findByIdWeightCatagory(weightcatagoryid_idWeightCatagory);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("weightcatagory", weightcatagory);
		mav.setViewName("fight/weightcatagoryid/viewWeightCatagoryId.jsp");

		return mav;
	}

	/**
	 * View an existing Fighter entity
	 * 
	 */
	@RequestMapping("/selectFightFighterNo1")
	public ModelAndView selectFightFighterNo1(@RequestParam Long fight_idFight, @RequestParam Long fighterno1_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fighterno1_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fighter", fighter);
		mav.setViewName("fight/fighterno1/viewFighterNo1.jsp");

		return mav;
	}

	/**
	 * Select the Fight entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFight")
	public ModelAndView confirmDeleteFight(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/deleteFight.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/fightController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@RequestMapping("/saveFightFighterNo1")
	public ModelAndView saveFightFighterNo1(@RequestParam Long fight_idFight, @ModelAttribute Fighter fighterno1) {
		Fight parent_fight = fightService.saveFightFighterNo1(fight_idFight, fighterno1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", parent_fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * View an existing EventTable entity
	 * 
	 */
	@RequestMapping("/selectFightEventId")
	public ModelAndView selectFightEventId(@RequestParam Long fight_idFight, @RequestParam Long eventid_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventid_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("fight/eventid/viewEventId.jsp");

		return mav;
	}

	/**
	 * Select the child Fighter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightFighterNo1")
	public ModelAndView confirmDeleteFightFighterNo1(@RequestParam Long fight_idFight, @RequestParam Long related_fighterno1_idFighter) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(related_fighterno1_idFighter));
		mav.addObject("fight_idFight", fight_idFight);
		mav.setViewName("fight/fighterno1/deleteFighterNo1.jsp");

		return mav;
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@RequestMapping("/saveFightFighterNo2")
	public ModelAndView saveFightFighterNo2(@RequestParam Long fight_idFight, @ModelAttribute Fighter fighterno2) {
		Fight parent_fight = fightService.saveFightFighterNo2(fight_idFight, fighterno2);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", parent_fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Create a new EventTable entity
	 * 
	 */
	@RequestMapping("/newFightEventId")
	public ModelAndView newFightEventId(@RequestParam Long fight_idFight) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("eventtable", new EventTable());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/eventid/editEventId.jsp");

		return mav;
	}

	/**
	 * Show all Fight entities
	 * 
	 */
	@RequestMapping("/indexFight")
	public ModelAndView listFights() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fights", fightService.loadFights());

		mav.setViewName("fight/listFights.jsp");

		return mav;
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/deleteFightFightSanctionedFor")
	public ModelAndView deleteFightFightSanctionedFor(@RequestParam Long fight_idFight, @RequestParam Long related_fightsanctionedfor_idSanctionfights) {
		ModelAndView mav = new ModelAndView();

		Fight fight = fightService.deleteFightFightSanctionedFor(fight_idFight, related_fightsanctionedfor_idSanctionfights);

		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Fight entities
	 * 
	 */
	public String indexFight() {
		return "redirect:/indexFight";
	}

	/**
	 * Select the child Fighter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightFighterNo2")
	public ModelAndView confirmDeleteFightFighterNo2(@RequestParam Long fight_idFight, @RequestParam Long related_fighterno2_idFighter) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(related_fighterno2_idFighter));
		mav.addObject("fight_idFight", fight_idFight);
		mav.setViewName("fight/fighterno2/deleteFighterNo2.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@RequestMapping("/deleteFight")
	public String deleteFight(@RequestParam Long idFightKey) {
		Fight fight = fightDAO.findByidFight(idFightKey);
		fightService.deleteFight(fight);
		return "forward:/indexFight";
	}

	/**
	 * Edit an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/editFightResultTable")
	public ModelAndView editFightResultTable(@RequestParam Long fight_idFight, @RequestParam Long resulttable_idResult) {
		ResultTable resulttable = resultTableDAO.findByIdResult(resulttable_idResult);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("resulttable", resulttable);
		mav.setViewName("fight/resulttable/editResultTable.jsp");

		return mav;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@RequestMapping("/deleteFightEventId")
	public ModelAndView deleteFightEventId(@RequestParam Long fight_idFight, @RequestParam Long related_eventid_idEvent) {
		ModelAndView mav = new ModelAndView();

		Fight fight = fightService.deleteFightEventId(fight_idFight, related_eventid_idEvent);

		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Show all Fighter entities by Fight
	 * 
	 */
	@RequestMapping("/listFightFighterNo1")
	public ModelAndView listFightFighterNo1(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/fighterno1/listFighterNo1.jsp");

		return mav;
	}

	/**
	 * Show all SanctionFights entities by Fight
	 * 
	 */
	@RequestMapping("/listFightFightSanctionedFor")
	public ModelAndView listFightFightSanctionedFor(@RequestParam Long idFightKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(idFightKey));
		mav.setViewName("fight/fightsanctionedfor/listFightSanctionedFor.jsp");

		return mav;
	}

	/**
	 * Create a new SanctionFights entity
	 * 
	 */
	@RequestMapping("/newFightFightSanctionedFor")
	public ModelAndView newFightFightSanctionedFor(@RequestParam Long fight_idFight) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("sanctionfights", new SanctionFights());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/fightsanctionedfor/editFightSanctionedFor.jsp");

		return mav;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@RequestMapping("/saveFight")
	public String saveFight(@ModelAttribute Fight fight) {
		fightService.saveFight(fight);
		return "forward:/indexFight";
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@RequestMapping("/deleteFightFighterNo2")
	public ModelAndView deleteFightFighterNo2(@RequestParam Long fight_idFight, @RequestParam Long related_fighterno2_idFighter) {
		ModelAndView mav = new ModelAndView();

		Fight fight = fightService.deleteFightFighterNo2(fight_idFight, related_fighterno2_idFighter);

		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/deleteFightResultTable")
	public ModelAndView deleteFightResultTable(@RequestParam Long fight_idFight, @RequestParam Long related_resulttable_idResult) {
		ModelAndView mav = new ModelAndView();

		Fight fight = fightService.deleteFightResultTable(fight_idFight, related_resulttable_idResult);

		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Delete an existing WeightCatagory entity
	 * 
	 */
	@RequestMapping("/deleteFightWeightCatagoryId")
	public ModelAndView deleteFightWeightCatagoryId(@RequestParam Long fight_idFight, @RequestParam Long related_weightcatagoryid_idWeightCatagory) {
		ModelAndView mav = new ModelAndView();

		Fight fight = fightService.deleteFightWeightCatagoryId(fight_idFight, related_weightcatagoryid_idWeightCatagory);

		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * View an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/selectFightFightSanctionedFor")
	public ModelAndView selectFightFightSanctionedFor(@RequestParam Long fight_idFight, @RequestParam Long fightsanctionedfor_idSanctionfights) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(fightsanctionedfor_idSanctionfights);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("fight/fightsanctionedfor/viewFightSanctionedFor.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/saveFightMediaRelationCollection")
	public ModelAndView saveFightMediaRelationCollection(@RequestParam Long fight_idFight, @ModelAttribute MediaRelation mediarelationcollection) {
		Fight parent_fight = fightService.saveFightMediaRelationCollection(fight_idFight, mediarelationcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("fight", parent_fight);
		mav.setViewName("fight/viewFight.jsp");

		return mav;
	}

	/**
	 * Create a new ResultTable entity
	 * 
	 */
	@RequestMapping("/newFightResultTable")
	public ModelAndView newFightResultTable(@RequestParam Long fight_idFight) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("resulttable", new ResultTable());
		mav.addObject("newFlag", true);
		mav.setViewName("fight/resulttable/editResultTable.jsp");

		return mav;
	}

	/**
	 * Edit an existing WeightCatagory entity
	 * 
	 */
	@RequestMapping("/editFightWeightCatagoryId")
	public ModelAndView editFightWeightCatagoryId(@RequestParam Long fight_idFight, @RequestParam Long weightcatagoryid_idWeightCatagory) {
		WeightCatagory weightcatagory = weightCatagoryDAO.findByIdWeightCatagory(weightcatagoryid_idWeightCatagory);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fight_idFight", fight_idFight);
		mav.addObject("weightcatagory", weightcatagory);
		mav.setViewName("fight/weightcatagoryid/editWeightCatagoryId.jsp");

		return mav;
	}
}