package rams.app.rerpository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.Authorities;
import rams.app.domain.Users;

public interface UsersRepository extends JpaRepository<Users,String>{
	public Users findByUsername(String username) throws DataAccessException;
	
}
