package rams.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;










import rams.app.domain.City;
import rams.app.domain.Country;
import rams.app.rerpository.CityRepository;
import rams.app.rerpository.CountryRepository;
import rams.app.service.CityService;
import rams.app.service.PageWrapper;

/**
 * Spring MVC controller that handles CRUD requests for City entities
 * 
 */

@Controller("CityController")
public class CityController {

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
	 * Service injected by Spring that provides CRUD operations for City entities
	 * 
	 */
	@Autowired
	private CityService cityService;

	/**
	 * Save an existing Country entity
	 * 
	 */
	@RequestMapping("/saveCityCountryCode")
	public ModelAndView saveCityCountryCode(@RequestParam Integer city_idCity, @ModelAttribute Country countrycode) {
		City parent_city = cityService.saveCityCountryCode(city_idCity, countrycode);

		ModelAndView mav = new ModelAndView();
		mav.addObject("city_idCity", city_idCity);
		mav.addObject("city", parent_city);
		mav.setViewName("city/viewCity");

		return mav;
	}

	/**
	 * Show all Country entities by City
	 * 
	 */
	@RequestMapping("/listCityCountryCode")
	public ModelAndView listCityCountryCode(@RequestParam Integer idCityKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("city", cityDAO.findByIdCity(idCityKey));
		mav.setViewName("city/countrycode/listCountryCode.jsp");

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
	 * Create a new City entity
	 * 
	 */
	@RequestMapping("/newCity")
	public ModelAndView newCity() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("city", new City());
		mav.addObject("newFlag", true);
		mav.setViewName("city/editCity");

		return mav;
	}

	/**
	 * Select the child Country entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCityCountryCode")
	public ModelAndView confirmDeleteCityCountryCode(@RequestParam Integer city_idCity, @RequestParam String related_countrycode_codeCountry) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("country", countryDAO.findByCodeCountry(related_countrycode_codeCountry));
		mav.addObject("city_idCity", city_idCity);
		mav.setViewName("city/countrycode/deleteCountryCode.jsp");

		return mav;
	}

	/**
	 * View an existing Country entity
	 * 
	 */
	@RequestMapping("/selectCityCountryCode")
	public ModelAndView selectCityCountryCode(@RequestParam Integer city_idCity, @RequestParam String countrycode_codeCountry) {
		Country country = countryDAO.findByCodeCountry(countrycode_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("city_idCity", city_idCity);
		mav.addObject("country", country);
		mav.setViewName("city/countrycode/viewCountryCode.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/cityController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Save an existing City entity
	 * 
	 */
	@RequestMapping("/saveCity")
	public String saveCity(@ModelAttribute City city) {
		cityService.saveCity(city);
		return "forward:/indexCity";
	}

	/**
	 * Delete an existing City entity
	 * 
	 */
	@RequestMapping("/deleteCity")
	public String deleteCity(@RequestParam Integer idCityKey) {
		City city = cityDAO.findByIdCity(idCityKey);
		cityService.deleteCity(city);
		return "forward:/indexCity";
	}

	/**
	 * Select an existing City entity
	 * 
	 */
	@RequestMapping("/selectCity")
	public ModelAndView selectCity(@RequestParam Integer idCityKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("city", cityDAO.findByIdCity(idCityKey));
		mav.setViewName("city/viewCity");

		return mav;
	}

	/**
	 * Entry point to show all City entities
	 * 
	 */
	public String indexCity() {
		return "redirect:/indexCity";
	}

	/**
	 * Create a new Country entity
	 * 
	 */
	@RequestMapping("/newCityCountryCode")
	public ModelAndView newCityCountryCode(@RequestParam Integer city_idCity) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("city_idCity", city_idCity);
		mav.addObject("country", new Country());
		mav.addObject("newFlag", true);
		mav.setViewName("city/countrycode/editCountryCode.jsp");

		return mav;
	}

	/**
	 * Delete an existing Country entity
	 * 
	 */
	@RequestMapping("/deleteCityCountryCode")
	public ModelAndView deleteCityCountryCode(@RequestParam Integer city_idCity, @RequestParam String related_countrycode_codeCountry) {
		ModelAndView mav = new ModelAndView();

		City city = cityService.deleteCityCountryCode(city_idCity, related_countrycode_codeCountry);

		mav.addObject("city_idCity", city_idCity);
		mav.addObject("city", city);
		mav.setViewName("city/viewCity");

		return mav;
	}

	/**
	 * Edit an existing City entity
	 * 
	 */
	@RequestMapping("/editCity")
	public ModelAndView editCity(@RequestParam Integer idCityKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("city", cityDAO.findByIdCity(idCityKey));
		mav.setViewName("city/editCity");

		return mav;
	}

	/**
	 * Show all City entities
	 * 
	 */
	@RequestMapping("/indexCity")
	public ModelAndView listCitys(Model model, Pageable p) {
		ModelAndView mav = new ModelAndView();
		
		Page<City> cities= cityDAO.findAll(p);
		mav.addObject("cities",cities);
		mav.addObject("cityPages",cityService.countCitys()/10);
		mav.setViewName("city/listCitys");

		return mav;
	}

	/**
	 * Select the City entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteCity")
	public ModelAndView confirmDeleteCity(@RequestParam Integer idCityKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("city", cityDAO.findByIdCity(idCityKey));
		mav.setViewName("city/deleteCity");

		return mav;
	}

	/**
	 * Edit an existing Country entity
	 * 
	 */
	@RequestMapping("/editCityCountryCode")
	public ModelAndView editCityCountryCode(@RequestParam Integer city_idCity, @RequestParam String countrycode_codeCountry) {
		Country country = countryDAO.findByCodeCountry(countrycode_codeCountry);

		ModelAndView mav = new ModelAndView();
		mav.addObject("city_idCity", city_idCity);
		mav.addObject("country", country);
		mav.setViewName("city/countrycode/editCountryCode.jsp");

		return mav;
	}
}