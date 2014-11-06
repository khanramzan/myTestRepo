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

import rams.app.domain.Country;
import rams.app.domain.EventTable;
import rams.app.domain.Fight;
import rams.app.domain.MediaRelation;
import rams.app.domain.Promoter;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.PromoterRepository;
import rams.app.service.EventTableService;

/**
 * Spring MVC controller that handles CRUD requests for EventTable entities
 * 
 */

@Controller("EventTableController")
public class EventTableController {

	/**
	 * DAO injected by Spring that manages Country entities
	 * 
	 */
	@Autowired
	private CountryRepository countryDAO;

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
	 * DAO injected by Spring that manages MediaRelation entities
	 * 
	 */
	@Autowired
	private MediaRelationRepository mediaRelationDAO;

	/**
	 * DAO injected by Spring that manages Promoter entities
	 * 
	 */
	@Autowired
	private PromoterRepository promoterDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for EventTable entities
	 * 
	 */
	@Autowired
	private EventTableService eventTableService;

	/**
	 * Create a new Country entity
	 * 
	 */
	@RequestMapping("/newEventTableEventCountry")
	public ModelAndView newEventTableEventCountry(@RequestParam Long eventtable_idEvent) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("country", new Country());
		mav.addObject("newFlag", true);
		mav.setViewName("eventtable/eventcountry/editEventCountry.jsp");

		return mav;
	}

	/**
	 * Edit an existing EventTable entity
	 * 
	 */
	@RequestMapping("/editEventTable")
	public ModelAndView editEventTable(@RequestParam Long idEventKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(idEventKey));
		mav.setViewName("eventtable/editEventTable.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fight entity
	 * 
	 */
	@RequestMapping("/editEventTableFightCollection")
	public ModelAndView editEventTableFightCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long fightcollection_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("fight", fight);
		mav.setViewName("eventtable/fightcollection/editFightCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing Promoter entity
	 * 
	 */
	@RequestMapping("/editEventTableEventPromoter")
	public ModelAndView editEventTableEventPromoter(@RequestParam Long eventtable_idEvent, @RequestParam Long eventpromoter_idPromoter) {
		Promoter promoter = promoterDAO.findByIdPromoter(eventpromoter_idPromoter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("promoter", promoter);
		mav.setViewName("eventtable/eventpromoter/editEventPromoter.jsp");

		return mav;
	}

	/**
	 * Select the child Country entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteEventTableEventCountry")
	public ModelAndView confirmDeleteEventTableEventCountry(@RequestParam Long eventtable_idEvent, @RequestParam String related_eventcountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(related_eventcountry_codeCountry));
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.setViewName("eventtable/eventcountry/deleteEventCountry.jsp");

		return mav;
	}

	/**
	 * Show all Country entities by EventTable
	 * 
	 */
	@RequestMapping("/listEventTableEventCountry")
	public ModelAndView listEventTableEventCountry(@RequestParam Long idEventKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(idEventKey));
		mav.setViewName("eventtable/eventcountry/listEventCountry.jsp");

		return mav;
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newEventTableMediaRelationCollection")
	public ModelAndView newEventTableMediaRelationCollection(@RequestParam Long eventtable_idEvent) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("eventtable/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Select the EventTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteEventTable")
	public ModelAndView confirmDeleteEventTable(@RequestParam Long idEventKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(idEventKey));
		mav.setViewName("eventtable/deleteEventTable.jsp");

		return mav;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@RequestMapping("/deleteEventTable")
	public String deleteEventTable(@RequestParam Long idEventKey) {
		EventTable eventtable = eventTableDAO.findByIdEvent(idEventKey);
		eventTableService.deleteEventTable(eventtable);
		return "forward:/indexEventTable";
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@RequestMapping("/deleteEventTableEventCountry")
	public ModelAndView deleteEventTableEventCountry(@RequestParam Long eventtable_idEvent, @RequestParam String related_eventcountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		EventTable eventtable = eventTableService.deleteEventTableEventCountry(eventtable_idEvent, related_eventcountry_codeCountry);

		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@RequestMapping("/saveEventTable")
	public String saveEventTable(@ModelAttribute EventTable eventtable) {
		eventTableService.saveEventTable(eventtable);
		return "forward:/indexEventTable";
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@RequestMapping("/saveEventTableEventPromoter")
	public ModelAndView saveEventTableEventPromoter(@RequestParam Long eventtable_idEvent, @ModelAttribute Promoter eventpromoter) {
		EventTable parent_eventtable = eventTableService.saveEventTableEventPromoter(eventtable_idEvent, eventpromoter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", parent_eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * View an existing Country entity
	 * 
	 */
	@RequestMapping("/selectEventTableEventCountry")
	public ModelAndView selectEventTableEventCountry(@RequestParam Long eventtable_idEvent, @RequestParam String eventcountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(eventcountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("country", country);
		mav.setViewName("eventtable/eventcountry/viewEventCountry.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@RequestMapping("/deleteEventTableFightCollection")
	public ModelAndView deleteEventTableFightCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long related_fightcollection_idFight) {
		ModelAndView mav = new ModelAndView();

		EventTable eventtable = eventTableService.deleteEventTableFightCollection(eventtable_idEvent, related_fightcollection_idFight);

		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * Select the child Fight entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteEventTableFightCollection")
	public ModelAndView confirmDeleteEventTableFightCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long related_fightcollection_idFight) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(related_fightcollection_idFight));
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.setViewName("eventtable/fightcollection/deleteFightCollection.jsp");

		return mav;
	}

	/**
	 * Show all Promoter entities by EventTable
	 * 
	 */
	@RequestMapping("/listEventTableEventPromoter")
	public ModelAndView listEventTableEventPromoter(@RequestParam Long idEventKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(idEventKey));
		mav.setViewName("eventtable/eventpromoter/listEventPromoter.jsp");

		return mav;
	}

	/**
	 * Create a new Promoter entity
	 * 
	 */
	@RequestMapping("/newEventTableEventPromoter")
	public ModelAndView newEventTableEventPromoter(@RequestParam Long eventtable_idEvent) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("promoter", new Promoter());
		mav.addObject("newFlag", true);
		mav.setViewName("eventtable/eventpromoter/editEventPromoter.jsp");

		return mav;
	}

	/**
	 * Show all EventTable entities
	 * 
	 */
	@RequestMapping("/indexEventTable")
	public ModelAndView listEventTables() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtables", eventTableService.loadEventTables());

		mav.setViewName("eventtable/listEventTables.jsp");

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
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/saveEventTableMediaRelationCollection")
	public ModelAndView saveEventTableMediaRelationCollection(@RequestParam Long eventtable_idEvent, @ModelAttribute MediaRelation mediarelationcollection) {
		EventTable parent_eventtable = eventTableService.saveEventTableMediaRelationCollection(eventtable_idEvent, mediarelationcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", parent_eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * Select the child Promoter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteEventTableEventPromoter")
	public ModelAndView confirmDeleteEventTableEventPromoter(@RequestParam Long eventtable_idEvent, @RequestParam Long related_eventpromoter_idPromoter) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(related_eventpromoter_idPromoter));
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.setViewName("eventtable/eventpromoter/deleteEventPromoter.jsp");

		return mav;
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@RequestMapping("/saveEventTableEventCountry")
	public ModelAndView saveEventTableEventCountry(@RequestParam Long eventtable_idEvent, @ModelAttribute Country eventcountry) {
		EventTable parent_eventtable = eventTableService.saveEventTableEventCountry(eventtable_idEvent, eventcountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", parent_eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * View an existing Fight entity
	 * 
	 */
	@RequestMapping("/selectEventTableFightCollection")
	public ModelAndView selectEventTableFightCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long fightcollection_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("fight", fight);
		mav.setViewName("eventtable/fightcollection/viewFightCollection.jsp");

		return mav;
	}

	/**
	 * Create a new EventTable entity
	 * 
	 */
	@RequestMapping("/newEventTable")
	public ModelAndView newEventTable() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", new EventTable());
		mav.addObject("newFlag", true);
		mav.setViewName("eventtable/editEventTable.jsp");

		return mav;
	}

	/**
	 * Edit an existing Country entity
	 * 
	 */
	@RequestMapping("/editEventTableEventCountry")
	public ModelAndView editEventTableEventCountry(@RequestParam Long eventtable_idEvent, @RequestParam String eventcountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(eventcountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("country", country);
		mav.setViewName("eventtable/eventcountry/editEventCountry.jsp");

		return mav;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@RequestMapping("/saveEventTableFightCollection")
	public ModelAndView saveEventTableFightCollection(@RequestParam Long eventtable_idEvent, @ModelAttribute Fight fightcollection) {
		EventTable parent_eventtable = eventTableService.saveEventTableFightCollection(eventtable_idEvent, fightcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", parent_eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * Select an existing EventTable entity
	 * 
	 */
	@RequestMapping("/selectEventTable")
	public ModelAndView selectEventTable(@RequestParam Long idEventKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(idEventKey));
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * View an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectEventTableMediaRelationCollection")
	public ModelAndView selectEventTableMediaRelationCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("eventtable/mediarelationcollection/viewMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Show all Fight entities by EventTable
	 * 
	 */
	@RequestMapping("/listEventTableFightCollection")
	public ModelAndView listEventTableFightCollection(@RequestParam Long idEventKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(idEventKey));
		mav.setViewName("eventtable/fightcollection/listFightCollection.jsp");

		return mav;
	}

	/**
	 * Select the child MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteEventTableMediaRelationCollection")
	public ModelAndView confirmDeleteEventTableMediaRelationCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation));
		
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.setViewName("eventtable/mediarelationcollection/deleteMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/eventtableController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * View an existing Promoter entity
	 * 
	 */
	@RequestMapping("/selectEventTableEventPromoter")
	public ModelAndView selectEventTableEventPromoter(@RequestParam Long eventtable_idEvent, @RequestParam Long eventpromoter_idPromoter) {
		Promoter promoter = promoterDAO.findByIdPromoter(eventpromoter_idPromoter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("promoter", promoter);
		mav.setViewName("eventtable/eventpromoter/viewEventPromoter.jsp");

		return mav;
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@RequestMapping("/deleteEventTableEventPromoter")
	public ModelAndView deleteEventTableEventPromoter(@RequestParam Long eventtable_idEvent, @RequestParam Long related_eventpromoter_idPromoter) {
		ModelAndView mav = new ModelAndView();

		EventTable eventtable = eventTableService.deleteEventTableEventPromoter(eventtable_idEvent, related_eventpromoter_idPromoter);

		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}

	/**
	 * Create a new Fight entity
	 * 
	 */
	@RequestMapping("/newEventTableFightCollection")
	public ModelAndView newEventTableFightCollection(@RequestParam Long eventtable_idEvent) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("fight", new Fight());
		mav.addObject("newFlag", true);
		mav.setViewName("eventtable/fightcollection/editFightCollection.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities by EventTable
	 * 
	 */
	@RequestMapping("/listEventTableMediaRelationCollection")
	public ModelAndView listEventTableMediaRelationCollection(@RequestParam Long idEventKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(idEventKey));
		mav.setViewName("eventtable/mediarelationcollection/listMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Entry point to show all EventTable entities
	 * 
	 */
	public String indexEventTable() {
		return "redirect:/indexEventTable";
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editEventTableMediaRelationCollection")
	public ModelAndView editEventTableMediaRelationCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("eventtable/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deleteEventTableMediaRelationCollection")
	public ModelAndView deleteEventTableMediaRelationCollection(@RequestParam Long eventtable_idEvent, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		EventTable eventtable = eventTableService.deleteEventTableMediaRelationCollection(eventtable_idEvent, related_mediarelationcollection_idMediaRelation);

		mav.addObject("eventtable_idEvent", eventtable_idEvent);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("eventtable/viewEventTable.jsp");

		return mav;
	}
}