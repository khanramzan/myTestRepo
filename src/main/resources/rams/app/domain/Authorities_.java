package rams.app.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-10-31T12:50:58.240+0000")
@StaticMetamodel(Authorities.class)
public class Authorities_ {
	public static volatile SingularAttribute<Authorities, Long> idAuthorities;
	public static volatile SingularAttribute<Authorities, String> authority;
	public static volatile SingularAttribute<Authorities, Users> username;
}
