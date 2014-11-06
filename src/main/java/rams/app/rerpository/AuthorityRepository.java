package rams.app.rerpository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rams.app.domain.Authorities;
import rams.app.domain.Users;

public interface AuthorityRepository extends JpaRepository<Authorities, Long>{
	
	public List <Users> findUserByAuthority (String authorityId) throws DataAccessException;
	
	
	public List<Authorities> findListByIdAuthorities(Long auth) ;
	
	public Authorities findByIdAuthorities(Long auth) ;
	
	
	
	@Query("SELECT a FROM Authorities a WHERE a.idAuthorities=:ff")
	public Authorities findById(@Param("ff") Long auth) ;
	
}
