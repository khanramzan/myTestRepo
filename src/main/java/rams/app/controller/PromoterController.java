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
import rams.app.domain.MediaRelation;
import rams.app.domain.Promoter;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.PromoterRepository;
import rams.app.service.PromoterService;

/**
 * Spring MVC controller that handles CRUD requests for Promoter entities
 * 
 */

@Controller("PromoterController")
public class PromoterController {

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
	 * Service injected by Spring that provides CRUD operations for Promoter entities
	 * 
	 */
	@Autowired
	private PromoterService promoterService;

	/**
	 * Entry point to show all Promoter entities
	 * 
	 */
	public String indexPromoter() {
		return "redirect:/indexPromoter";
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@RequestMapping("/deletePromoterPromoterCountry")
	public ModelAndView deletePromoterPromoterCountry(@RequestParam Long promoter_idPromoter, @RequestParam String related_promotercountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		Promoter promoter = promoterService.deletePromoterPromoterCountry(promoter_idPromoter, related_promotercountry_codeCountry);

		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("promoter", promoter);
		mav.setViewName("promoter/viewPromoter.jsp");

		return mav;
	}

	/**
	 * Select the Promoter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePromoter")
	public ModelAndView confirmDeletePromoter(@RequestParam Long idPromoterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(idPromoterKey));
		mav.setViewName("promoter/deletePromoter.jsp");

		return mav;
	}

	/**
	 * View an existing Country entity
	 * 
	 */
	@RequestMapping("/selectPromoterPromoterCountry")
	public ModelAndView selectPromoterPromoterCountry(@RequestParam Long promoter_idPromoter, @RequestParam String promotercountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(promotercountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("country", country);
		mav.setViewName("promoter/promotercountry/viewPromoterCountry.jsp");

		return mav;
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@RequestMapping("/savePromoterPromoterCountry")
	public ModelAndView savePromoterPromoterCountry(@RequestParam Long promoter_idPromoter, @ModelAttribute Country promotercountry) {
		Promoter parent_promoter = promoterService.savePromoterPromoterCountry(promoter_idPromoter, promotercountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("promoter", parent_promoter);
		mav.setViewName("promoter/viewPromoter.jsp");

		return mav;
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@RequestMapping("/deletePromoter")
	public String deletePromoter(@RequestParam Long idPromoterKey) {
		Promoter promoter = promoterDAO.findByIdPromoter(idPromoterKey);
		promoterService.deletePromoter(promoter);
		return "forward:/indexPromoter";
	}

	/**
	 * Create a new Country entity
	 * 
	 */
	@RequestMapping("/newPromoterPromoterCountry")
	public ModelAndView newPromoterPromoterCountry(@RequestParam Long promoter_idPromoter) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("country", new Country());
		mav.addObject("newFlag", true);
		mav.setViewName("promoter/promotercountry/editPromoterCountry.jsp");

		return mav;
	}

	/**
	 * Create a new Promoter entity
	 * 
	 */
	@RequestMapping("/newPromoter")
	public ModelAndView newPromoter() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", new Promoter());
		mav.addObject("newFlag", true);
		mav.setViewName("promoter/editPromoter.jsp");

		return mav;
	}

	/**
	 * Select an existing Promoter entity
	 * 
	 */
	@RequestMapping("/selectPromoter")
	public ModelAndView selectPromoter(@RequestParam Long idPromoterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(idPromoterKey));
		mav.setViewName("promoter/viewPromoter.jsp");

		return mav;
	}

	/**
	 * Edit an existing EventTable entity
	 * 
	 */
	@RequestMapping("/editPromoterEventTableCollection")
	public ModelAndView editPromoterEventTableCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long eventtablecollection_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventtablecollection_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("promoter/eventtablecollection/editEventTableCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/savePromoterMediaRelationCollection")
	public ModelAndView savePromoterMediaRelationCollection(@RequestParam Long promoter_idPromoter, @ModelAttribute MediaRelation mediarelationcollection) {
		Promoter parent_promoter = promoterService.savePromoterMediaRelationCollection(promoter_idPromoter, mediarelationcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("promoter", parent_promoter);
		mav.setViewName("promoter/viewPromoter.jsp");

		return mav;
	}

	/**
	 * Select the child MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePromoterMediaRelationCollection")
	public ModelAndView confirmDeletePromoterMediaRelationCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation));
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.setViewName("promoter/mediarelationcollection/deleteMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing Promoter entity
	 * 
	 */
	@RequestMapping("/editPromoter")
	public ModelAndView editPromoter(@RequestParam Long idPromoterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(idPromoterKey));
		mav.setViewName("promoter/editPromoter.jsp");

		return mav;
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@RequestMapping("/savePromoter")
	public String savePromoter(@ModelAttribute Promoter promoter) {
		promoterService.savePromoter(promoter);
		return "forward:/indexPromoter";
	}

	/**
	 * Create a new EventTable entity
	 * 
	 */
	@RequestMapping("/newPromoterEventTableCollection")
	public ModelAndView newPromoterEventTableCollection(@RequestParam Long promoter_idPromoter) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("eventtable", new EventTable());
		mav.addObject("newFlag", true);
		mav.setViewName("promoter/eventtablecollection/editEventTableCollection.jsp");

		return mav;
	}

	/**
	 * Show all Country entities by Promoter
	 * 
	 */
	@RequestMapping("/listPromoterPromoterCountry")
	public ModelAndView listPromoterPromoterCountry(@RequestParam Long idPromoterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(idPromoterKey));
		mav.setViewName("promoter/promotercountry/listPromoterCountry.jsp");

		return mav;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@RequestMapping("/savePromoterEventTableCollection")
	public ModelAndView savePromoterEventTableCollection(@RequestParam Long promoter_idPromoter, @ModelAttribute EventTable eventtablecollection) {
		Promoter parent_promoter = promoterService.savePromoterEventTableCollection(promoter_idPromoter, eventtablecollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("promoter", parent_promoter);
		mav.setViewName("promoter/viewPromoter.jsp");

		return mav;
	}

	/**
	 * Edit an existing Country entity
	 * 
	 */
	@RequestMapping("/editPromoterPromoterCountry")
	public ModelAndView editPromoterPromoterCountry(@RequestParam Long promoter_idPromoter, @RequestParam String promotercountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(promotercountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("country", country);
		mav.setViewName("promoter/promotercountry/editPromoterCountry.jsp");

		return mav;
	}

	/**
	 * Select the child Country entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePromoterPromoterCountry")
	public ModelAndView confirmDeletePromoterPromoterCountry(@RequestParam Long promoter_idPromoter, @RequestParam String related_promotercountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(related_promotercountry_codeCountry));
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.setViewName("promoter/promotercountry/deletePromoterCountry.jsp");

		return mav;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deletePromoterMediaRelationCollection")
	public ModelAndView deletePromoterMediaRelationCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		Promoter promoter = promoterService.deletePromoterMediaRelationCollection(promoter_idPromoter, related_mediarelationcollection_idMediaRelation);

		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("promoter", promoter);
		mav.setViewName("promoter/viewPromoter.jsp");

		return mav;
	}

	/**
	 * Show all EventTable entities by Promoter
	 * 
	 */
	@RequestMapping("/listPromoterEventTableCollection")
	public ModelAndView listPromoterEventTableCollection(@RequestParam Long idPromoterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(idPromoterKey));
		mav.setViewName("promoter/eventtablecollection/listEventTableCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editPromoterMediaRelationCollection")
	public ModelAndView editPromoterMediaRelationCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("promoter/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities by Promoter
	 * 
	 */
	@RequestMapping("/listPromoterMediaRelationCollection")
	public ModelAndView listPromoterMediaRelationCollection(@RequestParam Long idPromoterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(idPromoterKey));
		mav.setViewName("promoter/mediarelationcollection/listMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/promoterController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Select the child EventTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeletePromoterEventTableCollection")
	public ModelAndView confirmDeletePromoterEventTableCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long related_eventtablecollection_idEvent) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(related_eventtablecollection_idEvent));
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.setViewName("promoter/eventtablecollection/deleteEventTableCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@RequestMapping("/deletePromoterEventTableCollection")
	public ModelAndView deletePromoterEventTableCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long related_eventtablecollection_idEvent) {
		ModelAndView mav = new ModelAndView();

		Promoter promoter = promoterService.deletePromoterEventTableCollection(promoter_idPromoter, related_eventtablecollection_idEvent);

		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("promoter", promoter);
		mav.setViewName("promoter/viewPromoter.jsp");

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
	 * Show all Promoter entities
	 * 
	 */
	@RequestMapping("/indexPromoter")
	public ModelAndView listPromoters() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoters", promoterService.loadPromoters());

		mav.setViewName("promoter/listPromoters.jsp");

		return mav;
	}

	/**
	 * View an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectPromoterMediaRelationCollection")
	public ModelAndView selectPromoterMediaRelationCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("promoter/mediarelationcollection/viewMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newPromoterMediaRelationCollection")
	public ModelAndView newPromoterMediaRelationCollection(@RequestParam Long promoter_idPromoter) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("promoter/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * View an existing EventTable entity
	 * 
	 */
	@RequestMapping("/selectPromoterEventTableCollection")
	public ModelAndView selectPromoterEventTableCollection(@RequestParam Long promoter_idPromoter, @RequestParam Long eventtablecollection_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventtablecollection_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("promoter_idPromoter", promoter_idPromoter);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("promoter/eventtablecollection/viewEventTableCollection.jsp");

		return mav;
	}
}