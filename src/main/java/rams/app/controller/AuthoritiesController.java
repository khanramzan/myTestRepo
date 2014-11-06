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



import rams.app.domain.Authorities;
import rams.app.domain.Users;
import rams.app.rerpository.AuthorityRepository;
import rams.app.rerpository.UsersRepository;
import rams.app.service.AuthoritiesService;


/**
 * Spring MVC controller that handles CRUD requests for Authorities entities
 * 
 */

@Controller("AuthoritiesController")
public class AuthoritiesController {

	/**
	 * DAO injected by Spring that manages Authorities entities
	 * 
	 */
	@Autowired
	private AuthorityRepository authoritiesDAO;

	/**
	 * DAO injected by Spring that manages Users entities
	 * 
	 */
	@Autowired
	private UsersRepository usersDAO;

	/**
	 * Service injected by Spring that provides CRUD operations for Authorities entities
	 * 
	 */
	@Autowired
	private AuthoritiesService authoritiesService;

	/**
	 * Save an existing Users entity
	 * 
	 */
	@RequestMapping("/saveAuthoritiesUsername")
	public ModelAndView saveAuthoritiesUsername(@RequestParam Long authorities_idAuthorities, @ModelAttribute Users username) {
		Authorities parent_authorities = authoritiesService.saveAuthoritiesUsername(authorities_idAuthorities, username);

		ModelAndView mav = new ModelAndView();
		mav.addObject("authorities_idAuthorities", authorities_idAuthorities);
		mav.addObject("authorities", parent_authorities);
		mav.setViewName("authorities/viewAuthorities.jsp");

		return mav;
	}

	/**
	 * Save an existing Authorities entity
	 * 
	 */
	@RequestMapping("/saveAuthorities")
	public String saveAuthorities(@ModelAttribute Authorities authorities) {
		authoritiesService.saveAuthorities(authorities);
		return "forward:/indexAuthorities";
	}

	/**
	 * Select the child Users entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteAuthoritiesUsername")
	public ModelAndView confirmDeleteAuthoritiesUsername(@RequestParam Long authorities_idAuthorities, @RequestParam String related_username_username) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("users", usersDAO.findByUsername(related_username_username));
		mav.addObject("authorities_idAuthorities", authorities_idAuthorities);
		mav.setViewName("authorities/username/deleteUsername.jsp");

		return mav;
	}

	/**
	 * Show all Users entities by Authorities
	 * 
	 */
	@RequestMapping("/listAuthoritiesUsername")
	public ModelAndView listAuthoritiesUsername(@RequestParam Long idAuthoritiesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("authorities", authoritiesDAO.findByIdAuthorities(idAuthoritiesKey));
		mav.setViewName("authorities/username/listUsername.jsp");

		return mav;
	}

	/**
	 * View an existing Users entity
	 * 
	 */
	@RequestMapping("/selectAuthoritiesUsername")
	public ModelAndView selectAuthoritiesUsername(@RequestParam Long authorities_idAuthorities, @RequestParam String username_username) {
		Users users = usersDAO.findByUsername(username_username);

		ModelAndView mav = new ModelAndView();
		mav.addObject("authorities_idAuthorities", authorities_idAuthorities);
		mav.addObject("users", users);
		mav.setViewName("authorities/username/viewUsername.jsp");

		return mav;
	}

	/**
	 * Edit an existing Authorities entity
	 * 
	 */
	@RequestMapping("/editAuthorities")
	public ModelAndView editAuthorities(@RequestParam Long idAuthoritiesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("authorities", authoritiesDAO.findByIdAuthorities(idAuthoritiesKey));
		mav.setViewName("authorities/editAuthorities.jsp");

		return mav;
	}

	/**
	 * Select the Authorities entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteAuthorities")
	public ModelAndView confirmDeleteAuthorities(@RequestParam Long idAuthoritiesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("authorities", authoritiesDAO.findByIdAuthorities(idAuthoritiesKey));
		mav.setViewName("authorities/deleteAuthorities.jsp");

		return mav;
	}

	/**
	 * Create a new Authorities entity
	 * 
	 */
	@RequestMapping("/newAuthorities")
	public ModelAndView newAuthorities() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("authorities", new Authorities());
		mav.addObject("newFlag", true);
		mav.setViewName("authorities/editAuthorities.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/authoritiesController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
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
	 * Create a new Users entity
	 * 
	 */
	@RequestMapping("/newAuthoritiesUsername")
	public ModelAndView newAuthoritiesUsername(@RequestParam Long authorities_idAuthorities) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("authorities_idAuthorities", authorities_idAuthorities);
		mav.addObject("users", new Users());
		mav.addObject("newFlag", true);
		mav.setViewName("authorities/username/editUsername.jsp");

		return mav;
	}

	/**
	 * Delete an existing Users entity
	 * 
	 */
	@RequestMapping("/deleteAuthoritiesUsername")
	public ModelAndView deleteAuthoritiesUsername(@RequestParam Long authorities_idAuthorities, @RequestParam String related_username_username) {
		ModelAndView mav = new ModelAndView();

		Authorities authorities = authoritiesService.deleteAuthoritiesUsername(authorities_idAuthorities, related_username_username);

		mav.addObject("authorities_idAuthorities", authorities_idAuthorities);
		mav.addObject("authorities", authorities);
		mav.setViewName("authorities/viewAuthorities.jsp");

		return mav;
	}

	/**
	 * Select an existing Authorities entity
	 * 
	 */
	@RequestMapping("/selectAuthorities")
	public ModelAndView selectAuthorities(@RequestParam Long idAuthoritiesKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("authorities", authoritiesDAO.findByIdAuthorities(idAuthoritiesKey));
		mav.setViewName("authorities/viewAuthorities.jsp");

		return mav;
	}

	/**
	 * Show all Authorities entities
	 * 
	 */
	@RequestMapping("/indexAuthorities")
	public ModelAndView listAuthoritiess() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("authoritiess", authoritiesService.loadAuthoritiess());

		mav.setViewName("authorities/listAuthoritiess.jsp");

		return mav;
	}

	/**
	 * Delete an existing Authorities entity
	 * 
	 */
	@RequestMapping("/deleteAuthorities")
	public String deleteAuthorities(@RequestParam Long idAuthoritiesKey) {
		Authorities authorities = authoritiesDAO.findByIdAuthorities(idAuthoritiesKey);
		authoritiesService.deleteAuthorities(authorities);
		return "forward:/indexAuthorities";
	}

	/**
	 * Entry point to show all Authorities entities
	 * 
	 */
	public String indexAuthorities() {
		return "redirect:/indexAuthorities";
	}

	/**
	 * Edit an existing Users entity
	 * 
	 */
	@RequestMapping("/editAuthoritiesUsername")
	public ModelAndView editAuthoritiesUsername(@RequestParam Long authorities_idAuthorities, @RequestParam String username_username) {
		Users users = usersDAO.findByUsername(username_username);

		ModelAndView mav = new ModelAndView();
		mav.addObject("authorities_idAuthorities", authorities_idAuthorities);
		mav.addObject("users", users);
		mav.setViewName("authorities/username/editUsername.jsp");

		return mav;
	}
}