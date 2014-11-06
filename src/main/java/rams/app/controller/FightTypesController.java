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





import rams.app.domain.FightTypes;
import rams.app.domain.SanctionFights;
import rams.app.rerpository.FightTypesRepository;
import rams.app.rerpository.SanctionFightsRepository;
import rams.app.service.FightTypesService;

/**
 * Spring MVC controller that handles CRUD requests for FightTypes entities
 * 
 */

@Controller("FightTypesController")
public class FightTypesController {

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
	 * Service injected by Spring that provides CRUD operations for FightTypes entities
	 * 
	 */
	@Autowired
	private FightTypesService fightTypesService;

	/**
	 * Create a new FightTypes entity
	 * 
	 */
	@RequestMapping("/newFightTypes")
	public ModelAndView newFightTypes() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighttypes", new FightTypes());
		mav.addObject("newFlag", true);
		mav.setViewName("fighttypes/editFightTypes.jsp");

		return mav;
	}

	/**
	 * View an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/selectFightTypesSanctionFightsCollection")
	public ModelAndView selectFightTypesSanctionFightsCollection(@RequestParam Long fighttypes_idFightTypes, @RequestParam Long sanctionfightscollection_idSanctionfights) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(sanctionfightscollection_idSanctionfights);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighttypes_idFightTypes", fighttypes_idFightTypes);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("fighttypes/sanctionfightscollection/viewSanctionFightsCollection.jsp");

		return mav;
	}

	/**
	 * Select the FightTypes entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightTypes")
	public ModelAndView confirmDeleteFightTypes(@RequestParam Long idFightTypesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighttypes", fightTypesDAO.findByIdFightTypes(idFightTypesKey));
		mav.setViewName("fighttypes/deleteFightTypes.jsp");

		return mav;
	}

	/**
	 * Delete an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/deleteFightTypes")
	public String deleteFightTypes(@RequestParam Long idFightTypesKey) {
		FightTypes fighttypes = fightTypesDAO.findByIdFightTypes(idFightTypesKey);
		fightTypesService.deleteFightTypes(fighttypes);
		return "forward:/indexFightTypes";
	}

	/**
	 * Select an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/selectFightTypes")
	public ModelAndView selectFightTypes(@RequestParam Long idFightTypesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighttypes", fightTypesDAO.findByIdFightTypes(idFightTypesKey));
		mav.setViewName("fighttypes/viewFightTypes.jsp");

		return mav;
	}

	/**
	 * Entry point to show all FightTypes entities
	 * 
	 */
	public String indexFightTypes() {
		return "redirect:/indexFightTypes";
	}

	/**
	 * Edit an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/editFightTypesSanctionFightsCollection")
	public ModelAndView editFightTypesSanctionFightsCollection(@RequestParam Long fighttypes_idFightTypes, @RequestParam Long sanctionfightscollection_idSanctionfights) {
		SanctionFights sanctionfights = sanctionFightsDAO.findByIdSanctionfights(sanctionfightscollection_idSanctionfights);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighttypes_idFightTypes", fighttypes_idFightTypes);
		mav.addObject("sanctionfights", sanctionfights);
		mav.setViewName("fighttypes/sanctionfightscollection/editSanctionFightsCollection.jsp");

		return mav;
	}

	/**
	 * Create a new SanctionFights entity
	 * 
	 */
	@RequestMapping("/newFightTypesSanctionFightsCollection")
	public ModelAndView newFightTypesSanctionFightsCollection(@RequestParam Long fighttypes_idFightTypes) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fighttypes_idFightTypes", fighttypes_idFightTypes);
		mav.addObject("sanctionfights", new SanctionFights());
		mav.addObject("newFlag", true);
		mav.setViewName("fighttypes/sanctionfightscollection/editSanctionFightsCollection.jsp");

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
	 * Save an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/saveFightTypesSanctionFightsCollection")
	public ModelAndView saveFightTypesSanctionFightsCollection(@RequestParam Long fighttypes_idFightTypes, @ModelAttribute SanctionFights sanctionfightscollection) {
		FightTypes parent_fighttypes = fightTypesService.saveFightTypesSanctionFightsCollection(fighttypes_idFightTypes, sanctionfightscollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighttypes_idFightTypes", fighttypes_idFightTypes);
		mav.addObject("fighttypes", parent_fighttypes);
		mav.setViewName("fighttypes/viewFightTypes.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/fighttypesController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Edit an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/editFightTypes")
	public ModelAndView editFightTypes(@RequestParam Long idFightTypesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighttypes", fightTypesDAO.findByIdFightTypes(idFightTypesKey));
		mav.setViewName("fighttypes/editFightTypes.jsp");

		return mav;
	}

	/**
	 * Show all FightTypes entities
	 * 
	 */
	@RequestMapping("/indexFightTypes")
	public ModelAndView listFightTypess() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighttypess", fightTypesService.loadFightTypess());

		mav.setViewName("fighttypes/listFightTypess.jsp");

		return mav;
	}

	/**
	 * Select the child SanctionFights entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFightTypesSanctionFightsCollection")
	public ModelAndView confirmDeleteFightTypesSanctionFightsCollection(@RequestParam Long fighttypes_idFightTypes, @RequestParam Long related_sanctionfightscollection_idSanctionfights) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctionfights", sanctionFightsDAO.findByIdSanctionfights(related_sanctionfightscollection_idSanctionfights));
		mav.addObject("fighttypes_idFightTypes", fighttypes_idFightTypes);
		mav.setViewName("fighttypes/sanctionfightscollection/deleteSanctionFightsCollection.jsp");

		return mav;
	}

	/**
	 * Show all SanctionFights entities by FightTypes
	 * 
	 */
	@RequestMapping("/listFightTypesSanctionFightsCollection")
	public ModelAndView listFightTypesSanctionFightsCollection(@RequestParam Long idFightTypesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighttypes", fightTypesDAO.findByIdFightTypes(idFightTypesKey));
		mav.setViewName("fighttypes/sanctionfightscollection/listSanctionFightsCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing SanctionFights entity
	 * 
	 */
	@RequestMapping("/deleteFightTypesSanctionFightsCollection")
	public ModelAndView deleteFightTypesSanctionFightsCollection(@RequestParam Long fighttypes_idFightTypes, @RequestParam Long related_sanctionfightscollection_idSanctionfights) {
		ModelAndView mav = new ModelAndView();

		FightTypes fighttypes = fightTypesService.deleteFightTypesSanctionFightsCollection(fighttypes_idFightTypes, related_sanctionfightscollection_idSanctionfights);

		mav.addObject("fighttypes_idFightTypes", fighttypes_idFightTypes);
		mav.addObject("fighttypes", fighttypes);
		mav.setViewName("fighttypes/viewFightTypes.jsp");

		return mav;
	}

	/**
	 * Save an existing FightTypes entity
	 * 
	 */
	@RequestMapping("/saveFightTypes")
	public String saveFightTypes(@ModelAttribute FightTypes fighttypes) {
		fightTypesService.saveFightTypes(fighttypes);
		return "forward:/indexFightTypes";
	}
}