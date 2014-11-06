package rams.app.service;

import java.lang.Long;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rams.app.domain.Authorities;
import rams.app.domain.Users;

/**
 * Spring service that handles CRUD requests for Users entities
 * 
 */
public interface UsersService {

	/**
	 * Load an existing Users entity
	 * 
	 */
	public List<Users> loadUsers();

	/**
	 * Return all Users entity
	 * 
	 */
	public Page<Users> findAllUserss(int idx, int elements, String dir );

	/**
	 * Delete an existing Users entity
	 * 
	 */
	public void deleteUsers(Users users);

	/**
	 * Delete an existing Authorities entity
	 * 
	 */
	public Users deleteUsersAuthoritiesCollection(String users_username, Long related_authoritiescollection_idAuthorities);

	/**
	 */
	public Users findUsersByPrimaryKey(String username);

	/**
	 * Return a count of all Users entity
	 * 
	 */
	public Long countUserss();

	/**
	 * Save an existing Users entity
	 * 
	 */
	public void saveUsers(Users users_1);

	/**
	 * Save an existing Authorities entity
	 * 
	 */
	public Users saveUsersAuthoritiesCollection(String username_1, Authorities related_authoritiescollection);
}