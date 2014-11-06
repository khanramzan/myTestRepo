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
 * Spring service that handles CRUD requests for Users entities
 * 
 */

@Service("UsersService")
@Transactional
public class UsersServiceImpl implements UsersService {

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
	 * Instantiates a new UsersServiceImpl.
	 *
	 */
	public UsersServiceImpl() {
	}

	/**
	 * Load an existing Users entity
	 * 
	 */
	@Transactional
	public List<Users> loadUsers() {
		return usersDAO.findAll();
	}

	/**
	 * Return all Users entity
	 * 
	 */
	
	/**
	 * Delete an existing Users entity
	 * 
	 */
	@Transactional
	public void deleteUsers(Users users) {
		usersDAO.delete(users);
		usersDAO.flush();
	}

	/**
	 * Delete an existing Authorities entity
	 * 
	 */
	@Transactional
	public Users deleteUsersAuthoritiesCollection(String users_username, Long related_authoritiescollection_idAuthorities) {
		Authorities related_authoritiescollection = authoritiesDAO.findByIdAuthorities(related_authoritiescollection_idAuthorities);

		Users users = usersDAO.findByUsername(users_username);

		related_authoritiescollection.setUsername(null);
		users.setAuthoritiesCollection(null);

		authoritiesDAO.delete(related_authoritiescollection);
		authoritiesDAO.flush();

		return users;
	}

	/**
	 */
	@Transactional
	public Users findUsersByPrimaryKey(String username) {
		return usersDAO.findByUsername(username);
	}

	/**
	 * Return a count of all Users entity
	 * 
	 */
	@Transactional
	public Long countUserss() {
		return usersDAO.count();
	}

	/**
	 * Save an existing Users entity
	 * 
	 */
	@Transactional
	public void saveUsers(Users users) {
		Users existingUsers = usersDAO.findByUsername(users.getUsername());

		if (existingUsers != null) {
			if (existingUsers != users) {
				existingUsers.setUsername(users.getUsername());
				existingUsers.setPassword(users.getPassword());
				existingUsers.setEnabled(users.getEnabled());
			}
			users = usersDAO.save(existingUsers);
		} else {
			users = usersDAO.save(users);
		}
		usersDAO.flush();
	}

	/**
	 * Save an existing Authorities entity
	 * 
	 */
	@Transactional
	public Users saveUsersAuthoritiesCollection(String username, Authorities related_authoritiescollection) {
		Users users = usersDAO.findByUsername(username);
		Authorities existingauthoritiesCollection = authoritiesDAO.findByIdAuthorities(related_authoritiescollection.getIdAuthorities());

		// copy into the existing record to preserve existing relationships
		if (existingauthoritiesCollection != null) {
			existingauthoritiesCollection.setIdAuthorities(related_authoritiescollection.getIdAuthorities());
			existingauthoritiesCollection.setAuthority(related_authoritiescollection.getAuthority());
			related_authoritiescollection = existingauthoritiesCollection;
		} else {
			related_authoritiescollection = authoritiesDAO.save(related_authoritiescollection);
			authoritiesDAO.flush();
		}

		related_authoritiescollection.setUsername(users);
		users.setAuthoritiesCollection((Collection<Authorities>) related_authoritiescollection);
		related_authoritiescollection = authoritiesDAO.save(related_authoritiescollection);
		authoritiesDAO.flush();

		users = usersDAO.save(users);
		usersDAO.flush();

		return users;
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
	public Page<Users> findAllUserss(int idx, int elements, String dir) {
		// TODO Auto-generated method stub
		return usersDAO.findAll(constructPageSpecification(idx, elements, dir));
	}
}
