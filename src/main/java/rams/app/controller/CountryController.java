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

import rams.app.domain.City;
import rams.app.domain.Country;
import rams.app.domain.EventTable;
import rams.app.domain.Fighter;
import rams.app.domain.Promoter;
import rams.app.domain.Sanctioner;
import rams.app.rerpository.CityRepository;
import rams.app.rerpository.CountryRepository;
import rams.app.rerpository.EventTableRepository;
import rams.app.rerpository.FighterRepository;
import rams.app.rerpository.PromoterRepository;
import rams.app.rerpository.SanctionerRepository;
import rams.app.service.CountryService;

/**
 * Spring MVC controller that handles CRUD requests for Country entities
 * 
 */

@Controller("CountryController")
public class CountryController {

	/**
	 * DAO injected by Spring that manages City entities
	 * 
	 */
	@Autowired
	private CityRepository cityDAO;

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
	 * DAO injected by Spring that manages Fighter entities
	 * 
	 */
	@Autowired
	private FighterRepository fighterDAO;

	/**
	 * DAO injected by Spring that manages Promoter entities
	 * 
	 */
	@Autowired
	private PromoterRepository promoterDAO;

	/**
	 * DAO injected by Spring that manages Sanctioner entities
	 * 
	 */
	@Autowired
	private SanctionerRepository sanctionerDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Country entities
	 * 
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * Edit an existing Country entity
	 * 
	 */
	@RequestMapping("/editCountry")
	public ModelAndView editCountry(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/editCountry.jsp");

		return mav;
	}

	/**
	 * Delete an existing EventTable entity
	 * 
	 */
	@RequestMapping("/deleteCountryEventTableCollection")
	public ModelAndView deleteCountryEventTableCollection(@RequestParam String country_codeCountry, @RequestParam Long related_eventtablecollection_idEvent) {
		ModelAndView mav = new ModelAndView();

		Country country = countryService.deleteCountryEventTableCollection(country_codeCountry, related_eventtablecollection_idEvent);

		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Show all Promoter entities by Country
	 * 
	 */
	@RequestMapping("/listCountryPromoterCollection")
	public ModelAndView listCountryPromoterCollection(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/promotercollection/listPromoterCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing EventTable entity
	 * 
	 */
	@RequestMapping("/saveCountryEventTableCollection")
	public ModelAndView saveCountryEventTableCollection(@RequestParam String country_codeCountry, @ModelAttribute EventTable eventtablecollection) {
		Country parent_country = countryService.saveCountryEventTableCollection(country_codeCountry, eventtablecollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", parent_country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Create a new Country entity
	 * 
	 */
	@RequestMapping("/newCountry")
	public ModelAndView newCountry() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", new Country());
		mav.addObject("newFlag", true);
		mav.setViewName("country/editCountry.jsp");

		return mav;
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@RequestMapping("/deleteCountry")
	public String deleteCountry(@RequestParam String codeCountryKey) {
		Country country = countryDAO.findByCodeCountry(codeCountryKey);
		countryService.deleteCountry(country);
		return "forward:/indexCountry";
	}

	/**
	 * View an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/selectCountrySanctionerCollection")
	public ModelAndView selectCountrySanctionerCollection(@RequestParam String country_codeCountry, @RequestParam Long sanctionercollection_idSanctioner) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctionercollection_idSanctioner);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("country/sanctionercollection/viewSanctionerCollection.jsp");

		return mav;
	}

	/**
	 * Select the child Fighter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCountryFighterCollection")
	public ModelAndView confirmDeleteCountryFighterCollection(@RequestParam String country_codeCountry, @RequestParam Long related_fightercollection_idFighter) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("fighter", fighterDAO.findByidFighter(related_fightercollection_idFighter));
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.setViewName("country/fightercollection/deleteFighterCollection.jsp");

		return mav;
	}

	/**
	 * Select the child City entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCountryCityCollection")
	public ModelAndView confirmDeleteCountryCityCollection(@RequestParam String country_codeCountry, @RequestParam Integer related_citycollection_idCity) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("city", cityDAO.findByIdCity(related_citycollection_idCity));
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.setViewName("country/citycollection/deleteCityCollection.jsp");

		return mav;
	}

	/**
	 * View an existing EventTable entity
	 * 
	 */
	@RequestMapping("/selectCountryEventTableCollection")
	public ModelAndView selectCountryEventTableCollection(@RequestParam String country_codeCountry, @RequestParam Long eventtablecollection_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventtablecollection_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("country/eventtablecollection/viewEventTableCollection.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/countryController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Edit an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/editCountrySanctionerCollection")
	public ModelAndView editCountrySanctionerCollection(@RequestParam String country_codeCountry, @RequestParam Long sanctionercollection_idSanctioner) {
		Sanctioner sanctioner = sanctionerDAO.findByIdSanctioner(sanctionercollection_idSanctioner);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("sanctioner", sanctioner);
		mav.setViewName("country/sanctionercollection/editSanctionerCollection.jsp");

		return mav;
	}

	/**
	 * View an existing Fighter entity
	 * 
	 */
	@RequestMapping("/selectCountryFighterCollection")
	public ModelAndView selectCountryFighterCollection(@RequestParam String country_codeCountry, @RequestParam Long fightercollection_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fightercollection_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("fighter", fighter);
		mav.setViewName("country/fightercollection/viewFighterCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing City entity
	 * 
	 */
	@RequestMapping("/saveCountryCityCollection")
	public ModelAndView saveCountryCityCollection(@RequestParam String country_codeCountry, @ModelAttribute City citycollection) {
		Country parent_country = countryService.saveCountryCityCollection(country_codeCountry, citycollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", parent_country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Delete an existing Fighter entity
	 * 
	 */
	@RequestMapping("/deleteCountryFighterCollection")
	public ModelAndView deleteCountryFighterCollection(@RequestParam String country_codeCountry, @RequestParam Long related_fightercollection_idFighter) {
		ModelAndView mav = new ModelAndView();

		Country country = countryService.deleteCountryFighterCollection(country_codeCountry, related_fightercollection_idFighter);

		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Select the child Promoter entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCountryPromoterCollection")
	public ModelAndView confirmDeleteCountryPromoterCollection(@RequestParam String country_codeCountry, @RequestParam Long related_promotercollection_idPromoter) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("promoter", promoterDAO.findByIdPromoter(related_promotercollection_idPromoter));
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.setViewName("country/promotercollection/deletePromoterCollection.jsp");

		return mav;
	}

	/**
	 * View an existing City entity
	 * 
	 */
	@RequestMapping("/selectCountryCityCollection")
	public ModelAndView selectCountryCityCollection(@RequestParam String country_codeCountry, @RequestParam Integer citycollection_idCity) {
		City city = cityDAO.findByIdCity(citycollection_idCity);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("city", city);
		mav.setViewName("country/citycollection/viewCityCollection.jsp");

		return mav;
	}

	/**
	 * Create a new Promoter entity
	 * 
	 */
	@RequestMapping("/newCountryPromoterCollection")
	public ModelAndView newCountryPromoterCollection(@RequestParam String country_codeCountry) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("promoter", new Promoter());
		mav.addObject("newFlag", true);
		mav.setViewName("country/promotercollection/editPromoterCollection.jsp");

		return mav;
	}

	/**
	 * Select the Country entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCountry")
	public ModelAndView confirmDeleteCountry(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/deleteCountry.jsp");

		return mav;
	}

	/**
	 * Show all EventTable entities by Country
	 * 
	 */
	@RequestMapping("/listCountryEventTableCollection")
	public ModelAndView listCountryEventTableCollection(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/eventtablecollection/listEventTableCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing EventTable entity
	 * 
	 */
	@RequestMapping("/editCountryEventTableCollection")
	public ModelAndView editCountryEventTableCollection(@RequestParam String country_codeCountry, @RequestParam Long eventtablecollection_idEvent) {
		EventTable eventtable = eventTableDAO.findByIdEvent(eventtablecollection_idEvent);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("eventtable", eventtable);
		mav.setViewName("country/eventtablecollection/editEventTableCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/saveCountrySanctionerCollection")
	public ModelAndView saveCountrySanctionerCollection(@RequestParam String country_codeCountry, @ModelAttribute Sanctioner sanctionercollection) {
		Country parent_country = countryService.saveCountrySanctionerCollection(country_codeCountry, sanctionercollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", parent_country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Edit an existing Promoter entity
	 * 
	 */
	@RequestMapping("/editCountryPromoterCollection")
	public ModelAndView editCountryPromoterCollection(@RequestParam String country_codeCountry, @RequestParam Long promotercollection_idPromoter) {
		Promoter promoter = promoterDAO.findByIdPromoter(promotercollection_idPromoter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("promoter", promoter);
		mav.setViewName("country/promotercollection/editPromoterCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing Promoter entity
	 * 
	 */
	@RequestMapping("/saveCountryPromoterCollection")
	public ModelAndView saveCountryPromoterCollection(@RequestParam String country_codeCountry, @ModelAttribute Promoter promotercollection) {
		Country parent_country = countryService.saveCountryPromoterCollection(country_codeCountry, promotercollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", parent_country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Select an existing Country entity
	 * 
	 */
	@RequestMapping("/selectCountry")
	public ModelAndView selectCountry(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Show all Sanctioner entities by Country
	 * 
	 */
	@RequestMapping("/listCountrySanctionerCollection")
	public ModelAndView listCountrySanctionerCollection(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/sanctionercollection/listSanctionerCollection.jsp");

		return mav;
	}

	/**
	 * Show all Fighter entities by Country
	 * 
	 */
	@RequestMapping("/listCountryFighterCollection")
	public ModelAndView listCountryFighterCollection(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/fightercollection/listFighterCollection.jsp");

		return mav;
	}

	/**
	 * Create a new Sanctioner entity
	 * 
	 */
	@RequestMapping("/newCountrySanctionerCollection")
	public ModelAndView newCountrySanctionerCollection(@RequestParam String country_codeCountry) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("sanctioner", new Sanctioner());
		mav.addObject("newFlag", true);
		mav.setViewName("country/sanctionercollection/editSanctionerCollection.jsp");

		return mav;
	}

	/**
	 * Create a new Fighter entity
	 * 
	 */
	@RequestMapping("/newCountryFighterCollection")
	public ModelAndView newCountryFighterCollection(@RequestParam String country_codeCountry) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("fighter", new Fighter());
		mav.addObject("newFlag", true);
		mav.setViewName("country/fightercollection/editFighterCollection.jsp");

		return mav;
	}

	/**
	 * Create a new City entity
	 * 
	 */
	@RequestMapping("/newCountryCityCollection")
	public ModelAndView newCountryCityCollection(@RequestParam String country_codeCountry) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("city", new City());
		mav.addObject("newFlag", true);
		mav.setViewName("country/citycollection/editCityCollection.jsp");

		return mav;
	}

	/**
	 * Show all City entities by Country
	 * 
	 */
	@RequestMapping("/listCountryCityCollection")
	public ModelAndView listCountryCityCollection(@RequestParam String codeCountryKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(codeCountryKey));
		mav.setViewName("country/citycollection/listCityCollection.jsp");

		return mav;
	}

	/**
	 * View an existing Promoter entity
	 * 
	 */
	@RequestMapping("/selectCountryPromoterCollection")
	public ModelAndView selectCountryPromoterCollection(@RequestParam String country_codeCountry, @RequestParam Long promotercollection_idPromoter) {
		Promoter promoter = promoterDAO.findByIdPromoter(promotercollection_idPromoter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("promoter", promoter);
		mav.setViewName("country/promotercollection/viewPromoterCollection.jsp");

		return mav;
	}

	/**
	 * Select the child EventTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCountryEventTableCollection")
	public ModelAndView confirmDeleteCountryEventTableCollection(@RequestParam String country_codeCountry, @RequestParam Long related_eventtablecollection_idEvent) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("eventtable", eventTableDAO.findByIdEvent(related_eventtablecollection_idEvent));
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.setViewName("country/eventtablecollection/deleteEventTableCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing City entity
	 * 
	 */
	@RequestMapping("/deleteCountryCityCollection")
	public ModelAndView deleteCountryCityCollection(@RequestParam String country_codeCountry, @RequestParam Integer related_citycollection_idCity) {
		ModelAndView mav = new ModelAndView();

		Country country = countryService.deleteCountryCityCollection(country_codeCountry, related_citycollection_idCity);

		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Country entities
	 * 
	 */
	public String indexCountry() {
		return "redirect:/indexCountry";
	}

	/**
	 * Edit an existing Fighter entity
	 * 
	 */
	@RequestMapping("/editCountryFighterCollection")
	public ModelAndView editCountryFighterCollection(@RequestParam String country_codeCountry, @RequestParam Long fightercollection_idFighter) {
		Fighter fighter = fighterDAO.findByidFighter(fightercollection_idFighter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("fighter", fighter);
		mav.setViewName("country/fightercollection/editFighterCollection.jsp");

		return mav;
	}

	/**
	 * Show all Country entities
	 * 
	 */
	@RequestMapping("/indexCountry")
	public ModelAndView listCountrys() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("countrys", countryService.loadCountrys());

		mav.setViewName("country/listCountrys.jsp");

		return mav;
	}

	/**
	 * Delete an existing Promoter entity
	 * 
	 */
	@RequestMapping("/deleteCountryPromoterCollection")
	public ModelAndView deleteCountryPromoterCollection(@RequestParam String country_codeCountry, @RequestParam Long related_promotercollection_idPromoter) {
		ModelAndView mav = new ModelAndView();

		Country country = countryService.deleteCountryPromoterCollection(country_codeCountry, related_promotercollection_idPromoter);

		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Select the child Sanctioner entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCountrySanctionerCollection")
	public ModelAndView confirmDeleteCountrySanctionerCollection(@RequestParam String country_codeCountry, @RequestParam Long related_sanctionercollection_idSanctioner) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("sanctioner", sanctionerDAO.findByIdSanctioner(related_sanctionercollection_idSanctioner));
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.setViewName("country/sanctionercollection/deleteSanctionerCollection.jsp");

		return mav;
	}

	/**
	 * Create a new EventTable entity
	 * 
	 */
	@RequestMapping("/newCountryEventTableCollection")
	public ModelAndView newCountryEventTableCollection(@RequestParam String country_codeCountry) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("eventtable", new EventTable());
		mav.addObject("newFlag", true);
		mav.setViewName("country/eventtablecollection/editEventTableCollection.jsp");

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
	 * Save an existing Country entity
	 * 
	 */
	@RequestMapping("/saveCountry")
	public String saveCountry(@ModelAttribute Country country) {
		countryService.saveCountry(country);
		return "forward:/indexCountry";
	}

	/**
	 * Save an existing Fighter entity
	 * 
	 */
	@RequestMapping("/saveCountryFighterCollection")
	public ModelAndView saveCountryFighterCollection(@RequestParam String country_codeCountry, @ModelAttribute Fighter fightercollection) {
		Country parent_country = countryService.saveCountryFighterCollection(country_codeCountry, fightercollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", parent_country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}

	/**
	 * Edit an existing City entity
	 * 
	 */
	@RequestMapping("/editCountryCityCollection")
	public ModelAndView editCountryCityCollection(@RequestParam String country_codeCountry, @RequestParam Integer citycollection_idCity) {
		City city = cityDAO.findByIdCity(citycollection_idCity);

		ModelAndView mav = new ModelAndView();
		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("city", city);
		mav.setViewName("country/citycollection/editCityCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing Sanctioner entity
	 * 
	 */
	@RequestMapping("/deleteCountrySanctionerCollection")
	public ModelAndView deleteCountrySanctionerCollection(@RequestParam String country_codeCountry, @RequestParam Long related_sanctionercollection_idSanctioner) {
		ModelAndView mav = new ModelAndView();

		Country country = countryService.deleteCountrySanctionerCollection(country_codeCountry, related_sanctionercollection_idSanctioner);

		mav.addObject("country_codeCountry", country_codeCountry);
		mav.addObject("country", country);
		mav.setViewName("country/viewCountry.jsp");

		return mav;
	}
}