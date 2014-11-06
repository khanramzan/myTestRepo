package rams.app.service;

import java.lang.Long;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rams.app.domain.Authorities;
import rams.app.domain.Users;

/**
 * Spring service that handles CRUD requests for Authorities entities
 * 
 */
public interface AuthoritiesService {

	/**
	 * Save an existing Users entity
	 * 
	 */
	public Authorities saveAuthoritiesUsername(Long idAuthorities, Users related_username);

	/**
	 */
	public Authorities findAuthoritiesByPrimaryKey(Long idAuthorities_1);

	/**
	 * Return all Authorities entity
	 * 
	 */
	
	public Page <Authorities> findAllAuthorities(int idx, int elements, String dir );

	/**
	 * Delete an existing Authorities entity
	 * 
	 */
	public void deleteAuthorities(Authorities authorities);

	/**
	 * Return a count of all Authorities entity
	 * 
	 */
	public Long countAuthoritiess();

	/**
	 * Save an existing Authorities entity
	 * 
	 */
	public void saveAuthorities(Authorities authorities_1);

	/**
	 * Delete an existing Users entity
	 * 
	 */
	public Authorities deleteAuthoritiesUsername(Long authorities_idAuthorities, String related_username_username);

	/**
	 * Load an existing Authorities entity
	 * 
	 */
	public List<Authorities> loadAuthoritiess();
	

}