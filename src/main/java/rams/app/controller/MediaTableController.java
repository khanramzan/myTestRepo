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




import rams.app.domain.MediaRelation;
import rams.app.domain.MediaTable;
import rams.app.rerpository.MediaRelationRepository;
import rams.app.rerpository.MediaTableRepository;
import rams.app.service.MediaTableService;

/**
 * Spring MVC controller that handles CRUD requests for MediaTable entities
 * 
 */

@Controller("MediaTableController")
public class MediaTableController {

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
	 * Service injected by Spring that provides CRUD operations for MediaTable entities
	 * 
	 */
	@Autowired
	private MediaTableService mediaTableService;

	/**
	 * Delete an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/deleteMediaTable")
	public String deleteMediaTable(@RequestParam Long idMediaKey) {
		MediaTable mediatable = mediaTableDAO.findByIdMedia(idMediaKey);
		
		mediaTableService.deleteMediaTable(mediatable);
		return "forward:/indexMediaTable";
	}

	/**
	 * Select an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/selectMediaTable")
	public ModelAndView selectMediaTable(@RequestParam Long idMediaKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediatable", mediaTableDAO.findByIdMedia(idMediaKey));
		mav.setViewName("mediatable/viewMediaTable.jsp");

		return mav;
	}

	/**
	 * Create a new MediaTable entity
	 * 
	 */
	@RequestMapping("/newMediaTable")
	public ModelAndView newMediaTable() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediatable", new MediaTable());
		mav.addObject("newFlag", true);
		mav.setViewName("mediatable/editMediaTable.jsp");

		return mav;
	}

	/**
	 * Show all MediaRelation entities by MediaTable
	 * 
	 */
	@RequestMapping("/listMediaTableMediaRelationCollection")
	public ModelAndView listMediaTableMediaRelationCollection(@RequestParam Long idMediaKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediatable", mediaTableDAO.findByIdMedia(idMediaKey));
		mav.setViewName("mediatable/mediarelationcollection/listMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Delete an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/deleteMediaTableMediaRelationCollection")
	public ModelAndView deleteMediaTableMediaRelationCollection(@RequestParam Long mediatable_idMedia, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		MediaTable mediatable = mediaTableService.deleteMediaTableMediaRelationCollection(mediatable_idMedia, related_mediarelationcollection_idMediaRelation);

		mav.addObject("mediatable_idMedia", mediatable_idMedia);
		mav.addObject("mediatable", mediatable);
		mav.setViewName("mediatable/viewMediaTable.jsp");

		return mav;
	}

	/**
	 * Entry point to show all MediaTable entities
	 * 
	 */
	public String indexMediaTable() {
		return "redirect:/indexMediaTable";
	}

	/**
	 * Create a new MediaRelation entity
	 * 
	 */
	@RequestMapping("/newMediaTableMediaRelationCollection")
	public ModelAndView newMediaTableMediaRelationCollection(@RequestParam Long mediatable_idMedia) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("mediatable_idMedia", mediatable_idMedia);
		mav.addObject("mediarelation", new MediaRelation());
		mav.addObject("newFlag", true);
		mav.setViewName("mediatable/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * View an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/selectMediaTableMediaRelationCollection")
	public ModelAndView selectMediaTableMediaRelationCollection(@RequestParam Long mediatable_idMedia, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediatable_idMedia", mediatable_idMedia);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediatable/mediarelationcollection/viewMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Edit an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/editMediaTableMediaRelationCollection")
	public ModelAndView editMediaTableMediaRelationCollection(@RequestParam Long mediatable_idMedia, @RequestParam Long mediarelationcollection_idMediaRelation) {
		MediaRelation mediarelation = mediaRelationDAO.findByIdMediaRelation(mediarelationcollection_idMediaRelation);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediatable_idMedia", mediatable_idMedia);
		mav.addObject("mediarelation", mediarelation);
		mav.setViewName("mediatable/mediarelationcollection/editMediaRelationCollection.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaRelation entity
	 * 
	 */
	@RequestMapping("/saveMediaTableMediaRelationCollection")
	public ModelAndView saveMediaTableMediaRelationCollection(@RequestParam Long mediatable_idMedia, @ModelAttribute MediaRelation mediarelationcollection) {
		MediaTable parent_mediatable = mediaTableService.saveMediaTableMediaRelationCollection(mediatable_idMedia, mediarelationcollection);

		ModelAndView mav = new ModelAndView();
		mav.addObject("mediatable_idMedia", mediatable_idMedia);
		mav.addObject("mediatable", parent_mediatable);
		mav.setViewName("mediatable/viewMediaTable.jsp");

		return mav;
	}

	/**
	 * Show all MediaTable entities
	 * 
	 */
	@RequestMapping("/indexMediaTable")
	public ModelAndView listMediaTables() {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediatables", mediaTableService.loadMediaTables());

		mav.setViewName("mediatable/listMediaTables.jsp");

		return mav;
	}

	/**
	 */
	@RequestMapping("/mediatableController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request, @ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

	/**
	 * Select the child MediaRelation entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaTableMediaRelationCollection")
	public ModelAndView confirmDeleteMediaTableMediaRelationCollection(@RequestParam Long mediatable_idMedia, @RequestParam Long related_mediarelationcollection_idMediaRelation) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediarelation", mediaRelationDAO.findByIdMediaRelation(related_mediarelationcollection_idMediaRelation));
		mav.addObject("mediatable_idMedia", mediatable_idMedia);
		mav.setViewName("mediatable/mediarelationcollection/deleteMediaRelationCollection.jsp");

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
	 * Edit an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/editMediaTable")
	public ModelAndView editMediaTable(@RequestParam Long idMediaKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediatable", mediaTableDAO.findByIdMedia(idMediaKey));
		mav.setViewName("mediatable/editMediaTable.jsp");

		return mav;
	}

	/**
	 * Save an existing MediaTable entity
	 * 
	 */
	@RequestMapping("/saveMediaTable")
	public String saveMediaTable(@ModelAttribute MediaTable mediatable) {
		mediaTableService.saveMediaTable(mediatable);
		return "forward:/indexMediaTable";
	}

	/**
	 * Select the MediaTable entity for display allowing the user to confirm that they would like to delete the entity
	 * 
	 */
	@RequestMapping("/confirmDeleteMediaTable")
	public ModelAndView confirmDeleteMediaTable(@RequestParam Long idMediaKey) {
		ModelAndView mav = new ModelAndView();

		mav.addObject("mediatable", mediaTableDAO.findByIdMedia(idMediaKey));
		mav.setViewName("mediatable/deleteMediaTable.jsp");

		return mav;
	}
}