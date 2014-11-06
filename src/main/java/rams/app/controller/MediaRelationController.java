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
import rams.app.service.MediaRelationService;

/**
 * Spring MVC controller that handles CRUD requests for MediaRelation entities
 * 
 */

@Controller("MediaRelationController")
public class MediaRelationController {

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
	 * Service injected by Spring that provides CRUD operations for MediaRelation entities
	 * 
	 */
	@Autowired
	private MediaRelationService mediaRelationService;

	/**
	 * Edit an existing Fight entity
	 * 
	 */
	@RequestMapping("/editMediaRelationFightMedia")
	public ModelAndView editMediaRelationFightMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long fightmedia_idFight) {
		Fight fight = fightDAO.findByidFight(fightmedia_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("fight", fight);
		mav.setViewName("mediarelation/fightmedia/editFightMedia.jsp");

		return mav;
	}

	/**
	 * View an existing Fight entity
	 * 
	 */
	@RequestMapping("/selectMediaRelationFightMedia")
	public ModelAndView selectMediaRelationFightMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long fightmedia_idFight) {
		Fight fight = fightDAO.findByidFight(fightmedia_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("fight", fight);
		mav.setViewName("mediarelation/fightmedia/viewFightMedia.jsp");

		return mav;
	}

	/**
	 * Show all Sanctioner entities by MediaRelation
	 * 
	 */
	@RequestMapping("/listMediaRelationSanctionerMedia")
	public ModelAndView listMediaRelationSanctionerMedia(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/sanctionermedia/listSanctionerMedia.jsp");

		return mav;
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelationPromoterMedia")
	public ModelAndView deleteMediaRelationPromoterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_promotermedia_idPromoter) {
		ModelAndView mav = new ModelAndView();

		MediaRelation mediarelation = mediaRelationService.deleteMediaRelationPromoterMedia(mediarelation_idMediaRelation, related_promotermedia_idPromoter);

		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelationSanctionerMedia")
	public ModelAndView deleteMediaRelationSanctionerMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_sanctionermedia_idSanctioner) {
		ModelAndView mav = new ModelAndView();

		MediaRelation mediarelation = mediaRelationService.deleteMediaRelationSanctionerMedia(mediarelation_idMediaRelation, related_sanctionermedia_idSanctioner);

		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Create a new Fighter entity
	 * 
	 */
	@RequestMapping("/newMediaRelationFighterMedia")
	public ModelAndView newMediaRelationFighterMedia(@RequestParam Long mediarelation_idMediaRelation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("fighter", new Fighter());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/fightermedia/editFighterMedia.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities
	 * 
	 */
	@RequestMapping("/indexMediaRelation")
	public ModelAndView listMediaRelations() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelations", mediaRelationService.loadMediaRelations());

		mav.setViewName("mediarelation/listMediaRelations.jsp");

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
	 * Select the child Fighter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelationFighterMedia")
	public ModelAndView confirmDeleteMediaRelationFighterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_fightermedia_idFighter) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(related_fightermedia_idFighter));
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.setViewName("mediarelation/fightermedia/deleteFighterMedia.jsp");

		return mav;
	}

	/**
	 * Select the child Fight entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelationFightMedia")
	public ModelAndView confirmDeleteMediaRelationFightMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_fightmedia_idFight) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(related_fightmedia_idFight));
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.setViewName("mediarelation/fightmedia/deleteFightMedia.jsp");

		return mav;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@RequestMapping("/saveMediaRelationFightMedia")
	public ModelAndView saveMediaRelationFightMedia(@RequestParam Long mediarelation_idMediaRelation, @ModelAttribute Fight fightmedia) {
		MediaRelation parent_mediarelation = mediaRelationService.saveMediaRelationFightMedia(mediarelation_idMediaRelation, fightmedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", parent_mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Entry point to show all MediaRelation entities
	 * 
	 */
	public String indexMediaRelation() {
		return "redirect:/indexMediaRelation";
	}

	/**
	 * Show all Promoter entities by MediaRelation
	 * 
	 */
	@RequestMapping("/listMediaRelationPromoterMedia")
	public ModelAndView listMediaRelationPromoterMedia(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/promotermedia/listPromoterMedia.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelationFightMedia")
	public ModelAndView deleteMediaRelationFightMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_fightmedia_idFight) {
		ModelAndView mav = new ModelAndView();

		MediaRelation mediarelation = mediaRelationService.deleteMediaRelationFightMedia(mediarelation_idMediaRelation, related_fightmedia_idFight);

		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@RequestMapping("/saveMediaRelationFighterMedia")
	public ModelAndView saveMediaRelationFighterMedia(@RequestParam Long mediarelation_idMediaRelation, @ModelAttribute Fighter fightermedia) {
		MediaRelation parent_mediarelation = mediaRelationService.saveMediaRelationFighterMedia(mediarelation_idMediaRelation, fightermedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", parent_mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Select the child ResultTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelationResultsMedia")
	public ModelAndView confirmDeleteMediaRelationResultsMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_resultsmedia_idResult) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("resulttable", resultTableDAO.findByIdResult(related_resultsmedia_idResult));
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.setViewName("mediarelation/resultsmedia/deleteResultsMedia.jsp");

		return mav;
	}

	/**
	 * Show all EventTable entities by MediaRelation
	 * 
	 */
	@RequestMapping("/listMediaRelationEventMedia")
	public ModelAndView listMediaRelationEventMedia(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/eventmedia/listEventMedia.jsp");

		return mav;
	}

	/**
	 * Show all MediaTable entities by MediaRelation
	 * 
	 */
	@RequestMapping("/listMediaRelationMediaId")
	public ModelAndView listMediaRelationMediaId(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/mediaid/listMediaId.jsp");

		return mav;
	}

	/**
	 * View an existing Fighter entity
	 * 
	 */
	@RequestMapping("/selectMediaRelationFighterMedia")
	public ModelAndView selectMediaRelationFighterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long fightermedia_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fightermedia_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("fighter", fighter);
		mav.setViewName("mediarelation/fightermedia/viewFighterMedia.jsp");

		return mav;
	}

	/**
	 * Show all ResultTable entities by MediaRelation
	 * 
	 */
	@RequestMapping("/listMediaRelationResultsMedia")
	public ModelAndView listMediaRelationResultsMedia(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/resultsmedia/listResultsMedia.jsp");

		return mav;
	}

	/**
	 * View an existing EventTable entity
	 * 
	 */
	@RequestMapping("/selectMediaRelationEventMedia")
	public ModelAndView selectMediaRelationEventMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long eventmedia_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventmedia_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("mediarelation/eventmedia/viewEventMedia.jsp");

		return mav;
	}

	/**
	 * Edit an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/editMediaRelationSanctionerMedia")
	public ModelAndView editMediaRelationSanctionerMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long sanctionermedia_idSanctioner) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctionermedia_idSanctioner);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("mediarelation/sanctionermedia/editSanctionerMedia.jsp");

		return mav;
	}

	/**
	 * View an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/selectMediaRelationSanctionerMedia")
	public ModelAndView selectMediaRelationSanctionerMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long sanctionermedia_idSanctioner) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctionermedia_idSanctioner);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("mediarelation/sanctionermedia/viewSanctionerMedia.jsp");

		return mav;
	}

	/**
	 * Select the child EventTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelationEventMedia")
	public ModelAndView confirmDeleteMediaRelationEventMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_eventmedia_idEvent) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(related_eventmedia_idEvent));
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.setViewName("mediarelation/eventmedia/deleteEventMedia.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/mediarelationController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * View an existing Promoter entity
	 * 
	 */
	@RequestMapping("/selectMediaRelationPromoterMedia")
	public ModelAndView selectMediaRelationPromoterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long promotermedia_idPromoter) {
		Promoter promoter = promoterDAO.findByIdPromoter(promotermedia_idPromoter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("promoter", promoter);
		mav.setViewName("mediarelation/promotermedia/viewPromoterMedia.jsp");

		return mav;
	}

	/**
	 * Show all Fight entities by MediaRelation
	 * 
	 */
	@RequestMapping("/listMediaRelationFightMedia")
	public ModelAndView listMediaRelationFightMedia(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/fightmedia/listFightMedia.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/saveMediaRelationMediaId")
	public ModelAndView saveMediaRelationMediaId(@RequestParam Long mediarelation_idMediaRelation, @ModelAttribute MediaTable mediaid) {
		MediaRelation parent_mediarelation = mediaRelationService.saveMediaRelationMediaId(mediarelation_idMediaRelation, mediaid);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", parent_mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Create a new MediaTable entity
	 * 
	 */
	@RequestMapping("/newMediaRelationMediaId")
	public ModelAndView newMediaRelationMediaId(@RequestParam Long mediarelation_idMediaRelation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediatable", new MediaTable());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/mediaid/editMediaId.jsp");

		return mav;
	}

	/**
	 * View an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/selectMediaRelationResultsMedia")
	public ModelAndView selectMediaRelationResultsMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long resultsmedia_idResult) {
		ResultTable resulttable = resultTableDAO.findByIdResult(resultsmedia_idResult);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("resulttable", resulttable);
		mav.setViewName("mediarelation/resultsmedia/viewResultsMedia.jsp");

		return mav;
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/saveMediaRelationSanctionerMedia")
	public ModelAndView saveMediaRelationSanctionerMedia(@RequestParam Long mediarelation_idMediaRelation, @ModelAttribute Sanctioner sanctionermedia) {
		MediaRelation parent_mediarelation = mediaRelationService.saveMediaRelationSanctionerMedia(mediarelation_idMediaRelation, sanctionermedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", parent_mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/saveMediaRelation")
	public String saveMediaRelation(@ModelAttribute MediaRelation mediarelation) {
		mediaRelationService.saveMediaRelation(mediarelation);
		return "forward:/indexMediaRelation";
	}

	/**
	 * Delete an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelationResultsMedia")
	public ModelAndView deleteMediaRelationResultsMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_resultsmedia_idResult) {
		ModelAndView mav = new ModelAndView();

		MediaRelation mediarelation = mediaRelationService.deleteMediaRelationResultsMedia(mediarelation_idMediaRelation, related_resultsmedia_idResult);

		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Select the child Sanctioner entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelationSanctionerMedia")
	public ModelAndView confirmDeleteMediaRelationSanctionerMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_sanctionermedia_idSanctioner) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(related_sanctionermedia_idSanctioner));
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.setViewName("mediarelation/sanctionermedia/deleteSanctionerMedia.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/editMediaRelationMediaId")
	public ModelAndView editMediaRelationMediaId(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long mediaid_idMedia) {
		MediaTable mediatable = mediaTableDAO.findByIdMedia(mediaid_idMedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediatable", mediatable);
		mav.setViewName("mediarelation/mediaid/editMediaId.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editMediaRelation")
	public ModelAndView editMediaRelation(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/editMediaRelation.jsp");

		return mav;
	}

	/**
	 * Create a new Promoter entity
	 * 
	 */
	@RequestMapping("/newMediaRelationPromoterMedia")
	public ModelAndView newMediaRelationPromoterMedia(@RequestParam Long mediarelation_idMediaRelation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("promoter", new Promoter());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/promotermedia/editPromoterMedia.jsp");

		return mav;
	}

	/**
	 * Create a new EventTable entity
	 * 
	 */
	@RequestMapping("/newMediaRelationEventMedia")
	public ModelAndView newMediaRelationEventMedia(@RequestParam Long mediarelation_idMediaRelation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("eventtable", new EventTable());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/eventmedia/editEventMedia.jsp");

		return mav;
	}

	/**
	 * Select the MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelation")
	public ModelAndView confirmDeleteMediaRelation(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/deleteMediaRelation.jsp");

		return mav;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@RequestMapping("/saveMediaRelationEventMedia")
	public ModelAndView saveMediaRelationEventMedia(@RequestParam Long mediarelation_idMediaRelation, @ModelAttribute EventTable eventmedia) {
		MediaRelation parent_mediarelation = mediaRelationService.saveMediaRelationEventMedia(mediarelation_idMediaRelation, eventmedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", parent_mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelation")
	public String deleteMediaRelation(@RequestParam Long idMediaRelationKey) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey);
		mediaRelationService.deleteMediaRelation(mediarelation);
		return "forward:/indexMediaRelation";
	}

	/**
	 * Edit an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/editMediaRelationResultsMedia")
	public ModelAndView editMediaRelationResultsMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long resultsmedia_idResult) {
		ResultTable resulttable = resultTableDAO.findByIdResult(resultsmedia_idResult);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("resulttable", resulttable);
		mav.setViewName("mediarelation/resultsmedia/editResultsMedia.jsp");

		return mav;
	}

	/**
	 * Edit an existing Promoter entity
	 * 
	 */
	@RequestMapping("/editMediaRelationPromoterMedia")
	public ModelAndView editMediaRelationPromoterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long promotermedia_idPromoter) {
		Promoter promoter = promoterDAO.findByIdPromoter(promotermedia_idPromoter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("promoter", promoter);
		mav.setViewName("mediarelation/promotermedia/editPromoterMedia.jsp");

		return mav;
	}

	/**
	 * Save an existing ResultTable entity
	 * 
	 */
	@RequestMapping("/saveMediaRelationResultsMedia")
	public ModelAndView saveMediaRelationResultsMedia(@RequestParam Long mediarelation_idMediaRelation, @ModelAttribute ResultTable resultsmedia) {
		MediaRelation parent_mediarelation = mediaRelationService.saveMediaRelationResultsMedia(mediarelation_idMediaRelation, resultsmedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", parent_mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Create a new Sanctioner entity
	 * 
	 */
	@RequestMapping("/newMediaRelationSanctionerMedia")
	public ModelAndView newMediaRelationSanctionerMedia(@RequestParam Long mediarelation_idMediaRelation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("sanctioner", new Sanctioner());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/sanctionermedia/editSanctionerMedia.jsp");

		return mav;
	}

	/**
	 * Create a new ResultTable entity
	 * 
	 */
	@RequestMapping("/newMediaRelationResultsMedia")
	public ModelAndView newMediaRelationResultsMedia(@RequestParam Long mediarelation_idMediaRelation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("resulttable", new ResultTable());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/resultsmedia/editResultsMedia.jsp");

		return mav;
	}

	/**
	 * Select the child Promoter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelationPromoterMedia")
	public ModelAndView confirmDeleteMediaRelationPromoterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_promotermedia_idPromoter) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(related_promotermedia_idPromoter));
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.setViewName("mediarelation/promotermedia/deletePromoterMedia.jsp");

		return mav;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelationEventMedia")
	public ModelAndView deleteMediaRelationEventMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_eventmedia_idEvent) {
		ModelAndView mav = new ModelAndView();

		MediaRelation mediarelation = mediaRelationService.deleteMediaRelationEventMedia(mediarelation_idMediaRelation, related_eventmedia_idEvent);

		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newMediaRelation")
	public ModelAndView newMediaRelation() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/editMediaRelation.jsp");

		return mav;
	}

	/**
	 * Select an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectMediaRelation")
	public ModelAndView selectMediaRelation(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelationFighterMedia")
	public ModelAndView deleteMediaRelationFighterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_fightermedia_idFighter) {
		ModelAndView mav = new ModelAndView();

		MediaRelation mediarelation = mediaRelationService.deleteMediaRelationFighterMedia(mediarelation_idMediaRelation, related_fightermedia_idFighter);

		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fighter entity
	 * 
	 */
	@RequestMapping("/editMediaRelationFighterMedia")
	public ModelAndView editMediaRelationFighterMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long fightermedia_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fightermedia_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("fighter", fighter);
		mav.setViewName("mediarelation/fightermedia/editFighterMedia.jsp");

		return mav;
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@RequestMapping("/saveMediaRelationPromoterMedia")
	public ModelAndView saveMediaRelationPromoterMedia(@RequestParam Long mediarelation_idMediaRelation, @ModelAttribute Promoter promotermedia) {
		MediaRelation parent_mediarelation = mediaRelationService.saveMediaRelationPromoterMedia(mediarelation_idMediaRelation, promotermedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", parent_mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Show all Fighter entities by MediaRelation
	 * 
	 */
	@RequestMapping("/listMediaRelationFighterMedia")
	public ModelAndView listMediaRelationFighterMedia(@RequestParam Long idMediaRelationKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(idMediaRelationKey));
		mav.setViewName("mediarelation/fightermedia/listFighterMedia.jsp");

		return mav;
	}

	/**
	 * View an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/selectMediaRelationMediaId")
	public ModelAndView selectMediaRelationMediaId(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long mediaid_idMedia) {
		MediaTable mediatable = mediaTableDAO.findByIdMedia(mediaid_idMedia);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediatable", mediatable);
		mav.setViewName("mediarelation/mediaid/viewMediaId.jsp");

		return mav;
	}

	/**
	 * Select the child MediaTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaRelationMediaId")
	public ModelAndView confirmDeleteMediaRelationMediaId(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_mediaid_idMedia) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediatable", mediaTableDAO.findByIdMedia(related_mediaid_idMedia));
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.setViewName("mediarelation/mediaid/deleteMediaId.jsp");

		return mav;
	}

	/**
	 * Create a new Fight entity
	 * 
	 */
	@RequestMapping("/newMediaRelationFightMedia")
	public ModelAndView newMediaRelationFightMedia(@RequestParam Long mediarelation_idMediaRelation) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("fight", new Fight());
		mav.addObject("newFlag", true);
		mav.setViewName("mediarelation/fightmedia/editFightMedia.jsp");

		return mav;
	}

	/**
	 * Delete an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/deleteMediaRelationMediaId")
	public ModelAndView deleteMediaRelationMediaId(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long related_mediaid_idMedia) {
		ModelAndView mav = new ModelAndView();

		MediaRelation mediarelation = mediaRelationService.deleteMediaRelationMediaId(mediarelation_idMediaRelation, related_mediaid_idMedia);

		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediarelation/viewMediaRelation.jsp");

		return mav;
	}

	/**
	 * Edit an existing EventTable entity
	 * 
	 */
	@RequestMapping("/editMediaRelationEventMedia")
	public ModelAndView editMediaRelationEventMedia(@RequestParam Long mediarelation_idMediaRelation, @RequestParam Long eventmedia_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventmedia_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediarelation_idMediaRelation", mediarelation_idMediaRelation);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("mediarelation/eventmedia/editEventMedia.jsp");

		return mav;
	}
}