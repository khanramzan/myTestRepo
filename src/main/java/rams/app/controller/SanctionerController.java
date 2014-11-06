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
import rams.app.domain.MediaRelation;
import rams.app.domain.SanctionFights;
import rams.app.domain.Sanctioner;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.SanctionFightsRepository;
import rams.app.rerpository.SanctionerRepository;
import rams.app.service.SanctionerService;

/**
 * Spring MVC controller that handles CRUD requests for Sanctioner entities
 * 
 */

@Controller("SanctionerController")
public class SanctionerController {

	/**
	 * DAO injected by Spring that manages Country entities
	 * 
	 */
	@Autowired
	private CountryRepository countryDAO;

	/**
	 * DAO injected by Spring that manages MediaRelation entities
	 * 
	 */
	@Autowired
	private MediaRelationRepository mediaRelationDAO;

	/**
	 * DAO injected by Spring that manages SanctionFights entities
	 * 
	 */
	@Autowired
	private SanctionFightsRepository sanctionFightsDAO;

	/**
	 * DAO injected by Spring that manages Sanctioner entities
	 * 
	 */
	@Autowired
	private SanctionerRepository sanctionerDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Sanctioner entities
	 * 
	 */
	@Autowired
	private SanctionerService sanctionerService;

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deleteSanctionerMediaRelationCollection")
	public ModelAndView deleteSanctionerMediaRelationCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		Sanctioner sanctioner = sanctionerService.deleteSanctionerMediaRelationCollection(sanctioner_idSanctioner, related_mediarelationcollection_idMediaRelation);

		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/saveSanctionerMediaRelationCollection")
	public ModelAndView saveSanctionerMediaRelationCollection(@RequestParam Long sanctioner_idSanctioner, @ModelAttribute MediaRelation mediarelationcollection) {
		Sanctioner parent_sanctioner = sanctionerService.saveSanctionerMediaRelationCollection(sanctioner_idSanctioner, mediarelationcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctioner", parent_sanctioner);
		mav.setViewName("sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Create a new Country entity
	 * 
	 */
	@RequestMapping("/newSanctionerSanctionerCountry")
	public ModelAndView newSanctionerSanctionerCountry(@RequestParam Long sanctioner_idSanctioner) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("country", new Country());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctioner/sanctionercountry/editSanctionerCountry.jsp");

		return mav;
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/deleteSanctioner")
	public String deleteSanctioner(@RequestParam Long idSanctionerKey) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(idSanctionerKey);
		sanctionerService.deleteSanctioner(sanctioner);
		return "forward:/indexSanctioner";
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/deleteSanctionerSanctionFightsCollection")
	public ModelAndView deleteSanctionerSanctionFightsCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long related_sanctionfightscollection_idSanctionfights) {
		ModelAndView mav = new ModelAndView();

		Sanctioner sanctioner = sanctionerService.deleteSanctionerSanctionFightsCollection(sanctioner_idSanctioner, related_sanctionfightscollection_idSanctionfights);

		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editSanctionerMediaRelationCollection")
	public ModelAndView editSanctionerMediaRelationCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("sanctioner/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@RequestMapping("/saveSanctionerSanctionerCountry")
	public ModelAndView saveSanctionerSanctionerCountry(@RequestParam Long sanctioner_idSanctioner, @ModelAttribute Country sanctionercountry) {
		Sanctioner parent_sanctioner = sanctionerService.saveSanctionerSanctionerCountry(sanctioner_idSanctioner, sanctionercountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctioner", parent_sanctioner);
		mav.setViewName("sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newSanctionerMediaRelationCollection")
	public ModelAndView newSanctionerMediaRelationCollection(@RequestParam Long sanctioner_idSanctioner) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctioner/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Select the Sanctioner entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctioner")
	public ModelAndView confirmDeleteSanctioner(@RequestParam Long idSanctionerKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(idSanctionerKey));
		mav.setViewName("sanctioner/deleteSanctioner.jsp");

		return mav;
	}

	/**
	 * Show all Country entities by Sanctioner
	 * 
	 */
	@RequestMapping("/listSanctionerSanctionerCountry")
	public ModelAndView listSanctionerSanctionerCountry(@RequestParam Long idSanctionerKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(idSanctionerKey));
		mav.setViewName("sanctioner/sanctionercountry/listSanctionerCountry.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/sanctionerController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@RequestMapping("/deleteSanctionerSanctionerCountry")
	public ModelAndView deleteSanctionerSanctionerCountry(@RequestParam Long sanctioner_idSanctioner, @RequestParam String related_sanctionercountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		Sanctioner sanctioner = sanctionerService.deleteSanctionerSanctionerCountry(sanctioner_idSanctioner, related_sanctionercountry_codeCountry);

		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/saveSanctionerSanctionFightsCollection")
	public ModelAndView saveSanctionerSanctionFightsCollection(@RequestParam Long sanctioner_idSanctioner, @ModelAttribute SanctionFights sanctionfightscollection) {
		Sanctioner parent_sanctioner = sanctionerService.saveSanctionerSanctionFightsCollection(sanctioner_idSanctioner, sanctionfightscollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctioner", parent_sanctioner);
		mav.setViewName("sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Sanctioner entities
	 * 
	 */
	public String indexSanctioner() {
		return "redirect:/indexSanctioner";
	}

	/**
	 * Create a new SanctionFights entity
	 * 
	 */
	@RequestMapping("/newSanctionerSanctionFightsCollection")
	public ModelAndView newSanctionerSanctionFightsCollection(@RequestParam Long sanctioner_idSanctioner) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctionfights", new SanctionFights());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctioner/sanctionfightscollection/editSanctionFightsCollection.jsp");

		return mav;
	}

	/**
	 * Select an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/selectSanctioner")
	public ModelAndView selectSanctioner(@RequestParam Long idSanctionerKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(idSanctionerKey));
		mav.setViewName("sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities by Sanctioner
	 * 
	 */
	@RequestMapping("/listSanctionerMediaRelationCollection")
	public ModelAndView listSanctionerMediaRelationCollection(@RequestParam Long idSanctionerKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(idSanctionerKey));
		mav.setViewName("sanctioner/mediarelationcollection/listMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/saveSanctioner")
	public String saveSanctioner(@ModelAttribute Sanctioner sanctioner) {
		sanctionerService.saveSanctioner(sanctioner);
		return "forward:/indexSanctioner";
	}

	/**
	 * Select the child MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctionerMediaRelationCollection")
	public ModelAndView confirmDeleteSanctionerMediaRelationCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation));
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.setViewName("sanctioner/mediarelationcollection/deleteMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/editSanctioner")
	public ModelAndView editSanctioner(@RequestParam Long idSanctionerKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(idSanctionerKey));
		mav.setViewName("sanctioner/editSanctioner.jsp");

		return mav;
	}

	/**
	 * Select the child Country entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctionerSanctionerCountry")
	public ModelAndView confirmDeleteSanctionerSanctionerCountry(@RequestParam Long sanctioner_idSanctioner, @RequestParam String related_sanctionercountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(related_sanctionercountry_codeCountry));
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.setViewName("sanctioner/sanctionercountry/deleteSanctionerCountry.jsp");

		return mav;
	}

	/**
	 * Show all Sanctioner entities
	 * 
	 */
	@RequestMapping("/indexSanctioner")
	public ModelAndView listSanctioners() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioners", sanctionerService.loadSanctioners());

		mav.setViewName("sanctioner/listSanctioners.jsp");

		return mav;
	}

	/**
	 * Edit an existing Country entity
	 * 
	 */
	@RequestMapping("/editSanctionerSanctionerCountry")
	public ModelAndView editSanctionerSanctionerCountry(@RequestParam Long sanctioner_idSanctioner, @RequestParam String sanctionercountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(sanctionercountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("country", country);
		mav.setViewName("sanctioner/sanctionercountry/editSanctionerCountry.jsp");

		return mav;
	}

	/**
	 * View an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/selectSanctionerSanctionFightsCollection")
	public ModelAndView selectSanctionerSanctionFightsCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long sanctionfightscollection_idSanctionfights) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(sanctionfightscollection_idSanctionfights);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("sanctioner/sanctionfightscollection/viewSanctionFightsCollection.jsp");

		return mav;
	}

	/**
	 * Select the child SanctionFights entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctionerSanctionFightsCollection")
	public ModelAndView confirmDeleteSanctionerSanctionFightsCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long related_sanctionfightscollection_idSanctionfights) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(related_sanctionfightscollection_idSanctionfights));
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.setViewName("sanctioner/sanctionfightscollection/deleteSanctionFightsCollection.jsp");

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
	 * Show all SanctionFights entities by Sanctioner
	 * 
	 */
	@RequestMapping("/listSanctionerSanctionFightsCollection")
	public ModelAndView listSanctionerSanctionFightsCollection(@RequestParam Long idSanctionerKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(idSanctionerKey));
		mav.setViewName("sanctioner/sanctionfightscollection/listSanctionFightsCollection.jsp");

		return mav;
	}

	/**
	 * View an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectSanctionerMediaRelationCollection")
	public ModelAndView selectSanctionerMediaRelationCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("sanctioner/mediarelationcollection/viewMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * View an existing Country entity
	 * 
	 */
	@RequestMapping("/selectSanctionerSanctionerCountry")
	public ModelAndView selectSanctionerSanctionerCountry(@RequestParam Long sanctioner_idSanctioner, @RequestParam String sanctionercountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(sanctionercountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("country", country);
		mav.setViewName("sanctioner/sanctionercountry/viewSanctionerCountry.jsp");

		return mav;
	}

	/**
	 * Create a new Sanctioner entity
	 * 
	 */
	@RequestMapping("/newSanctioner")
	public ModelAndView newSanctioner() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", new Sanctioner());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctioner/editSanctioner.jsp");

		return mav;
	}

	/**
	 * Edit an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/editSanctionerSanctionFightsCollection")
	public ModelAndView editSanctionerSanctionFightsCollection(@RequestParam Long sanctioner_idSanctioner, @RequestParam Long sanctionfightscollection_idSanctionfights) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(sanctionfightscollection_idSanctionfights);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctioner_idSanctioner", sanctioner_idSanctioner);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("sanctioner/sanctionfightscollection/editSanctionFightsCollection.jsp");

		return mav;
	}
}