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
import rams.app.service.UsersService;

/**
 * Spring MVC controller that handles CRUD requests for Users entities
 * 
 */

@Controller("UsersController")
public class UsersController {

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
	 * Service injected by Spring that provides CRUD operations for Users entities
	 * 
	 */
	@Autowired
	private UsersService usersService;

	/**
	 * Edit an existing Users entity
	 * 
	 */
	@RequestMapping("/editUsers")
	public ModelAndView editUsers(@RequestParam String usernameKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("users", usersDAO.findByUsername(usernameKey));
		mav.setViewName("users/editUsers.jsp");

		return mav;
	}

	/**
	 * Entry point to show all Users entities
	 * 
	 */
	public String indexUsers() {
		return "redirect:/indexUsers";
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
	 * Select the child Authorities entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteUsersAuthoritiesCollection")
	public ModelAndView confirmDeleteUsersAuthoritiesCollection(@RequestParam String users_username, @RequestParam Long related_authoritiescollection_idAuthorities) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("authorities", authoritiesDAO.findByIdAuthorities(related_authoritiescollection_idAuthorities));
		mav.addObject("users_username", users_username);
		mav.setViewName("users/authoritiescollection/deleteAuthoritiesCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing Authorities entity
	 * 
	 */
	@RequestMapping("/deleteUsersAuthoritiesCollection")
	public ModelAndView deleteUsersAuthoritiesCollection(@RequestParam String users_username, @RequestParam Long related_authoritiescollection_idAuthorities) {
		ModelAndView mav = new ModelAndView();

		Users users = usersService.deleteUsersAuthoritiesCollection(users_username, related_authoritiescollection_idAuthorities);

		mav.addObject("users_username", users_username);
		mav.addObject("users", users);
		mav.setViewName("users/viewUsers.jsp");

		return mav;
	}

	/**
	 * Show all Authorities entities by Users
	 * 
	 */
	@RequestMapping("/listUsersAuthoritiesCollection")
	public ModelAndView listUsersAuthoritiesCollection(@RequestParam String usernameKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("users", usersDAO.findByUsername(usernameKey));
		mav.setViewName("users/authoritiescollection/listAuthoritiesCollection.jsp");

		return mav;
	}

	/**
	 * Create a new Users entity
	 * 
	 */
	@RequestMapping("/newUsers")
	public ModelAndView newUsers() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("users", new Users());
		mav.addObject("newFlag", true);
		mav.setViewName("users/editUsers.jsp");

		return mav;
	}

	/**
	 * Show all Users entities
	 * 
	 */
	@RequestMapping("/indexUsers")
	public ModelAndView listUserss() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("userss", usersService.loadUsers());

		mav.setViewName("users/listUserss.jsp");

		return mav;
	}

	/**
	 * Select the Users entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteUsers")
	public ModelAndView confirmDeleteUsers(@RequestParam String usernameKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("users", usersDAO.findByUsername(usernameKey));
		mav.setViewName("users/deleteUsers.jsp");

		return mav;
	}

	/**
	 * Create a new Authorities entity
	 * 
	 */
	@RequestMapping("/newUsersAuthoritiesCollection")
	public ModelAndView newUsersAuthoritiesCollection(@RequestParam String users_username) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users_username", users_username);
		mav.addObject("authorities", new Authorities());
		mav.addObject("newFlag", true);
		mav.setViewName("users/authoritiescollection/editAuthoritiesCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing Authorities entity
	 * 
	 */
	@RequestMapping("/editUsersAuthoritiesCollection")
	public ModelAndView editUsersAuthoritiesCollection(@RequestParam String users_username, @RequestParam Long authoritiescollection_idAuthorities) {
		Authorities authorities = authoritiesDAO.findByIdAuthorities(authoritiescollection_idAuthorities);

		ModelAndView mav = new ModelAndView();
		mav.addObject("users_username", users_username);
		mav.addObject("authorities", authorities);
		mav.setViewName("users/authoritiescollection/editAuthoritiesCollection.jsp");

		return mav;
	}

	/**
	 * Select an existing Users entity
	 * 
	 */
	@RequestMapping("/selectUsers")
	public ModelAndView selectUsers(@RequestParam String usernameKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("users", usersDAO.findByUsername(usernameKey));
		mav.setViewName("users/viewUsers.jsp");

		return mav;
	}

	/**
	 * Save an existing Users entity
	 * 
	 */
	@RequestMapping("/saveUsers")
	public String saveUsers(@ModelAttribute Users users) {
		usersService.saveUsers(users);
		return "forward:/indexUsers";
	}

	/**
	 * Save an existing Authorities entity
	 * 
	 */
	@RequestMapping("/saveUsersAuthoritiesCollection")
	public ModelAndView saveUsersAuthoritiesCollection(@RequestParam String users_username, @ModelAttribute Authorities authoritiescollection) {
		Users parent_users = usersService.saveUsersAuthoritiesCollection(users_username, authoritiescollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("users_username", users_username);
		mav.addObject("users", parent_users);
		mav.setViewName("users/viewUsers.jsp");

		return mav;
	}

	/**
	 * View an existing Authorities entity
	 * 
	 */
	@RequestMapping("/selectUsersAuthoritiesCollection")
	public ModelAndView selectUsersAuthoritiesCollection(@RequestParam String users_username, @RequestParam Long authoritiescollection_idAuthorities) {
		Authorities authorities = authoritiesDAO.findByIdAuthorities(authoritiescollection_idAuthorities);

		ModelAndView mav = new ModelAndView();
		mav.addObject("users_username", users_username);
		mav.addObject("authorities", authorities);
		mav.setViewName("users/authoritiescollection/viewAuthoritiesCollection.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/usersController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Delete an existing Users entity
	 * 
	 */
	@RequestMapping("/deleteUsers")
	public String deleteUsers(@RequestParam String usernameKey) {
		Users users = usersDAO.findByUsername(usernameKey);
		usersService.deleteUsers(users);
		return "forward:/indexUsers";
	}
}