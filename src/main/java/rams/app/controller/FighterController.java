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
import rams.app.domain.Fight;
import rams.app.domain.Fighter;
import rams.app.domain.MediaRelation;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.FighterRepository;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.service.FighterService;

/**
 * Spring MVC controller that handles CRUD requests for Fighter entities
 * 
 */

@Controller("FighterController")
public class FighterController {

	/**
	 * DAO injected by Spring that manages Country entities
	 * 
	 */
	@Autowired
	private CountryRepository countryDAO;

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
	 * Service injected by Spring that provides CRUD operations for Fighter entities
	 * 
	 */
	@Autowired
	private FighterService fighterService;

	/**
	 * View an existing Fight entity
	 * 
	 */
	@RequestMapping("/selectFighterFightCollection")
	public ModelAndView selectFighterFightCollection(@RequestParam Long fighter_idFighter, @RequestParam Long fightcollection_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fight", fight);
		mav.setViewName("fighter/fightcollection/viewFightCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@RequestMapping("/deleteFighterFightCollection")
	public ModelAndView deleteFighterFightCollection(@RequestParam Long fighter_idFighter, @RequestParam Long related_fightcollection_idFight) {
		ModelAndView mav = new ModelAndView();

		Fighter fighter = fighterService.deleteFighterFightCollection(fighter_idFighter, related_fightcollection_idFight);

		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deleteFighterMediaRelationCollection")
	public ModelAndView deleteFighterMediaRelationCollection(@RequestParam Long fighter_idFighter, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		Fighter fighter = fighterService.deleteFighterMediaRelationCollection(fighter_idFighter, related_mediarelationcollection_idMediaRelation);

		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Edit an existing Country entity
	 * 
	 */
	@RequestMapping("/editFighterFightCountry")
	public ModelAndView editFighterFightCountry(@RequestParam Long fighter_idFighter, @RequestParam String fightcountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(fightcountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("country", country);
		mav.setViewName("fighter/fightcountry/editFightCountry.jsp");

		return mav;
	}

	/**
	 * Create a new Fighter entity
	 * 
	 */
	@RequestMapping("/newFighter")
	public ModelAndView newFighter() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", new Fighter());
		mav.addObject("newFlag", true);
		mav.setViewName("fighter/editFighter.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fight entity
	 * 
	 */
	@RequestMapping("/editFighterFightCollection")
	public ModelAndView editFighterFightCollection(@RequestParam Long fighter_idFighter, @RequestParam Long fightcollection_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fight", fight);
		mav.setViewName("fighter/fightcollection/editFightCollection.jsp");

		return mav;
	}

	/**
	 * View an existing Fight entity
	 * 
	 */
	@RequestMapping("/selectFighterFightCollection1")
	public ModelAndView selectFighterFightCollection1(@RequestParam Long fighter_idFighter, @RequestParam Long fightcollection1_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection1_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fight", fight);
		mav.setViewName("fighter/fightcollection1/viewFightCollection1.jsp");

		return mav;
	}

	/**
	 * Select the child MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFighterMediaRelationCollection")
	public ModelAndView confirmDeleteFighterMediaRelationCollection(@RequestParam Long fighter_idFighter, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation));
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.setViewName("fighter/mediarelationcollection/deleteMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * View an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectFighterMediaRelationCollection")
	public ModelAndView selectFighterMediaRelationCollection(@RequestParam Long fighter_idFighter, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("fighter/mediarelationcollection/viewMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/fighterController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Save an existing Country entity
	 * 
	 */
	@RequestMapping("/saveFighterFightCountry")
	public ModelAndView saveFighterFightCountry(@RequestParam Long fighter_idFighter, @ModelAttribute Country fightcountry) {
		Fighter parent_fighter = fighterService.saveFighterFightCountry(fighter_idFighter, fightcountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", parent_fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fighter entity
	 * 
	 */
	@RequestMapping("/editFighter")
	public ModelAndView editFighter(@RequestParam Long idFighterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(idFighterKey));
		mav.setViewName("fighter/editFighter.jsp");

		return mav;
	}

	/**
	 * View an existing Country entity
	 * 
	 */
	@RequestMapping("/selectFighterFightCountry")
	public ModelAndView selectFighterFightCountry(@RequestParam Long fighter_idFighter, @RequestParam String fightcountry_codeCountry) {
		Country country = countryDAO.findByCodeCountry(fightcountry_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("country", country);
		mav.setViewName("fighter/fightcountry/viewFightCountry.jsp");

		return mav;
	}

	/**
	 * Select the child Fight entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFighterFightCollection1")
	public ModelAndView confirmDeleteFighterFightCollection1(@RequestParam Long fighter_idFighter, @RequestParam Long related_fightcollection1_idFight) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(related_fightcollection1_idFight));
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.setViewName("fighter/fightcollection1/deleteFightCollection1.jsp");

		return mav;
	}

	/**
	 * Create a new Fight entity
	 * 
	 */
	@RequestMapping("/newFighterFightCollection")
	public ModelAndView newFighterFightCollection(@RequestParam Long fighter_idFighter) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fight", new Fight());
		mav.addObject("newFlag", true);
		mav.setViewName("fighter/fightcollection/editFightCollection.jsp");

		return mav;
	}

	/**
	 * Create a new Country entity
	 * 
	 */
	@RequestMapping("/newFighterFightCountry")
	public ModelAndView newFighterFightCountry(@RequestParam Long fighter_idFighter) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("country", new Country());
		mav.addObject("newFlag", true);
		mav.setViewName("fighter/fightcountry/editFightCountry.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Fighter entities
	 * 
	 */
	public String indexFighter() {
		return "redirect:/indexFighter";
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/saveFighterMediaRelationCollection")
	public ModelAndView saveFighterMediaRelationCollection(@RequestParam Long fighter_idFighter, @ModelAttribute MediaRelation mediarelationcollection) {
		Fighter parent_fighter = fighterService.saveFighterMediaRelationCollection(fighter_idFighter, mediarelationcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", parent_fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@RequestMapping("/saveFighterFightCollection")
	public ModelAndView saveFighterFightCollection(@RequestParam Long fighter_idFighter, @ModelAttribute Fight fightcollection) {
		Fighter parent_fighter = fighterService.saveFighterFightCollection(fighter_idFighter, fightcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", parent_fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newFighterMediaRelationCollection")
	public ModelAndView newFighterMediaRelationCollection(@RequestParam Long fighter_idFighter) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("fighter/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Select the child Fight entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFighterFightCollection")
	public ModelAndView confirmDeleteFighterFightCollection(@RequestParam Long fighter_idFighter, @RequestParam Long related_fightcollection_idFight) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fight", fightDAO.findByidFight(related_fightcollection_idFight));
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.setViewName("fighter/fightcollection/deleteFightCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing Fight entity
	 * 
	 */
	@RequestMapping("/editFighterFightCollection1")
	public ModelAndView editFighterFightCollection1(@RequestParam Long fighter_idFighter, @RequestParam Long fightcollection1_idFight) {
		Fight fight = fightDAO.findByidFight(fightcollection1_idFight);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fight", fight);
		mav.setViewName("fighter/fightcollection1/editFightCollection1.jsp");

		return mav;
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@RequestMapping("/saveFighter")
	public String saveFighter(@ModelAttribute Fighter fighter) {
		fighterService.saveFighter(fighter);
		return "forward:/indexFighter";
	}

	/**
	 * Select the Fighter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFighter")
	public ModelAndView confirmDeleteFighter(@RequestParam Long idFighterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(idFighterKey));
		mav.setViewName("fighter/deleteFighter.jsp");

		return mav;
	}

	/**
	 * Show all Fight entities by Fighter
	 * 
	 */
	@RequestMapping("/listFighterFightCollection1")
	public ModelAndView listFighterFightCollection1(@RequestParam Long idFighterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(idFighterKey));
		mav.setViewName("fighter/fightcollection1/listFightCollection1.jsp");

		return mav;
	}

	/**
	 * Select an existing Fighter entity
	 * 
	 */
	@RequestMapping("/selectFighter")
	public ModelAndView selectFighter(@RequestParam Long idFighterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(idFighterKey));
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@RequestMapping("/deleteFighter")
	public String deleteFighter(@RequestParam Long idFighterKey) {
		Fighter fighter = fighterDAO.findByidFighter(idFighterKey);
		fighterService.deleteFighter(fighter);
		return "forward:/indexFighter";
	}

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@RequestMapping("/saveFighterFightCollection1")
	public ModelAndView saveFighterFightCollection1(@RequestParam Long fighter_idFighter, @ModelAttribute Fight fightcollection1) {
		Fighter parent_fighter = fighterService.saveFighterFightCollection1(fighter_idFighter, fightcollection1);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", parent_fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editFighterMediaRelationCollection")
	public ModelAndView editFighterMediaRelationCollection(@RequestParam Long fighter_idFighter, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("fighter/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@RequestMapping("/deleteFighterFightCollection1")
	public ModelAndView deleteFighterFightCollection1(@RequestParam Long fighter_idFighter, @RequestParam Long related_fightcollection1_idFight) {
		ModelAndView mav = new ModelAndView();

		Fighter fighter = fighterService.deleteFighterFightCollection1(fighter_idFighter, related_fightcollection1_idFight);

		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities by Fighter
	 * 
	 */
	@RequestMapping("/listFighterMediaRelationCollection")
	public ModelAndView listFighterMediaRelationCollection(@RequestParam Long idFighterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(idFighterKey));
		mav.setViewName("fighter/mediarelationcollection/listMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Show all Fight entities by Fighter
	 * 
	 */
	@RequestMapping("/listFighterFightCollection")
	public ModelAndView listFighterFightCollection(@RequestParam Long idFighterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(idFighterKey));
		mav.setViewName("fighter/fightcollection/listFightCollection.jsp");

		return mav;
	}

	/**
	 * Create a new Fight entity
	 * 
	 */
	@RequestMapping("/newFighterFightCollection1")
	public ModelAndView newFighterFightCollection1(@RequestParam Long fighter_idFighter) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fight", new Fight());
		mav.addObject("newFlag", true);
		mav.setViewName("fighter/fightcollection1/editFightCollection1.jsp");

		return mav;
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@RequestMapping("/deleteFighterFightCountry")
	public ModelAndView deleteFighterFightCountry(@RequestParam Long fighter_idFighter, @RequestParam String related_fightcountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		Fighter fighter = fighterService.deleteFighterFightCountry(fighter_idFighter, related_fightcountry_codeCountry);

		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.addObject("fighter", fighter);
		mav.setViewName("fighter/viewFighter.jsp");

		return mav;
	}

	/**
	 * Show all Country entities by Fighter
	 * 
	 */
	@RequestMapping("/listFighterFightCountry")
	public ModelAndView listFighterFightCountry(@RequestParam Long idFighterKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(idFighterKey));
		mav.setViewName("fighter/fightcountry/listFightCountry.jsp");

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
	 * Select the child Country entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteFighterFightCountry")
	public ModelAndView confirmDeleteFighterFightCountry(@RequestParam Long fighter_idFighter, @RequestParam String related_fightcountry_codeCountry) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(related_fightcountry_codeCountry));
		mav.addObject("fighter_idFighter", fighter_idFighter);
		mav.setViewName("fighter/fightcountry/deleteFightCountry.jsp");

		return mav;
	}

	/**
	 * Show all Fighter entities
	 * 
	 */
	@RequestMapping("/indexFighter")
	public ModelAndView listFighters() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighters", fighterService.loadFighters());

		mav.setViewName("fighter/listFighters.jsp");

		return mav;
	}
}