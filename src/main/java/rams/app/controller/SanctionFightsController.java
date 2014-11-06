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
import rams.app.domain.FightTypes;
import rams.app.domain.SanctionFights;
import rams.app.domain.Sanctioner;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.FightTypesRepository;
import rams.app.rerpository.SanctionFightsRepository;
import rams.app.rerpository.SanctionerRepository;
import rams.app.service.SanctionFightsService;

/**
 * Spring MVC controller that handles CRUD requests for SanctionFights entities
 * 
 */

@Controller("SanctionFightsController")
public class SanctionFightsController {

	/**
	 * DAO injected by Spring that manages Fight entities
	 * 
	 */
	@Autowired
	private FightRepository fightDAO;

	/**
	 * DAO injected by Spring that manages FightTypes entities
	 * 
	 */
	@Autowired
	private FightTypesRepository fightTypesDAO;

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
	 * Service injected by Spring that provides CRUD operations for SanctionFights entities
	 * 
	 */
	@Autowired
	private SanctionFightsService sanctionFightsService;

	/**
	 * Select an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/selectSanctionFights")
	public ModelAndView selectSanctionFights(@RequestParam Long idSanctionfightsKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(idSanctionfightsKey));
		mav.setViewName("sanctionfights/viewSanctionFights.jsp");

		return mav;
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/deleteSanctionFightsSanctioner")
	public ModelAndView deleteSanctionFightsSanctioner(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long related_sanctioner_idSanctioner) {
		ModelAndView mav = new ModelAndView();

		SanctionFights sanctionfights = sanctionFightsService.deleteSanctionFightsSanctioner(sanctionfights_idSanctionfights, related_sanctioner_idSanctioner);

		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("sanctionfights/viewSanctionFights.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/sanctionfightsController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Show all Sanctioner entities by SanctionFights
	 * 
	 */
	@RequestMapping("/listSanctionFightsSanctioner")
	public ModelAndView listSanctionFightsSanctioner(@RequestParam Long idSanctionfightsKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(idSanctionfightsKey));
		mav.setViewName("sanctionfights/sanctioner/listSanctioner.jsp");

		return mav;
	}

	/**
	 * Edit an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/editSanctionFightsFightType")
	public ModelAndView editSanctionFightsFightType(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long fighttype_idFightTypes) {
		FightTypes fighttypes = fightTypesDAO.findByIdFightTypes(fighttype_idFightTypes);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("fighttypes", fighttypes);
		mav.setViewName("sanctionfights/fighttype/editFightType.jsp");

		return mav;
	}

	/**
	 * Edit an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/editSanctionFightsSanctioner")
	public ModelAndView editSanctionFightsSanctioner(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long sanctioner_idSanctioner) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctioner_idSanctioner);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("sanctionfights/sanctioner/editSanctioner.jsp");

		return mav;
	}

	/**
	 * Select the SanctionFights entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctionFights")
	public ModelAndView confirmDeleteSanctionFights(@RequestParam Long idSanctionfightsKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(idSanctionfightsKey));
		mav.setViewName("sanctionfights/deleteSanctionFights.jsp");

		return mav;
	}

	/**
	 * Save an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/saveSanctionFightsFightType")
	public ModelAndView saveSanctionFightsFightType(@RequestParam Long sanctionfights_idSanctionfights, @ModelAttribute FightTypes fighttype) {
		SanctionFights parent_sanctionfights = sanctionFightsService.saveSanctionFightsFightType(sanctionfights_idSanctionfights, fighttype);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctionfights", parent_sanctionfights);
		mav.setViewName("sanctionfights/viewSanctionFights.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@RequestMapping("/deleteSanctionFightsFightCollection")
	public ModelAndView deleteSanctionFightsFightCollection(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long related_fightcollection_idFight) {
		ModelAndView mav = new ModelAndView();

		SanctionFights sanctionfights = sanctionFightsService.deleteSanctionFightsFightCollection(sanctionfights_idSanctionfights, related_fightcollection_idFight);

		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("sanctionfights/viewSanctionFights.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fight entity
	 * 
	 */
	@RequestMapping("/editSanctionFightsFightCollection")
	public ModelAndView editSanctionFightsFightCollection(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long fightcollection_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("fight", fight);
		mav.setViewName("sanctionfights/fightcollection/editFightCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/deleteSanctionFights")
	public String deleteSanctionFights(@RequestParam Long idSanctionfightsKey) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(idSanctionfightsKey);
		sanctionFightsService.deleteSanctionFights(sanctionfights);
		return "forward:/indexSanctionFights";
	}

	/**
	 * Create a new Sanctioner entity
	 * 
	 */
	@RequestMapping("/newSanctionFightsSanctioner")
	public ModelAndView newSanctionFightsSanctioner(@RequestParam Long sanctionfights_idSanctionfights) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctioner", new Sanctioner());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctionfights/sanctioner/editSanctioner.jsp");

		return mav;
	}

	/**
	 * Select the child Fight entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctionFightsFightCollection")
	public ModelAndView confirmDeleteSanctionFightsFightCollection(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long related_fightcollection_idFight) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(related_fightcollection_idFight));
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.setViewName("sanctionfights/fightcollection/deleteFightCollection.jsp");

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
	 * View an existing Fight entity
	 * 
	 */
	@RequestMapping("/selectSanctionFightsFightCollection")
	public ModelAndView selectSanctionFightsFightCollection(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long fightcollection_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("fight", fight);
		mav.setViewName("sanctionfights/fightcollection/viewFightCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/editSanctionFights")
	public ModelAndView editSanctionFights(@RequestParam Long idSanctionfightsKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(idSanctionfightsKey));
		mav.setViewName("sanctionfights/editSanctionFights.jsp");

		return mav;
	}

	/**
	 * Save an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/saveSanctionFights")
	public String saveSanctionFights(@ModelAttribute SanctionFights sanctionfights) {
		sanctionFightsService.saveSanctionFights(sanctionfights);
		return "forward:/indexSanctionFights";
	}

	/**
	 * Delete an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/deleteSanctionFightsFightType")
	public ModelAndView deleteSanctionFightsFightType(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long related_fighttype_idFightTypes) {
		ModelAndView mav = new ModelAndView();

		SanctionFights sanctionfights = sanctionFightsService.deleteSanctionFightsFightType(sanctionfights_idSanctionfights, related_fighttype_idFightTypes);

		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("sanctionfights/viewSanctionFights.jsp");

		return mav;
	}

	/**
	 * Entry point to show all SanctionFights entities
	 * 
	 */
	public String indexSanctionFights() {
		return "redirect:/indexSanctionFights";
	}

	/**
	 * Show all SanctionFights entities
	 * 
	 */
	@RequestMapping("/indexSanctionFights")
	public ModelAndView listSanctionFightss() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfightss", sanctionFightsService.loadSanctionFightss());

		mav.setViewName("sanctionfights/listSanctionFightss.jsp");

		return mav;
	}

	/**
	 * Create a new SanctionFights entity
	 * 
	 */
	@RequestMapping("/newSanctionFights")
	public ModelAndView newSanctionFights() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", new SanctionFights());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctionfights/editSanctionFights.jsp");

		return mav;
	}

	/**
	 * Select the child FightTypes entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctionFightsFightType")
	public ModelAndView confirmDeleteSanctionFightsFightType(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long related_fighttype_idFightTypes) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighttypes", fightTypesDAO.findByIdFightTypes(related_fighttype_idFightTypes));
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.setViewName("sanctionfights/fighttype/deleteFightType.jsp");

		return mav;
	}

	/**
	 * View an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/selectSanctionFightsSanctioner")
	public ModelAndView selectSanctionFightsSanctioner(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long sanctioner_idSanctioner) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctioner_idSanctioner);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("sanctionfights/sanctioner/viewSanctioner.jsp");

		return mav;
	}

	/**
	 * Create a new FightTypes entity
	 * 
	 */
	@RequestMapping("/newSanctionFightsFightType")
	public ModelAndView newSanctionFightsFightType(@RequestParam Long sanctionfights_idSanctionfights) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("fighttypes", new FightTypes());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctionfights/fighttype/editFightType.jsp");

		return mav;
	}

	/**
	 * Create a new Fight entity
	 * 
	 */
	@RequestMapping("/newSanctionFightsFightCollection")
	public ModelAndView newSanctionFightsFightCollection(@RequestParam Long sanctionfights_idSanctionfights) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("fight", new Fight());
		mav.addObject("newFlag", true);
		mav.setViewName("sanctionfights/fightcollection/editFightCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/saveSanctionFightsSanctioner")
	public ModelAndView saveSanctionFightsSanctioner(@RequestParam Long sanctionfights_idSanctionfights, @ModelAttribute Sanctioner sanctioner) {
		SanctionFights parent_sanctionfights = sanctionFightsService.saveSanctionFightsSanctioner(sanctionfights_idSanctionfights, sanctioner);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctionfights", parent_sanctionfights);
		mav.setViewName("sanctionfights/viewSanctionFights.jsp");

		return mav;
	}

	/**
	 * Show all Fight entities by SanctionFights
	 * 
	 */
	@RequestMapping("/listSanctionFightsFightCollection")
	public ModelAndView listSanctionFightsFightCollection(@RequestParam Long idSanctionfightsKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(idSanctionfightsKey));
		mav.setViewName("sanctionfights/fightcollection/listFightCollection.jsp");

		return mav;
	}

	/**
	 * Select the child Sanctioner entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteSanctionFightsSanctioner")
	public ModelAndView confirmDeleteSanctionFightsSanctioner(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long related_sanctioner_idSanctioner) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(related_sanctioner_idSanctioner));
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.setViewName("sanctionfights/sanctioner/deleteSanctioner.jsp");

		return mav;
	}

	/**
	 * Show all FightTypes entities by SanctionFights
	 * 
	 */
	@RequestMapping("/listSanctionFightsFightType")
	public ModelAndView listSanctionFightsFightType(@RequestParam Long idSanctionfightsKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(idSanctionfightsKey));
		mav.setViewName("sanctionfights/fighttype/listFightType.jsp");

		return mav;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@RequestMapping("/saveSanctionFightsFightCollection")
	public ModelAndView saveSanctionFightsFightCollection(@RequestParam Long sanctionfights_idSanctionfights, @ModelAttribute Fight fightcollection) {
		SanctionFights parent_sanctionfights = sanctionFightsService.saveSanctionFightsFightCollection(sanctionfights_idSanctionfights, fightcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("sanctionfights", parent_sanctionfights);
		mav.setViewName("sanctionfights/viewSanctionFights.jsp");

		return mav;
	}

	/**
	 * View an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/selectSanctionFightsFightType")
	public ModelAndView selectSanctionFightsFightType(@RequestParam Long sanctionfights_idSanctionfights, @RequestParam Long fighttype_idFightTypes) {
		FightTypes fighttypes = fightTypesDAO.findByIdFightTypes(fighttype_idFightTypes);

		ModelAndView mav = new ModelAndView();
		mav.addObject("sanctionfights_idSanctionfights", sanctionfights_idSanctionfights);
		mav.addObject("fighttypes", fighttypes);
		mav.setViewName("sanctionfights/fighttype/viewFightType.jsp");

		return mav;
	}
}