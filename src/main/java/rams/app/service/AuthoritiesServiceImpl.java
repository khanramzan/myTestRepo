package rams.app.service;

import java.lang.Long;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rams.app.domain.Authorities;
import rams.app.domain.Users;
import rams.app.rerpository.AuthorityRepository;
import rams.app.rerpository.UsersRepository;

/**
 * Spring service that handles CRUD requests for Authorities entities
 * 
 */

@Service("AuthoritiesService")
@Transactional
public class AuthoritiesServiceImpl implements AuthoritiesService {

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
	 * Instantiates a new AuthoritiesServiceImpl.
	 *
	 */
	public AuthoritiesServiceImpl() {
	}

	/**
	 * Save an existing Users entity
	 * 
	 */
	@Transactional
	public Authorities saveAuthoritiesUsername(Long idAuthorities, Users related_username) {
		Authorities authorities =  authoritiesDAO.findByIdAuthorities(idAuthorities);
		Users existingusername = usersDAO.findByUsername(related_username.getUsername());

		// copy into the existing record to preserve existing relationships
		if (existingusername != null) {
			existingusername.setUsername(related_username.getUsername());
			existingusername.setPassword(related_username.getPassword());
			existingusername.setEnabled(related_username.getEnabled());
			related_username = existingusername;
		} else {
			related_username = usersDAO.save(related_username);
			usersDAO.flush();
		}

		authorities.setUsername(related_username);
		related_username.setAuthoritiesCollection((Collection<Authorities>) authorities);
		authorities = authoritiesDAO.save(authorities);
		authoritiesDAO.flush();

		related_username = usersDAO.save(related_username);
		usersDAO.flush();

		return authorities;
	}

	/**
	 */
	@Transactional
	public Authorities findAuthoritiesByPrimaryKey(Long idAuthorities) {
		return authoritiesDAO.findByIdAuthorities(idAuthorities);
	}

	/**
	 * Return all Authorities entity
	 * 
	 */
/*	@Transactional
	public List<Authorities> findAllAuthoritiess(Integer startResult, Integer max) {
		return new java.util.ArrayList<Authorities>(authoritiesDAO.findAll());
	}
*/
	/**
	 * Delete an existing Authorities entity
	 * 
	 */
	@Transactional
	public void deleteAuthorities(Authorities authorities) {
		authoritiesDAO.delete(authorities);
		authoritiesDAO.flush();
	}

	/**
	 * Return a count of all Authorities entity
	 * 
	 */
	@Transactional
	public Long countAuthoritiess() {
		return authoritiesDAO.count();
	}

	/**
	 * Save an existing Authorities entity
	 * 
	 */
	@Transactional
	public void saveAuthorities(Authorities authorities) {
		Authorities existingAuthorities = authoritiesDAO.findByIdAuthorities(authorities.getIdAuthorities());

		if (existingAuthorities != null) {
			if (existingAuthorities != authorities) {
				existingAuthorities.setIdAuthorities(authorities.getIdAuthorities());
				existingAuthorities.setAuthority(authorities.getAuthority());
			}
			authorities = authoritiesDAO.save(existingAuthorities);
		} else {
			authorities = authoritiesDAO.save(authorities);
		}
		authoritiesDAO.flush();
	}

	/**
	 * Delete an existing Users entity
	 * 
	 */
	@Transactional
	public Authorities deleteAuthoritiesUsername(Long authorities_idAuthorities, String related_username_username) {
		Authorities authorities = authoritiesDAO.findById(authorities_idAuthorities);
		Users related_username = usersDAO.findByUsername(related_username_username);

		authorities.setUsername(null);
		related_username.setAuthoritiesCollection(null);
		authorities = authoritiesDAO.save(authorities);
		authoritiesDAO.flush();

		related_username = usersDAO.save(related_username);
		usersDAO.flush();

		usersDAO.delete(related_username);
		usersDAO.flush();

		return authorities;
	}

	/**
	 * Load an existing Authorities entity
	 * 
	 */
	@Transactional
	public List<Authorities> loadAuthoritiess() {
		return authoritiesDAO.findAll();
	}
	
	private Pageable constructPageSpecification(int pageIndex, int elements,String dir) {
		Sort srt;
		if(dir=="ASC")
			srt= new Sort(Sort.Direction.ASC, "cityName");
		else
			srt= new Sort(Sort.Direction.DESC, "cityName");
        Pageable pageSpecification = new PageRequest(pageIndex, elements, srt );
        return pageSpecification;
    }

	@Transactional
	public Page<Authorities> findAllAuthorities(int idx, int elements,
			String dir) {
		 return authoritiesDAO.findAll(constructPageSpecification(idx,elements,dir));
		 
	}
	
}
